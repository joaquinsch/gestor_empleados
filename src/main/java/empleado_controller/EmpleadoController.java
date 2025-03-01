package empleado_controller;

import jakarta.validation.Valid;
import model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@Valid @RequestBody Empleado empleado){
        return new ResponseEntity<>(empleadoService.guardarEmpleado(empleado), HttpStatus.CREATED);
    }
}
