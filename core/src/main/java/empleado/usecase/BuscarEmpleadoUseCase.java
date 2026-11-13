package empleado.usecase;

import empleado.exception.EmpleadoNoEncontradoException;
import empleado.modelo.Empleado;
import empleado.output.EmpleadoRepositoryPort;

public class BuscarEmpleadoUseCase {

    private final EmpleadoRepositoryPort repositoryPort;

    public BuscarEmpleadoUseCase(EmpleadoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Empleado ejecutar(String legajo) {
        return repositoryPort.buscarPorLegajo(legajo)
                .orElseThrow(() -> new EmpleadoNoEncontradoException("No se encontró ningún empleado con el legajo: " + legajo));
    }
}