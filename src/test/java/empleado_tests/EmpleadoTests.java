package empleado_tests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class EmpleadoTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void deberiaCrearUnEmpleado(){
        Empleado empleado = new Empleado();
        Assertions.assertNotNull(empleado);
    }

    @Test
    public void deberiaCrearseConId(){
        Empleado empleado = new Empleado(1L, null, null, null,null);
        Assertions.assertEquals(1, empleado.getId_empleado());
    }

    @Test
    public void deberiaCrearseConUnNombre(){
        Empleado empleado = new Empleado(1L, "joaquin",null,null,null);
        Assertions.assertEquals(1, empleado.getId_empleado());
        Assertions.assertEquals("joaquin", empleado.getNombre());
    }

    @Test
    public void deberiaCrearseConUnApellido(){
        Empleado empleado = new Empleado(1L, "joaquin","perez",null,null);
        Assertions.assertEquals("perez", empleado.getApellido());
    }

    @Test
    public void deberiaCrearseConUnSueldo(){
        Empleado empleado = new Empleado(1L, "joaquin","perez", 1000.0,null);
        Assertions.assertEquals(1000, empleado.getSueldo());
    }

    @Test
    public void deberiaCrearseConUnPuesto(){
        Empleado empleado = new Empleado(1L, "joaquin","perez", 1000.0, "administrativo");
        Assertions.assertEquals("administrativo", empleado.getPuesto());
    }

    @Test
    public void elNombreNoDeberiaContenerNumeros(){
        Empleado empleado = new Empleado(1L, "joaquin123","perez", 1000.0, "administrativo");
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleado);
        Assertions.assertFalse(violations.isEmpty(), "Se esperaba una violación de validación en el nombre");
        // verifica el mensaje de @Pattern
        Assertions.assertEquals("El nombre es inválido", violations.iterator().next().getMessage());
    }
    @Test
    public void deberiaCrearseSiElNombreEsCorrecto(){
        Empleado empleado = new Empleado(1L, "joaquin", "perez", 1000.0, "administrativo");
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleado);

        Assertions.assertTrue(violations.isEmpty(), "No se esperaba ninguna violación de validación");
    }

    @Test
    public void deberiaCrearseSiElNombreTieneEspacios(){
        Empleado empleado = new Empleado(1L, "joaquin ernesto", "perez", 1000.0, "administrativo");
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleado);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void deberiaCrearseSiNombreTieneAcentos(){
        Empleado empleado = new Empleado(1L, "joaquín ernesto", "perez", 1000.0, "administrativo");
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleado);
        Assertions.assertTrue(violations.isEmpty(), "no se esperaban violaciones");
    }
}
