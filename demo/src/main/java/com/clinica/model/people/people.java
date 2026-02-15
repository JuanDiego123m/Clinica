package com.clinica.model.people;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public abstract class people {

    private final String cedula; // única
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String direccion; // máx 30
    private String telefono;  // 1..10 dígitos (paciente exige 10)
    private String email;

    private static final Pattern SOLO_DIGITOS = Pattern.compile("^\\d+$");
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    protected people(String cedula, String nombreCompleto, LocalDate fechaNacimiento,
                      String direccion, String telefono, String email) {
        if (cedula == null || !SOLO_DIGITOS.matcher(cedula).matches() || cedula.length() > 10) {
            throw new IllegalArgumentException("Cédula inválida (solo dígitos, máx 10).");
        }
        if (nombreCompleto == null || nombreCompleto.isBlank()) {
            throw new IllegalArgumentException("Nombre completo obligatorio.");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("Fecha de nacimiento obligatoria.");
        }
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 0 || edad > 150) {
            throw new IllegalArgumentException("Edad fuera de rango (0-150).");
        }
        if (direccion != null && direccion.length() > 30) {
            throw new IllegalArgumentException("Dirección máx 30 caracteres.");
        }
        if (telefono != null) {
            if (!SOLO_DIGITOS.matcher(telefono).matches()) throw new IllegalArgumentException("Teléfono solo dígitos.");
            if (telefono.length() < 1 || telefono.length() > 10) throw new IllegalArgumentException("Teléfono 1..10 dígitos.");
        }
        if (email != null && !EMAIL.matcher(email).matches()) {
            throw new IllegalArgumentException("Email inválido.");
        }

        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto.trim();
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getCedula() { return cedula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}