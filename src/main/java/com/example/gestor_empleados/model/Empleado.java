package com.example.gestor_empleados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Pattern(message = "El nombre es inválido", regexp = "^\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?:\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]*)*\\s*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String nombre;
    @Pattern(message = "El apellido es inválido", regexp = "^\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?:\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]*)*\\s*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String apellido;
    private Double sueldo;
    @Pattern(message = "El puesto es inválido", regexp = "^\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+(?:\\s*[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]*)*\\s*$", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String puesto;

    public void setNombre(String nombre){
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido){
        this.apellido = apellido.trim();
    }

    public void setPuesto(String puesto){
        this.puesto = puesto.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id_empleado, empleado.id_empleado) && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido, empleado.apellido) && Objects.equals(sueldo, empleado.sueldo) && Objects.equals(puesto, empleado.puesto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_empleado, nombre, apellido, sueldo, puesto);
    }
}
