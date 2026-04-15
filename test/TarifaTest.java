package trabajoFinal.test;

import org.junit.jupiter.api.Test;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.modelo.Tarifa;

import static org.junit.jupiter.api.Assertions.*;

public class TarifaTest {

    @Test
    public void calcularCosto_menosDeUnaHora_debeAplicarMinimoUnaHora() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.CARRO, 3000.0);

        // 30 minutos → mínimo 1 hora
        double costo = tarifa.calcularCosto(30);

        assertEquals(3000.0, costo, 0.01);
    }

    @Test
    public void calcularCosto_exactamenteUnaHora_debeCalcularCorrectamente() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.MOTOCICLETA, 2000.0);

        double costo = tarifa.calcularCosto(60);

        assertEquals(2000.0, costo, 0.01);
    }

    @Test
    public void calcularCosto_dosHoras_debeMultiplicarCorrectamente() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.CARRO, 3000.0);

        double costo = tarifa.calcularCosto(120);

        assertEquals(6000.0, costo, 0.01);
    }

    @Test
    public void calcularCosto_unaHoraYMedia_debeProrratear() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.CARRO, 3000.0);

        double costo = tarifa.calcularCosto(90);

        assertEquals(4500.0, costo, 0.01);
    }

    @Test
    public void tarifa_debePersistirValorPorHora() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.BICICLETA, 500.0);

        assertEquals(TipoVehiculo.BICICLETA, tarifa.getTipoVehiculo());
        assertEquals(500.0, tarifa.getValorPorHora(), 0.01);
    }

    @Test
    public void modificarValorPorHora_debeCambiarElCosto() {
        Tarifa tarifa = new Tarifa(TipoVehiculo.CARRO, 3000.0);
        tarifa.setValorPorHora(4000.0);

        assertEquals(4000.0, tarifa.calcularCosto(60), 0.01);
    }
}
