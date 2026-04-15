package trabajoFinal.modelo;

import trabajoFinal.enums.TipoUsuario;

public class Docente extends Usuario {

    private static final double DESCUENTO = 0.30;

    public Docente(String nombre, String identificacion) {
        super(nombre, identificacion, TipoUsuario.DOCENTE);
    }

    @Override
    public double getDescuento() {
        return DESCUENTO;
    }
}
