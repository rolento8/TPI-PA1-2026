package ar.edu.undec.adapter.service.empleado.beanconfig;

import empleado.output.EmpleadoRepositoryPort;
import empleado.usecase.BuscarEmpleadoUseCase;
import empleado.usecase.CrearEmpleadoUseCase;
import empleado.usecase.ListarEmpleadosUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CrearEmpleadoUseCase crearEmpleadoUseCase(EmpleadoRepositoryPort port) {
        return new CrearEmpleadoUseCase(port);
    }

    @Bean
    public BuscarEmpleadoUseCase buscarEmpleadoUseCase(EmpleadoRepositoryPort port) {
        return new BuscarEmpleadoUseCase(port);
    }

    @Bean
    public ListarEmpleadosUseCase listarEmpleadosUseCase(EmpleadoRepositoryPort port) {
        return new ListarEmpleadosUseCase(port);
    }
}