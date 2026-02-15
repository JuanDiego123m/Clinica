package com.clinica.model.order;
import com.clinica.model.enums.specialty;
import com.clinica.model.enums.itemtypeorder;
import com.clinica.model.inventory.Procedure;

import java.util.Objects;
public class ItemProcedure extends ItemOrder {

    
        private final Procedure procedimiento;
        private final int cantidad;
        private final String frecuencia;
        private final boolean requiereEspecialista;
        private final specialty especialidad;
    
        public ItemProcedure(String numeroOrden, int numeroItem, Procedure procedimiento,
                                 int cantidad, String frecuencia,
                                 boolean requiereEspecialista, specialty especialidad) {
            super(numeroOrden, numeroItem);
            this.procedimiento = Objects.requireNonNull(procedimiento, "Procedimiento requerido");
            if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0.");
            if (frecuencia == null || frecuencia.isBlank()) throw new IllegalArgumentException("Frecuencia requerida.");
    
            if (requiereEspecialista && especialidad == null)
                throw new IllegalArgumentException("Si requiere especialista, debe indicar especialidad.");
    
            this.cantidad = cantidad;
            this.frecuencia = frecuencia.trim();
            this.requiereEspecialista = requiereEspecialista;
            this.especialidad = especialidad;
        }
    
        @Override public itemtypeorder getTipo() { return itemtypeorder.PROCEDIMIENTO; }
        @Override public long costoTotal() { return (long) cantidad * procedimiento.getCosto(); }
    
        public String getNombreProcedimiento() { return procedimiento.getNombre(); }
        public int getCantidad() { return cantidad; }
        public String getFrecuencia() { return frecuencia; }
        public boolean isRequiereEspecialista() { return requiereEspecialista; }
        public specialty getEspecialidad() { return especialidad; }
}
