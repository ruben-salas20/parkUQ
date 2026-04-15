package trabajoFinal.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajoFinal.enums.EstadoEspacio;
import trabajoFinal.enums.TipoEspacio;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.excepcion.PlacaDuplicadaException;
import trabajoFinal.excepcion.SinEspacioDisponibleException;
import trabajoFinal.excepcion.VehiculoNoEncontradoException;
import trabajoFinal.modelo.Carro;
import trabajoFinal.modelo.EspacioParqueadero;
import trabajoFinal.modelo.Tarifa;
import trabajoFinal.servicio.Parqueadero;

import static org.junit.jupiter.api.Assertions.*;

public class ExcepcionesTest {

    private Parqueadero parqueadero;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero("ParkUQ Test");
        parqueadero.agregarEspacio(new EspacioParqueadero("C-01", TipoEspacio.CARRO));
        parqueadero.agregarTarifa(new Tarifa(TipoVehiculo.CARRO, 3000.0));
    }

    @Test
    public void registrarIngreso_placaDuplicada_debeLanzarExcepcion() {
        Carro carro1 = new Carro("AAA111", "Juan Pérez", "111");
        Carro carro2 = new Carro("AAA111", "Ana López", "222");
        parqueadero.registrarIngreso(carro1, "111");

        PlacaDuplicadaException excepcion = assertThrows(
                PlacaDuplicadaException.class,
                () -> parqueadero.registrarIngreso(carro2, "222")
        );
        assertTrue(excepcion.getMessage().contains("AAA111"));
    }

    @Test
    public void registrarIngreso_sinEspacios_debeLanzarExcepcion() {
        // Llenar el único espacio disponible
        Carro carro1 = new Carro("BBB222", "Luis Torres", "333");
        parqueadero.registrarIngreso(carro1, "333");

        // Intentar ingresar otro carro sin espacios
        Carro carro2 = new Carro("CCC333", "María Gil", "444");

        SinEspacioDisponibleException excepcion = assertThrows(
                SinEspacioDisponibleException.class,
                () -> parqueadero.registrarIngreso(carro2, "444")
        );
        assertTrue(excepcion.getMessage().contains("CARRO"));
    }

    @Test
    public void registrarSalida_placaNoExiste_debeLanzarExcepcion() {
        VehiculoNoEncontradoException excepcion = assertThrows(
                VehiculoNoEncontradoException.class,
                () -> parqueadero.registrarSalida("XYZ999")
        );
        assertTrue(excepcion.getMessage().contains("XYZ999"));
    }

    @Test
    public void excepcion_espacioFueraDeServicio_noPermiteIngreso() {
        // Poner el único espacio fuera de servicio
        parqueadero.modificarEstadoEspacio("C-01", EstadoEspacio.FUERA_DE_SERVICIO);

        Carro carro = new Carro("DDD444", "Rosa Mora", "555");

        assertThrows(
                SinEspacioDisponibleException.class,
                () -> parqueadero.registrarIngreso(carro, "555")
        );
    }
}
