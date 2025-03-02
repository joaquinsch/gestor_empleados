package com.example.gestor_empleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;
    @Pattern(message = "El nombre es inválido", regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]+( [a-zA-ZáéíóúÁÉÍÓÚüÜ]+)*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String nombre;
    @Pattern(message = "El apellido es inválido", regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]+( [a-zA-ZáéíóúÁÉÍÓÚüÜ]+)*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String apellido;
    private Double sueldo;
    @Pattern(message = "El puesto es inválido", regexp = "^[a-zA-ZáéíóúÁÉÍÓÚüÜ]+( [a-zA-ZáéíóúÁÉÍÓÚüÜ]+)*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String puesto;



}
