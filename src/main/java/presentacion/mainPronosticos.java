package presentacion;

import entity.*;
import negocio.Apostador;
import negocio.ArchResultadoException;
import negocio.Resultado;
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

            // Leer partidos reales en archivo resultados.csv
        Resultado resultado = new Resultado(args[0]);

            try {
                resultado.cargar();
            } catch (ArchResultadoException e) {
                System.out.println("No se pudo leer el archivo de resultados: ");

                System.out.println(e.getMessage());
                System.exit(1);
            }

            // Leer pronostico del apostador po el archivo pronostico.csv
            Map<String, Apostador> apostadores = new HashMap<>();
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
                    Ronda ronda = new Ronda(campos[5]);
                    Partido partido = resultado.partidoDeApuesta(equipo1,equipo2,ronda);

                    Equipo equipo = null;
                    EnumResultado resultadoPartido = null;
                    if("X".equals(campos[1])) {
                        equipo = equipo1;
                        resultadoPartido = EnumResultado.GANADOR;
                    }
                    if("X".equals(campos[2])) {
                        equipo = equipo1;
                        resultadoPartido = EnumResultado.EMPATE;
                    }
                    if("X".equals(campos[3])) {
                        equipo = equipo1;
                        resultadoPartido = EnumResultado.PERDEDOR;
                    }
                    Pronostico pronostico = new Pronostico(partido, equipo, resultadoPartido);
                    // sumar los puntos correspondientes
                    String nombreApostador =campos[5];

                    Apostador apostador = null;
                    if(apostadores.containsKey(nombreApostador)) {
                        apostador = apostadores.get(nombreApostador);

                    } else {
                        apostador = new Apostador(nombreApostador);
                        apostadores.put(nombreApostador, apostador);
                    }
                    apostador.addPronostico(pronostico);


                }
            }

            // Calcular bonus por ronda
            for (String nombreApostador : apostadores.keySet()) {
                for (Ronda ronda : resultado.rondas()) {
                    Apostador apostador = apostadores.get(nombreApostador);
                    List<Pronostico> apuestas = apostador.getPronosticos();
                    boolean cumplioRonda = ronda.acertoTodos(apuestas);
                    if(cumplioRonda) {
                        apostador.incPuntosPorBonus(2);
                    }
                }
            }


            // mostrar los puntos obtenidos por el apostador
            for (String apostador : apostadores.keySet()) {

                System.out.println("Los puntos obtenidos por el apostador " + apostador +
                        " fueron:");
                System.out.println(apostadores.get(apostador).puntosTotales());
            }

        }

    }

