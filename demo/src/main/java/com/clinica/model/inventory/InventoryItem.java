package com.clinica.model.inventory;

public abstract class InventoryItem {
    
        private final String id;
        private final String nombre;
        private final long costo;
    
        protected InventoryItem(String id, String nombre, long costo) {
            if (id == null || id.isBlank()) throw new IllegalArgumentException("Id requerido.");
            if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre requerido.");
            if (costo < 0) throw new IllegalArgumentException("Costo no puede ser negativo.");
            this.id = id.trim();
            this.nombre = nombre.trim();
            this.costo = costo;
        }
    
        public String getId() { return id; }
        public String getNombre() { return nombre; }
        public long getCosto() { return costo; }
}
