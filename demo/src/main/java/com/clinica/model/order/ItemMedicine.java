package com.clinica.model.order;
import com.clinica.model.enums.itemtypeorder;
import com.clinica.model.inventory.Medication;

import java.util.Objects;
public class ItemMedicine extends ItemOrder {
    private final Medication medicamento;
    private final String dosis;
    private final int duracionTratamientoDias;
    
        public ItemMedicine(String numeroOrden, int numeroItem, Medication medicamento,
                               String dosis, int duracionTratamientoDias) {
            super(numeroOrden, numeroItem);
            this.medicamento = Objects.requireNonNull(medicamento, "Medicamento requerido");
            if (dosis == null || dosis.isBlank()) throw new IllegalArgumentException("Dosis requerida.");
            if (duracionTratamientoDias <= 0) throw new IllegalArgumentException("Duración debe ser > 0.");
    
            this.dosis = dosis.trim();
            this.duracionTratamientoDias = duracionTratamientoDias;
        }
    
        @Override public itemtypeorder getTipo() { return itemtypeorder.MEDICAMENTO; }
        @Override public long costoTotal() { return medicamento.getCosto(); }
    
        public String getNombreMedicamento() { return medicamento.getNombre(); }
        public String getDosis() { return dosis; }
        public int getDuracionTratamientoDias() { return duracionTratamientoDias; }
}
