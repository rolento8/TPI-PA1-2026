package ar.edu.undec.adapter.data.empleado.repoimplementacion;

import ar.edu.undec.adapter.data.empleado.crud.EmpleadoCrudRepository;
import ar.edu.undec.adapter.data.empleado.model.EmpleadoEntity;
import empleado.modelo.Empleado;
import empleado.modelo.Puesto;
import empleado.output.EmpleadoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmpleadoRepositoryImpl implements EmpleadoRepositoryPort {

    private final EmpleadoCrudRepository crudRepository;

    public EmpleadoRepositoryImpl(EmpleadoCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public boolean existePorLegajo(String legajo) {
        return crudRepository.existsByLegajo(legajo);
    }

    @Override
    public Optional<Empleado> buscarPorLegajo(String legajo) {
        return crudRepository.findByLegajo(legajo).map(this::toDomain);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        EmpleadoEntity entity = new EmpleadoEntity(
                empleado.getId(), empleado.getNombre(), empleado.getLegajo(),
                empleado.getFecha(), empleado.getPuesto().name(), empleado.getSueldo()
        );
        EmpleadoEntity guardado = crudRepository.save(entity);
        return toDomain(guardado);
    }

    @Override
    public List<Empleado> buscarTodos() {
        return crudRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Empleado toDomain(EmpleadoEntity entity) {
        return Empleado.instance(
                entity.getId(), entity.getNombre(), entity.getLegajo(),
                entity.getFecha(), Puesto.valueOf(entity.getPuesto()), entity.getSueldo()
        );
    }
}