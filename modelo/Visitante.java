package trabajoFinal.modelo;

import trabajoFinal.enums.TipoUsuario;

public class Visitante extends Usuario {

    private static final double DESCUENTO = 0.0;

    public Visitante(String nombre, String identificacion) {
        super(nombre, identificacion, TipoUsuario.VISITANTE);
    }

    @Override
    public double getDescuento() {
        return DESCUENTO;
    }
}
