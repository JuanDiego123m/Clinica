package com.clinica.model.order;
import com.clinica.model.enums.itemtypeorder;
public abstract class ItemOrder {

        private final String numeroOrden;
        private final int numeroItem;
    
        protected ItemOrder(String numeroOrden, int numeroItem) {
            if (numeroOrden == null || !numeroOrden.matches("^\\d{1,6}$"))
                throw new IllegalArgumentException("Número orden inválido en ítem.");
            if (numeroItem <= 0)
                throw new IllegalArgumentException("Número de ítem debe ser >= 1.");
            this.numeroOrden = numeroOrden;
            this.numeroItem = numeroItem;
        }
    
        public String getNumeroOrden() { return numeroOrden; }
        public int getNumeroItem() { return numeroItem; }
    
        public abstract itemtypeorder getTipo();
        public abstract long costoTotal();
}
