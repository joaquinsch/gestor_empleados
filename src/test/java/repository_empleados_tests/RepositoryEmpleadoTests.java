package repository_empleados_tests;

import model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.EmpleadoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class RepositoryEmpleadoTests {


    @Mock
    private EmpleadoRepository empleadoRepository;
    private Empleado empleado;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado();
        empleado.setId_empleado(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setSueldo(1000.0);
        empleado.setPuesto("administrativo");

    }

    @Test
    public void deberiaListarTodos(){
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1L, "asd", "asd", 1000.0, "asd"));
        Mockito.when(empleadoRepository.findAll()).thenReturn(empleados);
        List<Empleado> listaEmpleados = empleadoRepository.findAll();
        Mockito.verify(empleadoRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals("asd",listaEmpleados.get(0).getNombre());
    }

    @Test
    public void deberiaGuardarUnEmpleado(){
        Mockito.when(empleadoRepository.save(Mockito.any(Empleado.class))).thenReturn(empleado);
        Empleado empleadoGuardado = empleadoRepository.save(empleado);
        Mockito.verify(empleadoRepository, Mockito.times(1)).save(empleado);
        Assertions.assertNotNull(empleadoGuardado.getId_empleado());
    }

    @Test
    public void deberiaEliminarEmpleadoPorId(){
        empleadoRepository.deleteById(empleado.getId_empleado());
        Mockito.verify(empleadoRepository).deleteById(empleado.getId_empleado());

    }

    @Test
    public void deberiaBuscarEmpleadoPorId(){
        Mockito.when(empleadoRepository.findById(empleado.getId_empleado())).thenReturn(Optional.of(empleado));
        Optional<Empleado> empleadoBuscado = empleadoRepository.findById(empleado.getId_empleado());
        Mockito.verify(empleadoRepository).findById(empleado.getId_empleado());
        Assertions.assertEquals(empleadoBuscado.get().getId_empleado(), empleado.getId_empleado());

    }
}
