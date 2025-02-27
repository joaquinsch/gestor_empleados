package repository_empleados_tests;

import model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.EmpleadoRepository;


@ExtendWith(MockitoExtension.class)
public class RepositoryEmpleadoTests {


    @Mock
    private EmpleadoRepository empleadoRepository;
    private Empleado empleado;

    @BeforeEach
    public void setUp(){
        empleado = new Empleado();
        empleado.setId_empleado(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setSueldo(1000.0);
        empleado.setPuesto("administrativo");

    }

    @Test
    public void deberiaGuardarUnEmpleado(){
        Mockito.when(empleadoRepository.save(Mockito.any(Empleado.class))).thenReturn(empleado);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        Mockito.verify(empleadoRepository, Mockito.times(1)).save(empleado);
        Assertions.assertNotNull(empleadoGuardado.getId_empleado());
    }
}
