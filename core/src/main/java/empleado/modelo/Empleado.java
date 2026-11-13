package empleado.modelo;

import empleado.exception.EmpleadoInvalidaException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

public class Empleado {
    private UUID id;
    private String nombre;
    private String legajo;
    private LocalDate fecha;
    private Puesto puesto;
    private BigDecimal sueldo;

    private static final Pattern LEGAJO_PATTERN = Pattern.compile("^[A-Z]{3}\\d{4}$");

    private Empleado(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
        validarAtributosObligatorios(id, nombre, legajo, fecha, puesto, sueldo);
        validarLegajo(legajo);
        validarFecha(fecha);
        validarSueldo(puesto, sueldo);

        this.id = id;
        this.nombre = nombre;
        this.legajo = legajo;
        this.fecha = fecha;
        this.puesto = puesto;
        this.sueldo = sueldo;
    }

    public static Empleado instance(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
        return new Empleado(id, nombre, legajo, fecha, puesto, sueldo);
    }

    private void validarAtributosObligatorios(UUID id, String nombre, String legajo, LocalDate fecha, Puesto puesto, BigDecimal sueldo) {
        if (id == null) throw new EmpleadoInvalidaException("El ID no puede ser null");
        if (nombre == null || nombre.trim().isEmpty()) throw new EmpleadoInvalidaException("El nombre no puede estar vacío");
        if (legajo == null || legajo.trim().isEmpty()) throw new EmpleadoInvalidaException("El legajo no puede estar vacío");
        if (fecha == null) throw new EmpleadoInvalidaException("La fecha no puede ser null");
        if (puesto == null) throw new EmpleadoInvalidaException("El puesto no puede ser null");
        if (sueldo == null) throw new EmpleadoInvalidaException("El sueldo no puede ser null");
    }

    private void validarLegajo(String legajo) {
        if (!LEGAJO_PATTERN.matcher(legajo).matches()) {
            throw new EmpleadoInvalidaException("Formato de legajo inválido. Debe ser 3 letras mayúsculas seguidas de 4 números.");
        }
    }

    private void validarFecha(LocalDate fecha) {
        if (fecha.isAfter(LocalDate.now())) {
            throw new EmpleadoInvalidaException("La fecha de ingreso no puede ser posterior a la actual.");
        }
    }

    private void validarSueldo(Puesto puesto, BigDecimal sueldo) {
        if (sueldo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new EmpleadoInvalidaException("El sueldo debe ser mayor a cero.");
        }

        BigDecimal minimo = switch (puesto) {
            case ANALISTA -> new BigDecimal("500000");
            case SUPERVISOR -> new BigDecimal("800000");
            case GERENTE -> new BigDecimal("1200000");
        };

        if (sueldo.compareTo(minimo) < 0) {
            throw new EmpleadoInvalidaException("El sueldo no respeta el piso salarial para el puesto de " + puesto);
        }
    }

    // Getters
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getLegajo() { return legajo; }
    public LocalDate getFecha() { return fecha; }
    public Puesto getPuesto() { return puesto; }
    public BigDecimal getSueldo() { return sueldo; }
}