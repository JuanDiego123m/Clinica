package com.clinica.model.nursing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Nursingrecord {

    
        private final String cedulaPaciente;
        private final String cedulaEnfermera;
        private final LocalDateTime fechaHora;
    
        private final Vitalsings signosVitales;
        private final List<ReferenceOrderItem> referencias = new ArrayList<>();
        private final String observaciones;
    
        public Nursingrecord(String cedulaPaciente, String cedulaEnfermera, LocalDateTime fechaHora,
                                  Vitalsings signosVitales, String observaciones) {
            if (cedulaPaciente == null || cedulaPaciente.isBlank())
                throw new IllegalArgumentException("Cédula paciente requerida.");
            if (cedulaEnfermera == null || cedulaEnfermera.isBlank())
                throw new IllegalArgumentException("Cédula enfermera requerida.");
    
            this.cedulaPaciente = cedulaPaciente;
            this.cedulaEnfermera = cedulaEnfermera;
            this.fechaHora = (fechaHora == null) ? LocalDateTime.now() : fechaHora;
            this.signosVitales = Objects.requireNonNull(signosVitales, "Signos vitales requeridos");
            this.observaciones = (observaciones == null) ? "" : observaciones.trim();
        }
    
        public void agregarReferencia(ReferenceOrderItem ref) {
            referencias.add(Objects.requireNonNull(ref));
        }
    
        public String getCedulaPaciente() { return cedulaPaciente; }
        public String getCedulaEnfermera() { return cedulaEnfermera; }
        public LocalDateTime getFechaHora() { return fechaHora; }
        public Vitalsings getSignosVitales() { return signosVitales; }
        public String getObservaciones() { return observaciones; }
    
        public List<ReferenceOrderItem> getReferencias() {
            return Collections.unmodifiableList(referencias);
        }
}
