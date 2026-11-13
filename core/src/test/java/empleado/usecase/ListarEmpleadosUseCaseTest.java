package empleado.usecase;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepositoryPort;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ListarEmpleadosUseCaseTest {
    @Test
    void ejecutar_RetornaListaDeEmpleados() {
        EmpleadoRepositoryPort port = mock(EmpleadoRepositoryPort.class);
        ListarEmpleadosUseCase useCase = new ListarEmpleadosUseCase(port);
        Empleado emp = Empleado.instance(UUID.randomUUID(), "Agustin", "ABC1234", LocalDate.now(), Puesto.ANALISTA, new BigDecimal("550000"));

        when(port.buscarTodos()).thenReturn(List.of(emp));

        List<Empleado> resultado = useCase.ejecutar();
        assertEquals(1, resultado.size());
        verify(port, times(1)).buscarTodos();
    }
}