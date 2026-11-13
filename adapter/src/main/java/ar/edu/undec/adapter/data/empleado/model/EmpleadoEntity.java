package ar.edu.undec.adapter.data.empleado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "empleados")
public class EmpleadoEntity {
    @Id
    private UUID id;
    private String nombre;

    @Column(unique = true, nullable = false)
    private String legajo;

    private LocalDate fecha;
    private String puesto;
    private BigDecimal sueldo;

    public EmpleadoEntity() {}

    public EmpleadoEntity(UUID id, String nombre, String legajo, LocalDate fecha, String puesto, BigDecimal sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.fecha = fecha;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getLegajo() { return legajo; }
    public LocalDate getFecha() { return fecha; }
    public String getPuesto() { return puesto; }
    public BigDecimal getSueldo() { return sueldo; }
}