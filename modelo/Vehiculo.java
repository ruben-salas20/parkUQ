package trabajoFinal.modelo;

import trabajoFinal.enums.EstadoVehiculo;
import trabajoFinal.enums.TipoVehiculo;

import java.time.LocalDateTime;

public abstract class Vehiculo {

    private String placa;
    private TipoVehiculo tipoVehiculo;
    private String nombreConductor;
    private String identificacionConductor;
    private LocalDateTime horaIngreso;
    private EspacioParqueadero espacioAsignado;
    private EstadoVehiculo estado;

    public Vehiculo(String placa, TipoVehiculo tipoVehiculo, String nombreConductor, String identificacionConductor) {
        this.placa = placa.toUpperCase();
        this.tipoVehiculo = tipoVehiculo;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;
        this.estado = EstadoVehiculo.DENTRO;
    }

    public abstract String getTipoDescripcion();

    public String getPlaca() {
        return placa;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public String getIdentificacionConductor() {
        return identificacionConductor;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public EspacioParqueadero getEspacioAsignado() {
        return espacioAsignado;
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public void setIdentificacionConductor(String identificacionConductor) {
        this.identificacionConductor = identificacionConductor;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setEspacioAsignado(EspacioParqueadero espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getTipoDescripcion() + " | Placa: " + placa + " | Conductor: " + nombreConductor;
    }
}
