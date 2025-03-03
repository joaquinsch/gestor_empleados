package empleado_tests;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import com.example.gestor_empleados.model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class EmpleadoTests {

    private Validator validator;
    private Empleado empleado;
    private Set<ConstraintViolation<Empleado>> violations;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        empleado = new Empleado(1L, "joaquín ernesto", "perez", 1000.0, "administrativo");

    }

    @Test
    public void deberiaCrearUnEmpleado(){
        Empleado empleado2 = new Empleado();
        Assertions.assertNotNull(empleado2);
    }

    @Test
    public void deberiaCrearseConId(){

        Assertions.assertEquals(1, empleado.getId_empleado());
    }

    @Test
    public void deberiaCrearseConUnNombre(){
        Assertions.assertEquals(1, empleado.getId_empleado());
        Assertions.assertEquals("joaquín ernesto", empleado.getNombre());
    }

    @Test
    public void deberiaCrearseConUnApellido(){

        Assertions.assertEquals("perez", empleado.getApellido());
    }

    @Test
    public void deberiaCrearseConUnSueldo(){

        Assertions.assertEquals(1000, empleado.getSueldo());
    }

    @Test
    public void deberiaCrearseConUnPuesto(){

        Assertions.assertEquals("administrativo", empleado.getPuesto());
    }

    @Test
    public void elNombreNoDeberiaContenerNumeros(){

        Empleado empleado2 = new Empleado(1L, "joaquín1ernesto", "perez", 1000.0, "administrativo");
        violations = validator.validate(empleado2);
        Assertions.assertEquals("joaquín1ernesto", empleado2.getNombre());
        Assertions.assertFalse(violations.isEmpty(), "Se esperaba una violación de validación en el nombre");
        // verifica el mensaje de @Pattern
        Assertions.assertEquals("El nombre es inválido", violations.iterator().next().getMessage());
    }
    @Test
    public void deberiaCrearseSiElNombreEsCorrecto(){
        violations = validator.validate(empleado);
        Assertions.assertTrue(violations.isEmpty(), "No se esperaba ninguna violación de validación");
    }

    @Test
    public void deberiaCrearseSiElNombreTieneEspacios(){
        violations = validator.validate(empleado);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void deberiaCrearseSiNombreTieneAcentos(){
        violations = validator.validate(empleado);
        Assertions.assertTrue(violations.isEmpty(), "no se esperaban violaciones");
    }

    @Test
    public void deberiaCrearseSiApellidoTieneAcentosYMayusculas(){
        Empleado em = new Empleado(1L, "Joaquín Ernesto", "Pérez", 1000.0, "administrativo");
        violations = validator.validate(em);
        Assertions.assertTrue(violations.isEmpty(), "No se esperaban violaciones de validación");
        Assertions.assertEquals("Pérez", em.getApellido());
    }

    @Test
    public void deberiaDarErrorSiCreaApellidoConNumeros(){
        Empleado em1 = new Empleado(1L, "Joaquín Ernesto", "123 Pérez 1", 1000.0, "administrativo");
        violations = validator.validate(em1);
        Assertions.assertFalse(violations.isEmpty(), "Se esperaban violaciones de validación");
        Assertions.assertEquals("El apellido es inválido", violations.iterator().next().getMessage());
    }

    @Test
    public void elPuestoSoloDebeContenerLetras(){
        violations = validator.validate(empleado);
        Assertions.assertEquals("administrativo", empleado.getPuesto());
        Assertions.assertTrue(violations.isEmpty(), "no se esperaban violaciones de validación");

    }

    @Test
    public void elPuestoNoDeberiaContenerNumeros(){
        Empleado em1 = new Empleado(1L, "Joaquín Ernesto", "Pérez", 1000.0, "administrativo número 1");
        violations = validator.validate(em1);
        Assertions.assertFalse(violations.isEmpty(), "se esperaban errores de validacion");
        Assertions.assertEquals("El puesto es inválido", violations.iterator().next().getMessage());
    }

    @Test
    public void deberiaSerValidoUnNombreConEspaciosAlPrincipioYAlFinalYDeberianEliminarse(){
        Empleado em1 = new Empleado();
        em1.setId_empleado(1L);
        em1.setNombre("Joaquín Ernesto   ");
        em1.setApellido("Pérez");
        em1.setSueldo(1000.0);
        em1.setPuesto("administrativo");

        violations = validator.validate(em1);
        Assertions.assertTrue(violations.isEmpty(), "no es esperaban error de validacion");
        Assertions.assertEquals("Joaquín Ernesto", em1.getNombre());
    }
}
