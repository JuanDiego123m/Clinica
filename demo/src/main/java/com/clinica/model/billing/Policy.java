package com.clinica.model.billing;

import java.time.LocalDate;

public class Policy{

    private final String compania;
    private final String numeroPoliza;
    private final boolean activa;
    private final LocalDate fechaFinVigencia;

    public Policy(String compania, String numeroPoliza, boolean activa, LocalDate fechaFinVigencia) {
        if (compania == null || compania.isBlank()) throw new IllegalArgumentException("Compañía requerida.");
        if (numeroPoliza == null || numeroPoliza.isBlank()) throw new IllegalArgumentException("Número póliza requerido.");
        if (fechaFinVigencia == null) throw new IllegalArgumentException("Fecha fin vigencia requerida.");
        this.compania = compania.trim();
        this.numeroPoliza = numeroPoliza.trim();
        this.activa = activa;
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getCompania() { return compania; }
    public String getNumeroPoliza() { return numeroPoliza; }
    public LocalDate getFechaFinVigencia() { return fechaFinVigencia; }

    public boolean isVigenteYActiva(LocalDate hoy) {
        return activa && !fechaFinVigencia.isBefore(hoy);
    }
}