package empleado_service_tests;


import model.Empleado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.EmpleadoRepository;
import service.EmpleadoService;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTests {

    private Empleado empleado;

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado(1L,
                "joaquín ernesto",
                "perez",
                1000.0,
                "administrativo");

    }
    @Test
    public void deberiaGuardarEmpleado(){
        Mockito.when(empleadoRepository.save(empleado)).thenReturn(empleado);
        empleadoService.guardarEmpleado(empleado);

    }

    @Test
    public void deberiaBuscarElEmpleadoPorIdSiExiste(){
        Mockito.when(empleadoRepository.findById(empleado.getId_empleado())).thenReturn(Optional.of(empleado));
        Empleado empleadoBuscado = empleadoService.buscarEmpleado(empleado.getId_empleado());
        Assertions.assertNotNull(empleadoBuscado);
        Assertions.assertEquals(1L, empleadoBuscado.getId_empleado());
        Assertions.assertEquals("joaquín ernesto", empleadoBuscado.getNombre());
    }

    @Test
    public void deberiaDevolverErrorSiEmpleadoNoSeEncuentra(){
        Mockito.when(empleadoRepository.findById(2L)).thenReturn(Optional.empty());
        NoSuchElementException e = Assertions.assertThrows(
                NoSuchElementException.class,
                () -> empleadoService.buscarEmpleado(2L)
        );

        Assertions.assertTrue(e.getMessage().contains("No se encontró el empleado con el id " + 2L));

    }

    @Test
    public void deberiaEditarElEmpleado(){
        Empleado nuevoEmpleado = new Empleado(2L, "carlos", "pérez", 1500.0, "dev");

        Mockito.when(empleadoRepository.findById(empleado.getId_empleado())).thenReturn(Optional.of(empleado));
        Mockito.when(empleadoRepository.save(Mockito.any(Empleado.class))).thenReturn(nuevoEmpleado);
        Empleado empleadoEditado = empleadoService.editarEmpleado(empleado.getId_empleado(), nuevoEmpleado);

        Assertions.assertEquals(2L, empleadoEditado.getId_empleado());
        Assertions.assertEquals("carlos", empleadoEditado.getNombre());
        Assertions.assertEquals("pérez", empleadoEditado.getApellido());
        Assertions.assertEquals(1500.0, empleadoEditado.getSueldo());
        Assertions.assertEquals("dev", empleadoEditado.getPuesto());

    }
}
