package empleado_controller_tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import empleado_controller.EmpleadoController;

import model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import service.EmpleadoService;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private EmpleadoService empleadoService;
    @InjectMocks
    private EmpleadoController empleadoController;

    private Empleado empleado;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(empleadoController)
               // .setControllerAdvice(new ApiExceptionHandler())esto se necesita para las excepciones personalizadas
                .build();
        objectMapper = new ObjectMapper();
        empleado = new Empleado();
        empleado.setId_empleado(1L);
        empleado.setNombre("carlos");
        empleado.setApellido("gomez");
        empleado.setSueldo(1000.0);
        empleado.setPuesto("dev");
    }

    @Test
    public void deberiaListarTodos() throws Exception {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1L, "asd", "asd", 1000.0, "asd"));
        empleados.add(new Empleado(2L, "aa", "bb", 1500.0, "qcyo" ));
        Mockito.when(empleadoService.listarEmpleados()).thenReturn(empleados);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_empleado").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("asd"))
                .andExpect(jsonPath("$[1].id_empleado").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("aa"));
        Mockito.verify(empleadoService, Mockito.times(1)).listarEmpleados();
    }

    @Test
    public void deberiaGuardarEmpleado() throws Exception {

        Mockito.when(empleadoService.guardarEmpleado(Mockito.any(Empleado.class))).thenReturn(empleado);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_empleado").value(1L))
                .andExpect(jsonPath("$.nombre").value("carlos"))
                .andExpect(jsonPath("$.apellido").value("gomez"))
                .andExpect(jsonPath("$.sueldo").value(1000.0))
                .andExpect(jsonPath("$.puesto").value("dev"));
    }

}
