package service;

import model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public Empleado guardarEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }

    public Empleado buscarEmpleado(Long id_empleado) {
        return empleadoRepository.findById(id_empleado).orElse(null);
    }
}
