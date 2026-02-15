package com.clinica.model.billing;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.clinica.model.order.Order;
import com.clinica.model.people.doctor;
import com.clinica.model.people.patient;

public class Invoice {

    public static final long COPAGO_FIJO = 50_000;
    public static final long TOPE_COPAGO_ANUAL = 1_000_000;

    private final patient paciente;
    private final doctor medicoTratante;
    private final LocalDate fecha;
    private final List<Order> ordenes = new ArrayList<>();

    public Invoice(patient paciente, doctor medicoTratante, LocalDate fecha) {
        this.paciente = Objects.requireNonNull(paciente, "Paciente requerido");
        this.medicoTratante = Objects.requireNonNull(medicoTratante, "Médico requerido");
        this.fecha = (fecha == null) ? LocalDate.now() : fecha;
    }

    public void agregarOrden(Order orden) {
        ordenes.add(Objects.requireNonNull(orden, "Orden requerida"));
    }

    public long totalServicios() {
        return ordenes.stream().mapToLong(Order::costoTotal).sum();
    }

    public boolean polizaActiva() {
        Policy p = paciente.getPoliza();
        return p != null && p.isVigenteYActiva(fecha);
    }

    public long diasVigenciaPoliza() {
        if (paciente.getPoliza() == null) return 0;
        return Math.max(0, ChronoUnit.DAYS.between(fecha, paciente.getPoliza().getFechaFinVigencia()));
    }

    public long valorPaciente() {
        long total = totalServicios();
        if (!polizaActiva()) return total;

        int anio = fecha.getYear();
        long acumulado = paciente.getCopagoAcumulado(anio);

        if (acumulado >= TOPE_COPAGO_ANUAL) return 0;

        long copago = COPAGO_FIJO;
        long restante = TOPE_COPAGO_ANUAL - acumulado;
        copago = Math.min(copago, restante);

        return Math.min(copago, total);
    }

    public long valorAseguradora() {
        long total = totalServicios();
        return polizaActiva() ? Math.max(0, total - valorPaciente()) : 0;
    }

    public void registrarPagoPaciente() {
        if (polizaActiva()) {
            paciente.acumularCopago(fecha.getYear(), valorPaciente());
        }
    }

    public patient getPaciente() { return paciente; }
    public doctor getMedicoTratante() { return medicoTratante; }
    public LocalDate getFecha() { return fecha; }
    public List<Order> getOrdenes() { return List.copyOf(ordenes); }
}