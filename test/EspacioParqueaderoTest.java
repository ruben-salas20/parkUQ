package trabajoFinal.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajoFinal.enums.EstadoEspacio;
import trabajoFinal.enums.TipoEspacio;
import trabajoFinal.modelo.Carro;
import trabajoFinal.modelo.EspacioParqueadero;

import static org.junit.jupiter.api.Assertions.*;

public class EspacioParqueaderoTest {

    private EspacioParqueadero espacio;

    @BeforeEach
    public void setUp() {
        espacio = new EspacioParqueadero("C-01", TipoEspacio.CARRO);
    }

    @Test
    public void espacio_estadoInicialDebeSerDisponible() {
        assertEquals(EstadoEspacio.DISPONIBLE, espacio.getEstado());
        assertTrue(espacio.estaDisponible());
        assertNull(espacio.getVehiculoAsignado());
    }

    @Test
    public void asignarVehiculo_debeOcuparElEspacio() {
        Carro carro = new Carro("ABC123", "Juan Pérez", "111222333");
        espacio.asignarVehiculo(carro);

        assertEquals(EstadoEspacio.OCUPADO, espacio.getEstado());
        assertFalse(espacio.estaDisponible());
        assertEquals(carro, espacio.getVehiculoAsignado());
    }

    @Test
    public void liberarEspacio_debeDevolverEstadoDisponible() {
        Carro carro = new Carro("DEF456", "Ana López", "444555666");
        espacio.asignarVehiculo(carro);
        espacio.liberarEspacio();

        assertEquals(EstadoEspacio.DISPONIBLE, espacio.getEstado());
        assertTrue(espacio.estaDisponible());
        assertNull(espacio.getVehiculoAsignado());
    }

    @Test
    public void espacio_debeTenerCodigoYTipoCorrecto() {
        assertEquals("C-01", espacio.getCodigo());
        assertEquals(TipoEspacio.CARRO, espacio.getTipoEspacio());
    }

    @Test
    public void espacio_fuera_de_servicio_no_estaDisponible() {
        espacio.setEstado(EstadoEspacio.FUERA_DE_SERVICIO);

        assertFalse(espacio.estaDisponible());
    }
}
