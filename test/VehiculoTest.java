package trabajoFinal.test;

import org.junit.jupiter.api.Test;
import trabajoFinal.enums.EstadoVehiculo;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.modelo.Bicicleta;
import trabajoFinal.modelo.Carro;
import trabajoFinal.modelo.Motocicleta;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {

    @Test
    public void carro_debeCrearseConTipoCorrecto() {
        Carro carro = new Carro("ABC123", "Juan Pérez", "1098765432");

        assertEquals("ABC123", carro.getPlaca());
        assertEquals(TipoVehiculo.CARRO, carro.getTipoVehiculo());
        assertEquals("Juan Pérez", carro.getNombreConductor());
        assertEquals("Automóvil", carro.getTipoDescripcion());
    }

    @Test
    public void placa_debeAlmacenarseEnMayusculas() {
        Carro carro = new Carro("abc123", "Ana López", "123456789");

        assertEquals("ABC123", carro.getPlaca());
    }

    @Test
    public void motocicleta_debeCrearseConCilindraje() {
        Motocicleta moto = new Motocicleta("ZXY987", "Carlos Ruiz", "987654321", 150);

        assertEquals(TipoVehiculo.MOTOCICLETA, moto.getTipoVehiculo());
        assertEquals(150, moto.getCilindraje());
        assertEquals("Motocicleta", moto.getTipoDescripcion());
    }

    @Test
    public void bicicleta_debeCrearseConTipoCorrecto() {
        Bicicleta bici = new Bicicleta("BCI001", "María García", "555444333");

        assertEquals(TipoVehiculo.BICICLETA, bici.getTipoVehiculo());
        assertEquals("Bicicleta", bici.getTipoDescripcion());
    }

    @Test
    public void vehiculo_estadoInicialDebeSerDentro() {
        Carro carro = new Carro("DEF456", "Luis Torres", "111222333");

        assertEquals(EstadoVehiculo.DENTRO, carro.getEstado());
    }

    @Test
    public void vehiculo_debePermitirCambiarEstado() {
        Carro carro = new Carro("GHI789", "Rosa Mora", "444555666");
        carro.setEstado(EstadoVehiculo.SALIO);

        assertEquals(EstadoVehiculo.SALIO, carro.getEstado());
    }
}
