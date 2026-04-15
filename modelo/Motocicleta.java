package trabajoFinal.modelo;

import trabajoFinal.enums.TipoVehiculo;

public class Motocicleta extends Vehiculo {

    private int cilindraje;

    public Motocicleta(String placa, String nombreConductor, String identificacionConductor, int cilindraje) {
        super(placa, TipoVehiculo.MOTOCICLETA, nombreConductor, identificacionConductor);
        this.cilindraje = cilindraje;
    }

    @Override
    public String getTipoDescripcion() {
        return "Motocicleta";
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }
}
