package trabajoFinal.modelo;

import trabajoFinal.enums.TipoUsuario;

public class Administrativo extends Usuario {

    private static final double DESCUENTO = 0.25;

    public Administrativo(String nombre, String identificacion) {
        super(nombre, identificacion, TipoUsuario.ADMINISTRATIVO);
    }

    @Override
    public double getDescuento() {
        return DESCUENTO;
    }
}
