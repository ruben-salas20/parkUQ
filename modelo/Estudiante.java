package trabajoFinal.modelo;

import trabajoFinal.enums.TipoUsuario;

public class Estudiante extends Usuario {

    private static final double DESCUENTO = 0.20;

    public Estudiante(String nombre, String identificacion) {
        super(nombre, identificacion, TipoUsuario.ESTUDIANTE);
    }

    @Override
    public double getDescuento() {
        return DESCUENTO;
    }
}
