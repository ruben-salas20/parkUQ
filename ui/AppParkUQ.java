package trabajoFinal.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import trabajoFinal.enums.RolSistema;
import trabajoFinal.enums.TipoEspacio;
import trabajoFinal.enums.TipoVehiculo;
import trabajoFinal.modelo.EspacioParqueadero;
import trabajoFinal.modelo.Tarifa;
import trabajoFinal.modelo.UsuarioSistema;
import trabajoFinal.servicio.Parqueadero;
import trabajoFinal.ui.controlador.LoginControlador;

import java.io.IOException;

public class AppParkUQ extends Application {

    private static Parqueadero parqueadero;

    @Override
    public void start(Stage stage) throws IOException {
        parqueadero = inicializarParqueadero();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("vista/login.fxml"));
        Scene escena = new Scene(loader.load(), 400, 300);

        LoginControlador controlador = loader.getController();
        controlador.setParqueadero(parqueadero);
        controlador.setStage(stage);

        stage.setTitle("ParkUQ — Sistema de Gestión de Parqueadero");
        stage.setScene(escena);
        stage.setResizable(false);
        stage.show();
    }

    private Parqueadero inicializarParqueadero() {
        Parqueadero p = new Parqueadero("Parqueadero Central UQ");

        // Espacios para carros
        for (int i = 1; i <= 10; i++) {
            p.agregarEspacio(new EspacioParqueadero("C-" + String.format("%02d", i), TipoEspacio.CARRO));
        }
        // Espacios para motocicletas
        for (int i = 1; i <= 10; i++) {
            p.agregarEspacio(new EspacioParqueadero("M-" + String.format("%02d", i), TipoEspacio.MOTOCICLETA));
        }
        // Espacios para bicicletas
        for (int i = 1; i <= 5; i++) {
            p.agregarEspacio(new EspacioParqueadero("B-" + String.format("%02d", i), TipoEspacio.BICICLETA));
        }

        // Tarifas por defecto (por hora)
        p.agregarTarifa(new Tarifa(TipoVehiculo.CARRO, 3000.0));
        p.agregarTarifa(new Tarifa(TipoVehiculo.MOTOCICLETA, 2000.0));
        p.agregarTarifa(new Tarifa(TipoVehiculo.BICICLETA, 500.0));

        // Usuarios del sistema
        p.agregarUsuarioSistema(new UsuarioSistema("operador", "1234", RolSistema.OPERADOR));
        p.agregarUsuarioSistema(new UsuarioSistema("admin", "admin", RolSistema.ADMINISTRADOR));

        return p;
    }

    public static Parqueadero getParqueadero() {
        return parqueadero;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
