package presentacion;

import entity.*;
import negocio.ArchResultadoException;
import negocio.FixtureCsv;
import negocio.Jugador;
import negocio.PartidoNoEncontradoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainPronosticos {
    public static void main(String[] args) throws PartidoNoEncontradoException {

           FixtureCsv fixture = new FixtureCsv(args[0]);

            try {
                fixture.cargar();
            } catch (ArchResultadoException e) {
                System.out.println("No se pudo leer el archivo de resultados: ");
                        //+ e.getArchivoCsv());
                System.out.println(e.getMessage());
                System.exit(1);
            }

            // Leer pronostico
            Map<String,Jugador> jugadores = new HashMap<>();

            Path pathPronostico = Paths.get(args[1]);
            List<String> lineasPronostico = null;
            try {
                lineasPronostico = Files.readAllLines(pathPronostico);
            } catch (IOException e) {
                System.out.println("No se pudo leer la linea de pronosticos...");
                System.out.println(e.getMessage());
                System.exit(1);
            }
            boolean primera = true;
            for (String lineaPronostico : lineasPronostico) {
                if (primera) {
                    primera = false;
                } else {
                    String[] campos = lineaPronostico.split(",");
                    Equipo equipo1 = new Equipo(campos[0]);
                    Equipo equipo2 = new Equipo(campos[4]);
                    Partido partido = fixture.partidoDe(equipo1,equipo2);


                    Equipo equipo = null;
                    EnumResultado resultado = null;
                    if("X".equals(campos[1])) {
                        equipo = equipo1;
                        resultado = EnumResultado.GANADOR;
                    }
                    if("X".equals(campos[2])) {
                        equipo = equipo1;
                        resultado = EnumResultado.EMPATE;
                    }
                    if("X".equals(campos[3])) {
                        equipo = equipo1;
                        resultado = EnumResultado.PERDEDOR;
                    }
                    Pronostico pronostico = new Pronostico(partido, equipo, resultado);
                    // sumar los puntos correspondientes
                    String nombreJugador =campos[5];

                    Jugador jugador = null;
                    if(jugadores.containsKey(nombreJugador)) {
                        jugador = jugadores.get(nombreJugador);

                    } else {
                        jugador = new Jugador(nombreJugador);
                        jugadores.put(nombreJugador, jugador);
                    }
                    jugador.addPronostico(pronostico);


                }
            }

            // Calcular bonus por ronda
            for (String nombreJugador : jugadores.keySet()) {
                for (Ronda ronda : fixture.rondas()) {
                    Jugador jugador = jugadores.get(nombreJugador);
                    List<Pronostico> apuestas = jugador.getPronosticos();
                    boolean cumplioRonda = ronda.acertoTodos(apuestas);
                    if(cumplioRonda) {
                        jugador.incPuntosPorBonus(2);
                    }
                }
            }


            // mostrar los puntos
            for (String jugador : jugadores.keySet()) {

                System.out.println("Los puntos obtenidos por el jugador " + jugador +
                        " fueron:");
                System.out.println(jugadores.get(jugador).puntosTotales());
            }


        }

    }

