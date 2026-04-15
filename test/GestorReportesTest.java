package trabajoFinal.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trabajoFinal.enums.TipoEspacio;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.modelo.Carro;
import trabajoFinal.modelo.EspacioParqueadero;
import trabajoFinal.modelo.Tarifa;
import trabajoFinal.servicio.GestorReportes;
import trabajoFinal.servicio.Parqueadero;

import static org.junit.jupiter.api.Assertions.*;

public class GestorReportesTest {

    private Parqueadero parqueadero;
    private GestorReportes gestor;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero("ParkUQ Test");
        parqueadero.agregarEspacio(new EspacioParqueadero("C-01", TipoEspacio.CARRO));
        parqueadero.agregarEspacio(new EspacioParqueadero("C-02", TipoEspacio.CARRO));
        parqueadero.agregarTarifa(new Tarifa(TipoVehiculo.CARRO, 3000.0));
        gestor = new GestorReportes(parqueadero);
    }

    @Test
    public void calcularIngresosTotales_sinHistorial_debeRetornarCero() {
        assertEquals(0.0, gestor.calcularIngresosTotales(), 0.01);
    }

    @Test
    public void calcularTiempoPromedio_sinHistorial_debeRetornarCero() {
        assertEquals(0.0, gestor.calcularTiempoPromedio(), 0.01);
    }

    @Test
    public void calcularIngresosTotales_conRegistros_debeSumarCorrectamente() {
        Carro carro1 = new Carro("AAA111", "Juan Pérez", "111");
        parqueadero.registrarIngreso(carro1, "111");
        parqueadero.registrarSalida("AAA111");

        Carro carro2 = new Carro("BBB222", "Ana López", "222");
        parqueadero.registrarIngreso(carro2, "222");
        parqueadero.registrarSalida("BBB222");

        // Ambos estuvieron menos de 1 hora → tarifa mínima × 2
        double ingresos = gestor.calcularIngresosTotales();
        assertEquals(6000.0, ingresos, 0.01);
    }

    @Test
    public void generarResumenDia_debeIncluirDatosDelDia() {
        Carro carro = new Carro("CCC333", "Rosa Mora", "333");
        parqueadero.registrarIngreso(carro, "333");
        parqueadero.registrarSalida("CCC333");

        String resumen = gestor.generarResumenDia();

        assertTrue(resumen.contains("1"));
        assertTrue(resumen.contains("ParkUQ"));
    }

    @Test
    public void listarVehiculosSobreTiempo_ceroMinutos_debeRetornarActivos() {
        Carro carro = new Carro("DDD444", "Luis Torres", "444");
        parqueadero.registrarIngreso(carro, "444");

        // Con límite de 0 minutos, cualquier vehículo activo supera el tiempo
        int cantidad = gestor.listarVehiculosSobreTiempo(0).size();
        assertEquals(1, cantidad);
    }
}
