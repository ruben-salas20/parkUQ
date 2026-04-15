package trabajoFinal.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajoFinal.enums.TipoEspacio;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.modelo.Carro;
import trabajoFinal.modelo.Docente;
import trabajoFinal.modelo.EspacioParqueadero;
import trabajoFinal.modelo.Motocicleta;
import trabajoFinal.modelo.RegistroSalida;
import trabajoFinal.modelo.Tarifa;
import trabajoFinal.servicio.Parqueadero;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero("ParkUQ Test");
        parqueadero.agregarEspacio(new EspacioParqueadero("C-01", TipoEspacio.CARRO));
        parqueadero.agregarEspacio(new EspacioParqueadero("C-02", TipoEspacio.CARRO));
        parqueadero.agregarEspacio(new EspacioParqueadero("M-01", TipoEspacio.MOTOCICLETA));
        parqueadero.agregarTarifa(new Tarifa(TipoVehiculo.CARRO, 3000.0));
        parqueadero.agregarTarifa(new Tarifa(TipoVehiculo.MOTOCICLETA, 2000.0));
        parqueadero.agregarTarifa(new Tarifa(TipoVehiculo.BICICLETA, 500.0));
    }

    @Test
    public void registrarIngreso_debeAsignarEspacioYRegistrarVehiculo() {
        Carro carro = new Carro("ABC123", "Juan Pérez", "111222333");

        EspacioParqueadero espacio = parqueadero.registrarIngreso(carro, "111222333");

        assertNotNull(espacio);
        assertEquals("C-01", espacio.getCodigo());
        assertEquals(1, parqueadero.consultarVehiculosActivos().size());
        assertNotNull(carro.getHoraIngreso());
    }

    @Test
    public void registrarSalida_debeGenerarReciboYLiberarEspacio() {
        Carro carro = new Carro("DEF456", "Ana López", "444555666");
        EspacioParqueadero espacio = parqueadero.registrarIngreso(carro, "444555666");

        RegistroSalida registro = parqueadero.registrarSalida("DEF456");

        assertNotNull(registro);
        assertEquals(0, parqueadero.consultarVehiculosActivos().size());
        assertTrue(espacio.estaDisponible());
        assertTrue(registro.getTotalCobrado() >= 0);
    }

    @Test
    public void registrarSalida_conductorConDescuento_debAplicarDescuento() {
        Docente docente = new Docente("Dr. Martínez", "77665544");
        parqueadero.agregarUsuarioAutorizado(docente);

        Carro carro = new Carro("GHI789", "Dr. Martínez", "77665544");
        parqueadero.registrarIngreso(carro, "77665544");
        RegistroSalida registro = parqueadero.registrarSalida("GHI789");

        assertEquals(0.30, registro.getDescuentoAplicado(), 0.001);
        assertTrue(registro.getTotalCobrado() < registro.getTarifaBase());
    }

    @Test
    public void consultarEspaciosDisponibles_despuesDeIngreso_debeReducirCantidad() {
        int disponiblesAntes = parqueadero.consultarEspaciosDisponibles().size();

        Carro carro = new Carro("JKL012", "Rosa Mora", "999888777");
        parqueadero.registrarIngreso(carro, "999888777");

        int disponiblesDespues = parqueadero.consultarEspaciosDisponibles().size();

        assertEquals(disponiblesAntes - 1, disponiblesDespues);
    }

    @Test
    public void ingresoMotocicleta_noDebeOcuparEspacioDeCarro() {
        Motocicleta moto = new Motocicleta("MNO345", "Carlos Gil", "333222111", 200);
        EspacioParqueadero espacio = parqueadero.registrarIngreso(moto, "333222111");

        assertEquals(TipoEspacio.MOTOCICLETA, espacio.getTipoEspacio());
    }

    @Test
    public void placas_sonCasInsensibles_alBuscarSalida() {
        Carro carro = new Carro("PQR678", "Luis Torres", "666777888");
        parqueadero.registrarIngreso(carro, "666777888");

        // Registrar salida con placa en minúsculas
        RegistroSalida registro = parqueadero.registrarSalida("pqr678");

        assertNotNull(registro);
    }
}
