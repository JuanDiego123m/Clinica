package com.clinica.model.order;
import com.clinica.model.enums.itemtypeorder;

import java.time.LocalDateTime;
import java.util.*;
public class Order {

    private final String numeroOrden;    // único global (control externo), máx 6 dígitos
    private final String cedulaPaciente;
    private final String cedulaMedico;
    private final LocalDateTime fechaCreacion;

    private final Map<Integer, ItemOrder> items = new HashMap<>(); // item único por número

    public Order(String numeroOrden, String cedulaPaciente, String cedulaMedico, LocalDateTime fechaCreacion) {
        if (numeroOrden == null || !numeroOrden.matches("^\\d{1,6}$"))
            throw new IllegalArgumentException("Número orden inválido (1..6 dígitos).");
        if (cedulaPaciente == null || cedulaPaciente.isBlank())
            throw new IllegalArgumentException("Cédula paciente requerida.");
        if (cedulaMedico == null || cedulaMedico.isBlank())
            throw new IllegalArgumentException("Cédula médico requerida.");

        this.numeroOrden = numeroOrden;
        this.cedulaPaciente = cedulaPaciente;
        this.cedulaMedico = cedulaMedico;
        this.fechaCreacion = (fechaCreacion == null) ? LocalDateTime.now() : fechaCreacion;
    }

    public String getNumeroOrden() { return numeroOrden; }
    public String getCedulaPaciente() { return cedulaPaciente; }
    public String getCedulaMedico() { return cedulaMedico; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void agregarItem(ItemOrder item) {
        Objects.requireNonNull(item, "Item requerido");

        // Regla: no repetir número de ítem dentro de la orden (aunque sea otro tipo)
        if (items.containsKey(item.getNumeroItem())) {
            throw new IllegalArgumentException("Ítem repetido en la orden: " + item.getNumeroItem());
        }

        // Regla: si hay ayuda diagnóstica, no puede haber meds/proc en la misma orden, y viceversa
        boolean hayAyuda = items.values().stream().anyMatch(i -> i.getTipo() == itemtypeorder.AYUDA_DIAGNOSTIC);
        boolean itemEsAyuda = item.getTipo() == itemtypeorder.AYUDA_DIAGNOSTIC;

        if (hayAyuda && !itemEsAyuda) {
            throw new IllegalArgumentException("Orden con ayuda diagnóstica no puede incluir medicamentos/procedimientos.");
        }
        if (!items.isEmpty() && itemEsAyuda) {
            throw new IllegalArgumentException("No se puede agregar ayuda diagnóstica a una orden que ya tiene otros ítems.");
        }

        items.put(item.getNumeroItem(), item);
    }

    public List<ItemOrder> getItemsOrdenados() {
        List<ItemOrder> list = new ArrayList<>(items.values());
        list.sort(Comparator.comparingInt(ItemOrder::getNumeroItem));
        return Collections.unmodifiableList(list);
    }

    public long costoTotal() {
        return items.values().stream().mapToLong(ItemOrder::costoTotal).sum();
    }
}
