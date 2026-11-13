package empleado.usecase;

import empleado.exception.EmpleadoDuplicadoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepositoryPort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CrearEmpleadoUseCase {

    private final EmpleadoRepositoryPort repositoryPort;

    public CrearEmpleadoUseCase(EmpleadoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Empleado ejecutar(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
        Empleado nuevoEmpleado = Empleado.instance(id, nombre, legajo, fecha, puesto, sueldo);

        if (repositoryPort.existePorLegajo(nuevoEmpleado.getLegajo())) {
            throw new EmpleadoDuplicadoException("Ya existe un empleado con el legajo: " + nuevoEmpleado.getLegajo());
        }

        return repositoryPort.guardar(nuevoEmpleado);
    }
}