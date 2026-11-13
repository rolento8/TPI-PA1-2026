package empleado.adapter.data;

import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(EmpleadoRepositoryAdapter.class)
class EmpleadoRepositoryAdapterTest {

    @Autowired
    private EmpleadoRepositoryAdapter adapter;

    @Test
    void guardarYBuscarPorLegajo_Exito() {
        Empleado emp = Empleado.instance(UUID.randomUUID(), "Gabriel Romero", "ABC1234", LocalDate.of(2026, 3, 21), Puesto.ANALISTA, new BigDecimal("500000"));

        adapter.guardar(emp);
        Optional<Empleado> recuperado = adapter.buscarPorLegajo("ABC1234");

        assertTrue(recuperado.isPresent());
        assertEquals("Gabriel Romero", recuperado.get().getNombre());
        assertTrue(adapter.existePorLegajo("ABC1234"));
    }
}