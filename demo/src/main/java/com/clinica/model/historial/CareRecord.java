package com.clinica.model.historial;

import com.clinica.model.order.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CareRecord {
        private final LocalDate fecha;     // subclave
        private final String cedulaMedico; // máx 10 dígitos (se valida por Persona si usas Medico)
    
        private final String motivoConsulta;
        private final String sintomatologia;
        private String diagnostico; // puede iniciar vacío si está en etapa de ayuda diagnóstica
    
        private final List<Order> ordenes = new ArrayList<>();
    
        public CareRecord(LocalDate fecha, String cedulaMedico, String motivoConsulta,
                                String sintomatologia, String diagnostico) {
            this.fecha = Objects.requireNonNull(fecha, "Fecha requerida");
            this.cedulaMedico = Objects.requireNonNull(cedulaMedico, "Cédula médico requerida");
            this.motivoConsulta = motivoConsulta;
            this.sintomatologia = sintomatologia;
            this.diagnostico = diagnostico;
        }
    
        public LocalDate getFecha() { return fecha; }
        public String getCedulaMedico() { return cedulaMedico; }
        public String getMotivoConsulta() { return motivoConsulta; }
        public String getSintomatologia() { return sintomatologia; }
        public String getDiagnostico() { return diagnostico; }
    
        public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    
        public void agregarOrden(Order orden) { ordenes.add(Objects.requireNonNull(orden)); }
    
        public List<Order> getOrdenes() { return Collections.unmodifiableList(ordenes); }
    
}
