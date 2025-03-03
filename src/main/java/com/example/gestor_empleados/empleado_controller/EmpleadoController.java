package com.example.gestor_empleados.empleado_controller;

import jakarta.validation.Valid;
import com.example.gestor_empleados.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.gestor_empleados.service.EmpleadoService;

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

    @GetMapping("/buscar/{id_empleado}")
    public ResponseEntity<Empleado> buscarEmpleado(@PathVariable Long id_empleado){
        return new ResponseEntity<>(empleadoService.buscarEmpleado(id_empleado),HttpStatus.OK);
    }

    @PutMapping("/editar/{id_empleado}")
    public ResponseEntity<Empleado> editarEmpleado(@PathVariable Long  id_empleado, @RequestBody Empleado datosEmpleado){
        Empleado empleadoEditado = empleadoService.editarEmpleado(id_empleado, datosEmpleado);
        return new ResponseEntity<>(empleadoEditado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id_empleado}")
    public ResponseEntity<Empleado> eliminarEmpleado(@PathVariable Long id_empleado){
        empleadoService.eliminarEmpleado(id_empleado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
