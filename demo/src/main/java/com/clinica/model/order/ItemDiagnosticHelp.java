package com.clinica.model.order;
import com.clinica.model.enums.specialty;
import com.clinica.model.enums.itemtypeorder;
import com.clinica.model.inventory.DiagnosticHelp;

import java.util.Objects;
public class ItemDiagnosticHelp extends ItemOrder {

    private final DiagnosticHelp examen;
    private final int cantidad;
    private final boolean requiereEspecialista;
    private final specialty especialidad; // null si no requiere

    public ItemDiagnosticHelp(String numeroOrden, int numeroItem, DiagnosticHelp examen,
                                int cantidad, boolean requiereEspecialista, specialty especialidad) {
        super(numeroOrden, numeroItem);
        this.examen = Objects.requireNonNull(examen, "Ayuda diagnóstica requerida");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0.");
        if (requiereEspecialista && especialidad == null)
            throw new IllegalArgumentException("Si requiere especialista, debe indicar especialidad.");

        this.cantidad = cantidad;
        this.requiereEspecialista = requiereEspecialista;
        this.especialidad = especialidad;
    }

    @Override public itemtypeorder getTipo() { return itemtypeorder.AYUDA_DIAGNOSTIC; }
    @Override public long costoTotal() { return (long) cantidad * examen.getCosto(); }

    public String getNombreExamen() { return examen.getNombre(); }
    public int getCantidad() { return cantidad; }
    public boolean isRequiereEspecialista() { return requiereEspecialista; }
    public specialty getEspecialidad() { return especialidad; }
}
