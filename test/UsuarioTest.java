package trabajoFinal.test;

import org.junit.jupiter.api.Test;
import trabajoFinal.enums.TipoUsuario;
import trabajoFinal.modelo.Administrativo;
import trabajoFinal.modelo.Docente;
import trabajoFinal.modelo.Estudiante;
import trabajoFinal.modelo.Visitante;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void estudiante_debeRetornarDescuentoDel20Porciento() {
        Estudiante estudiante = new Estudiante("Andrea Ríos", "1098001122");

        assertEquals(0.20, estudiante.getDescuento(), 0.001);
        assertEquals(TipoUsuario.ESTUDIANTE, estudiante.getTipoUsuario());
    }

    @Test
    public void docente_debeRetornarDescuentoDel30Porciento() {
        Docente docente = new Docente("Dr. Martínez", "77665544");

        assertEquals(0.30, docente.getDescuento(), 0.001);
        assertEquals(TipoUsuario.DOCENTE, docente.getTipoUsuario());
    }

    @Test
    public void administrativo_debeRetornarDescuentoDel25Porciento() {
        Administrativo admin = new Administrativo("Sandra López", "33221100");

        assertEquals(0.25, admin.getDescuento(), 0.001);
        assertEquals(TipoUsuario.ADMINISTRATIVO, admin.getTipoUsuario());
    }

    @Test
    public void visitante_debeRetornarDescuentoDel0Porciento() {
        Visitante visitante = new Visitante("Pedro Gómez", "99887766");

        assertEquals(0.0, visitante.getDescuento(), 0.001);
        assertEquals(TipoUsuario.VISITANTE, visitante.getTipoUsuario());
    }

    @Test
    public void usuario_polimorfismo_descuentosDistintosPorSubclase() {
        Estudiante estudiante = new Estudiante("X", "1");
        Docente docente = new Docente("Y", "2");

        assertNotEquals(estudiante.getDescuento(), docente.getDescuento());
        assertTrue(docente.getDescuento() > estudiante.getDescuento());
    }
}
