package com.clinica.model.people;
import com.clinica.model.enums.contactrelationship;
import java.util.regex.Pattern;

public class emergencycontact {
    private final String nombres;
    private final String apellidos; 
    private final contactrelationship relacion; 
    private final String telefonoEmergencia;


    private static final Pattern DIEZ = Pattern.compile("^\\d{10}$"); 
    public emergencycontact(String nombres, String apellidos, contactrelationship relacion, String telefonoEmergencia) { 
        if (nombres == null || nombres.isBlank()) 
            throw new IllegalArgumentException("Nombres requeridos."); 
        if (apellidos == null || apellidos.isBlank()) 
            throw new IllegalArgumentException("Apellidos requeridos.");
        if (relacion == null) 
            throw new IllegalArgumentException("Relación requerida."); 
        if (telefonoEmergencia == null || !DIEZ.matcher(telefonoEmergencia).matches()) { 
            throw new IllegalArgumentException("Teléfono emergencia debe tener 10 dígitos."); } 
            this.nombres = nombres.trim(); 
            this.apellidos = apellidos.trim(); 
            this.relacion = relacion; 
            this.telefonoEmergencia = telefonoEmergencia; 
        }
            public String getNombres() { return nombres; }
            public String getApellidos() { return apellidos; }
        
            public String getNombreCompleto() { return nombres + " " + apellidos; } 
            public contactrelationship getRelacion() { return relacion; }
            public String getTelefonoEmergencia() { return telefonoEmergencia; } 
        
}
