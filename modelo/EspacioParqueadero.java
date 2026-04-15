package trabajoFinal.modelo;

import trabajoFinal.enums.EstadoEspacio;
import trabajoFinal.enums.TipoEspacio;

public class EspacioParqueadero {

    private String codigo;
    private TipoEspacio tipoEspacio;
    private EstadoEspacio estado;
    private Vehiculo vehiculoAsignado;

    public EspacioParqueadero(String codigo, TipoEspacio tipoEspacio) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = EstadoEspacio.DISPONIBLE;
        this.vehiculoAsignado = null;
    }

    public void asignarVehiculo(Vehiculo vehiculo) {
        this.vehiculoAsignado = vehiculo;
        this.estado = EstadoEspacio.OCUPADO;
    }

    public void liberarEspacio() {
        this.vehiculoAsignado = null;
        this.estado = EstadoEspacio.DISPONIBLE;
    }

    public boolean estaDisponible() {
        return estado == EstadoEspacio.DISPONIBLE;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public EstadoEspacio getEstado() {
        return estado;
    }

    public Vehiculo getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    public void setEstado(EstadoEspacio estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Espacio " + codigo + " [" + tipoEspacio + "] — " + estado;
    }
}
