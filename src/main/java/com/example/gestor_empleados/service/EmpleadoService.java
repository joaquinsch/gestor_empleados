package com.example.gestor_empleados.service;

import com.example.gestor_empleados.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestor_empleados.repository.EmpleadoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado guardarEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public Empleado buscarEmpleado(Long id_empleado) {
        return empleadoRepository.findById(id_empleado).orElseThrow(() ->
                new NoSuchElementException("No se encontró el empleado con el id " + id_empleado));
    }

    public Empleado editarEmpleado(Long id_empleado, Empleado nuevoEmpleado) {
        Empleado buscado = buscarEmpleado(id_empleado);
        buscado.setNombre(nuevoEmpleado.getNombre());
        buscado.setApellido(nuevoEmpleado.getApellido());
        buscado.setSueldo(nuevoEmpleado.getSueldo());
        buscado.setPuesto(nuevoEmpleado.getPuesto());
        return empleadoRepository.save(buscado);
    }


    public void eliminarEmpleado(Long id_empleado) {
        buscarEmpleado(id_empleado);
        empleadoRepository.deleteById(id_empleado);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }
}
