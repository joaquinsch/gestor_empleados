package empleado_controller;

import jakarta.validation.Valid;
import model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmpleadoService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;


    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        return new ResponseEntity<>(empleadoService.listarEmpleados(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@Valid @RequestBody Empleado empleado){
        return new ResponseEntity<>(empleadoService.guardarEmpleado(empleado), HttpStatus.CREATED);
    }


}
