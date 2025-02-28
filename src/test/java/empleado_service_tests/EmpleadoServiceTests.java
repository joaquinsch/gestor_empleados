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
}
