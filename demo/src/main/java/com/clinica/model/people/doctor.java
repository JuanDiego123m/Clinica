package com.clinica.model.people;

import com.clinica.model.enums.employeerol; 
import java.time.LocalDate; 

public class doctor extends employee { 
    public doctor(String cedula, String nombre, LocalDate fn, String dir, String tel, String email) { 
    super(cedula, nombre, fn, dir, tel, email, employeerol.MEDICO); } 
} 
