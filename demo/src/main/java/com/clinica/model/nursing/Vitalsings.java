package com.clinica.model.nursing;

public class Vitalsings {
    
        private final String presionArterial;
        private final double temperatura;
        private final int pulso;
        private final int nivelOxigeno;
    
        public Vitalsings(String presionArterial, double temperatura, int pulso, int nivelOxigeno) {
            if (presionArterial == null || presionArterial.isBlank())
                throw new IllegalArgumentException("Presión arterial requerida.");
            if (temperatura < 30 || temperatura > 45)
                throw new IllegalArgumentException("Temperatura fuera de rango.");
            if (pulso <= 0)
                throw new IllegalArgumentException("Pulso inválido.");
            if (nivelOxigeno < 0 || nivelOxigeno > 100)
                throw new IllegalArgumentException("Oxígeno debe estar entre 0 y 100.");
            this.presionArterial = presionArterial.trim();
            this.temperatura = temperatura;
            this.pulso = pulso;
            this.nivelOxigeno = nivelOxigeno;
        }
    
        public String getPresionArterial() { return presionArterial; }
        public double getTemperatura() { return temperatura; }
        public int getPulso() { return pulso; }
        public int getNivelOxigeno() { return nivelOxigeno; }
}
