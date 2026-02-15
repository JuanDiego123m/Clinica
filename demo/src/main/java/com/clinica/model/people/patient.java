package com.clinica.model.people;

import com.clinica.model.enums.gender;
import com.clinica.model.billing.Policy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class patient extends people {

    private final gender genero;

    private emergencycontact contactoEmergencia;
    private Policy poliza;                   

    private final Map<Integer, Long> copagoAnual = new HashMap<>();

    private static final Pattern DIEZ = Pattern.compile("^\\d{10}$");

    public patient(String cedula, String nombre, LocalDate fn, gender genero,
                    String direccion, String telefono10, String email) {
        super(cedula, nombre, fn, direccion, telefono10, email);
        if (genero == null) throw new IllegalArgumentException("Género requerido.");
        if (telefono10 == null || !DIEZ.matcher(telefono10).matches()) {
            throw new IllegalArgumentException("Teléfono paciente debe tener 10 dígitos.");
        }
        this.genero = genero;
    }
    public gender getGenero() { return genero; }

    public emergencycontact getContactoEmergencia() { return contactoEmergencia; }
    public void setContactoEmergencia(emergencycontact contactoEmergencia) {
        this.contactoEmergencia = contactoEmergencia;
    }

    public Policy getPoliza() { return poliza; }
    public void setPoliza(Policy poliza) { this.poliza = poliza; }

    public long getCopagoAcumulado(int anio) { return copagoAnual.getOrDefault(anio, 0L); }

    public void acumularCopago(int anio, long valor) {
        if (valor < 0) throw new IllegalArgumentException("Copago inválido.");
        copagoAnual.put(anio, getCopagoAcumulado(anio) + valor);
    }
}