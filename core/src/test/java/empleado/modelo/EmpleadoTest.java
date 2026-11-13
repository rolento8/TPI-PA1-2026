package empleado.modelo;

import empleado.exception.EmpleadoInvalidaException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    @Test
    void instance_datosValidos(){

        UUID id = UUID.randomUUID();

        Empleado empleado = Empleado.instance(
                id,
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        );

        assertAll(
                () -> assertEquals(id, empleado.getId()),
                () -> assertEquals("Gabriel Agustin Romero", empleado.getNombre()),
                () -> assertEquals("ABC1234", empleado.getLegajo()),
                () -> assertEquals((LocalDate.of(2026,3,21)), empleado.getFecha()),
                () -> assertEquals(Puesto.ANALISTA, empleado.getPuesto()),
                () -> assertEquals(new BigDecimal("500000"), empleado.getSueldo())
        );
    }

    @Test
    void instance_idNUll(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                null,
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")

        ));

    }

    @Test
    void instance_nombreVacio(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoNull(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                null,
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoVacio(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_legajoInvalido(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "abc123",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_fechaInvalida(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.now().plusDays(1),
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_fechaNull(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                null,
                Puesto.ANALISTA,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_puestoNull(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                null,
                new BigDecimal("500000")
        ));
    }

    @Test
    void instance_sueldoNull(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                null
        ));
    }

    @Test
    void instance_sueldoCero(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                BigDecimal.ZERO
        ));
    }

    @Test
    void instance_sueldoNegativo(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("-100")
        ));
    }

    @Test
    void instance_analistaSueldoMenor(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.ANALISTA,
                new BigDecimal("499999")
        ));
    }

    @Test
    void instance_supervisorSueldoMenor(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.SUPERVISOR,
                new BigDecimal("799999")
        ));
    }

    @Test
    void instance_gerenteSueldoMenor(){

        assertThrows(EmpleadoInvalidaException.class, () -> Empleado.instance(
                UUID.randomUUID(),
                "Gabriel Agustin Romero",
                "ABC1234",
                LocalDate.of(2026,3,21),
                Puesto.GERENTE,
                new BigDecimal("1199999")
        ));
    }
}