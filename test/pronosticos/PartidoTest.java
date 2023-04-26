package test.pronosticos;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.Main.negocio.EnumResultado;
import java.Main.negocio.Equipo;
import java.Main.negocio.Partido;

public class PartidoTest {

    private Equipo equipo1;
    private Equipo equipo2;
    private Partido partido ;

    @Before
    public void setUp() {
        this.equipo1 = new Equipo("Argentina");
        this.equipo2 = new Equipo("Arabia Saudita");
        this.partido = new Partido(this.equipo1,
                this.equipo2, 1, 2);
    }

    @Test
    public void testPartidoGanadorPerdedor() {

        // nuestro escenario
        this.partido.setGolesEq1(1);
        this.partido.setGolesEq2(2);

        // Procesar
        EnumResultado resultadoObtenido1 = partido.resultado(this.equipo1);
        EnumResultado resultadoObtenido2 = partido.resultado(this.equipo2);

        // Evaluar
        assertEquals(EnumResultado.PERDEDOR, resultadoObtenido1);
        assertEquals(EnumResultado.GANADOR, resultadoObtenido2);

    }

    @Test
    public void testPartidoEmpatado() {

        // nuestro escenario
        // Esta armado

        // Procesar
        EnumResultado resultadoObtenido = partido.resultado(equipo1);

        // Evaluar
        assertEquals(EnumResultado.EMPATE, resultadoObtenido);

    }
}
