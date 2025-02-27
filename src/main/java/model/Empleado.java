package model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    private Long id_empleado;
    @Pattern(message = "El nombre es inválido", regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", flags = Pattern.Flag.CASE_INSENSITIVE)

    private String nombre;
    private String apellido;
    private Double sueldo;
    private String puesto;



}
