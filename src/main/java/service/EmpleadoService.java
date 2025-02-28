package service;

import model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmpleadoRepository;

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
        buscarEmpleado(id_empleado);
        Empleado empleadoEditado = new Empleado();
        empleadoEditado.setId_empleado(nuevoEmpleado.getId_empleado());
        empleadoEditado.setNombre(nuevoEmpleado.getNombre());
        empleadoEditado.setApellido(nuevoEmpleado.getApellido());
        empleadoEditado.setSueldo(nuevoEmpleado.getSueldo());
        empleadoEditado.setPuesto(nuevoEmpleado.getPuesto());
        return empleadoRepository.save(empleadoEditado);
    }


    public void eliminarEmpleado(Long idEmpleado) {
        buscarEmpleado(idEmpleado);
        empleadoRepository.deleteById(idEmpleado);
    }
}
