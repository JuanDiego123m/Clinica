package com.clinica.model.nursing;

public class ReferenceOrderItem {
    private final String numeroOrden;
    private final int numeroItem;
    private final String nota; 

    public ReferenceOrderItem(String numeroOrden, int numeroItem, String nota) {
        if (numeroOrden == null || !numeroOrden.matches("^\\d{1,6}$"))
            throw new IllegalArgumentException("Número orden inválido.");
        if (numeroItem <= 0)
            throw new IllegalArgumentException("Número ítem inválido.");
        this.numeroOrden = numeroOrden;
        this.numeroItem = numeroItem;
        this.nota = (nota == null) ? "" : nota.trim();
    }

    public String getNumeroOrden() { return numeroOrden; }
    public int getNumeroItem() { return numeroItem; }
    public String getNota() { return nota; }
}
