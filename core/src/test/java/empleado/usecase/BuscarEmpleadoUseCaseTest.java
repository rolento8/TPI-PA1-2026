package empleado.usecase;

import empleado.exception.EmpleadoNoEncontradoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarEmpleadoUseCaseTest {

    private EmpleadoRepositoryPort repositoryPort;
    private BuscarEmpleadoUseCase useCase;

    @BeforeEach
    void setUp() {
        repositoryPort = mock(EmpleadoRepositoryPort.class);
        useCase = new BuscarEmpleadoUseCase(repositoryPort);
    }

    @Test
    void ejecutar_LegajoExistente_RetornaEmpleado() {
        Empleado mockEmpleado = Empleado.instance(UUID.randomUUID(), "Gabriel Agustin Romero", "ABC1234", LocalDate.of(2026, 3, 21), Puesto.ANALISTA, new BigDecimal("500000"));
        when(repositoryPort.buscarPorLegajo("ABC1234")).thenReturn(Optional.of(mockEmpleado));

        Empleado resultado = useCase.ejecutar("ABC1234");

        assertNotNull(resultado);
        assertEquals("ABC1234", resultado.getLegajo());
        verify(repositoryPort, times(1)).buscarPorLegajo("ABC1234");
    }

    @Test
    void ejecutar_LegajoInexistente_LanzaExcepcion() {
        when(repositoryPort.buscarPorLegajo("XYZ9999")).thenReturn(Optional.empty());

        assertThrows(EmpleadoNoEncontradoException.class, () ->
                useCase.ejecutar("XYZ9999")
        );

        verify(repositoryPort, times(1)).buscarPorLegajo("XYZ9999");
    }
}