package com.clinica.model.people;
import com.clinica.model.enums.employeerol;
import java.time.LocalDate;

public abstract class employee extends people{
    private final employeerol rol;

    protected employee(String cedula, String nombreCompleto, LocalDate fechaNacimiento,
        String direccion, String telefono, String email, employeerol rol) {
            super(cedula, nombreCompleto, fechaNacimiento, direccion, telefono, email);
            if (rol == null) throw new IllegalArgumentException("Rol obligatorio."); 
            this.rol = rol; 
        } 
            
            public employeerol getRol() { return rol; } 
        
}

