package com.clinica.model.historial;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MedicalHistory {
        private final Map<String, Map<LocalDate, CareRecord>> data = new HashMap<>();
    
        public void agregarRegistro(String cedulaPaciente, CareRecord registro) {
            if (cedulaPaciente == null || cedulaPaciente.isBlank())
                throw new IllegalArgumentException("Cédula paciente requerida.");
            if (registro == null)
                throw new IllegalArgumentException("Registro requerido.");
    
            data.computeIfAbsent(cedulaPaciente, k -> new HashMap<>());
    
            Map<LocalDate, CareRecord> porFecha = data.get(cedulaPaciente);
            if (porFecha.containsKey(registro.getFecha())) {
                throw new IllegalArgumentException("Ya existe registro para esa fecha.");
            }
            porFecha.put(registro.getFecha(), registro);
        }
    
        public CareRecord obtenerRegistro(String cedulaPaciente, LocalDate fecha) {
            return data.getOrDefault(cedulaPaciente, Map.of()).get(fecha);
        }
}
