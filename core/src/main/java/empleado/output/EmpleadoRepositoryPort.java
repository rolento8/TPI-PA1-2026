package empleado.output;

import empleado.modelo.Empleado;
import java.util.Optional;

public interface EmpleadoRepositoryPort {
    boolean existePorLegajo(String legajo);
    Optional<Empleado> buscarPorLegajo(String legajo);
    Empleado guardar(Empleado empleado);
}