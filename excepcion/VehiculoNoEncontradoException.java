package trabajoFinal.excepcion;

public class VehiculoNoEncontradoException extends ParkUQException {

    public VehiculoNoEncontradoException(String placa) {
        super("No se encontró ningún vehículo activo con la placa: " + placa);
    }
}
