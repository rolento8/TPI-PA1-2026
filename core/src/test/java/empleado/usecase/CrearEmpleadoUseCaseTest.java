package empleado.usecase;

import empleado.exception.EmpleadoDuplicadoException;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrearEmpleadoUseCaseTest {

    private EmpleadoRepositoryPort repositoryPort;
    private CrearEmpleadoUseCase useCase;

    @BeforeEach
    void setUp() {
        repositoryPort = mock(EmpleadoRepositoryPort.class);
        useCase = new CrearEmpleadoUseCase(repositoryPort);
    }

    @Test
    void ejecutar_DatosValidos_GuardaYRetornaEmpleado() {
        UUID id = UUID.randomUUID();
        when(repositoryPort.existePorLegajo("ABC1234")).thenReturn(false);
        when(repositoryPort.guardar(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Empleado resultado = useCase.ejecutar(id, "Gabriel Agustin Romero", "ABC1234", LocalDate.of(2026, 3, 21), Puesto.ANALISTA, new BigDecimal("500000"));

        assertNotNull(resultado);
        assertEquals("ABC1234", resultado.getLegajo());
        verify(repositoryPort, times(1)).existePorLegajo("ABC1234");
        verify(repositoryPort, times(1)).guardar(any(Empleado.class));
    }

    @Test
    void ejecutar_LegajoDuplicado_LanzaExcepcion() {
        UUID id = UUID.randomUUID();
        when(repositoryPort.existePorLegajo("ABC1234")).thenReturn(true);

        assertThrows(EmpleadoDuplicadoException.class, () ->
                useCase.ejecutar(id, "Gabriel Agustin Romero", "ABC1234", LocalDate.of(2026, 3, 21), Puesto.ANALISTA, new BigDecimal("500000"))
        );

        verify(repositoryPort, times(1)).existePorLegajo("ABC1234");
        verify(repositoryPort, never()).guardar(any(Empleado.class));
    }
}