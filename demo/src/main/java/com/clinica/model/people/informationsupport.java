package com.clinica.model.people;
import java.time.LocalDate;
import com.clinica.model.enums.employeerol;


public class informationsupport extends employee {
    public informationsupport(String cedula, String nombre, LocalDate fn, String dir, String tel, String email) { 
        super(cedula, nombre, fn, dir, tel, email, employeerol.SOPORTE_INFO); 
    }  
}
