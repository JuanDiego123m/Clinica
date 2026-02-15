package com.clinica.model.inventory;

import com.clinica.model.enums.specialty;
    
import java.util.EnumSet;
public class Procedure extends InventoryItem {
    
        private final boolean requiereEspecialista;
        private final EnumSet<specialty> especialidades; // si requiere
    
        public Procedure(String id, String nombre, long costo,
                             boolean requiereEspecialista, EnumSet<specialty> especialidades) {
            super(id, nombre, costo);
            this.requiereEspecialista = requiereEspecialista;
            this.especialidades = (especialidades == null)
                    ? EnumSet.noneOf(specialty.class)
                    : EnumSet.copyOf(especialidades);
            if (requiereEspecialista && this.especialidades.isEmpty()) {
                throw new IllegalArgumentException("Si requiere especialista, debe tener especialidades.");
            }
        }
    
        public boolean isRequiereEspecialista() { return requiereEspecialista; }
        public EnumSet<specialty> getEspecialidades() { return EnumSet.copyOf(especialidades); }
}
