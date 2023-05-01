package negocio;

import entity.Equipo;
import entity.Partido;
import entity.Ronda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Resultado {
    private String archivoCsv;

    private Collection<Partido> partidos ;
    private Map<Integer, Ronda> rondas;

    public Resultado(String pathCompleto) {
        super();
        this.archivoCsv = pathCompleto;
        this.partidos = new ArrayList<Partido>();
        this.rondas = new HashMap<Integer, Ronda>();
    }

    /**
     * @param campos
     * @return
     */
    // Argentina,1,2,Arabia Saudita,1
    private Partido crearPartidoDeApuesta(String[] campos) {
        Equipo equipo1 = new Equipo(campos[0]);
        Equipo equipo2 = new Equipo(campos[3]);
        Partido partido = new Partido(equipo1, equipo2);
        partido.setGolesEq1(Integer.parseInt(campos[1]));
        partido.setGolesEq2(Integer.parseInt(campos[2]));
        Ronda ronda = new Ronda(0);
        ronda.setNumero(Integer.parseInt(campos[4]));

        return partido;
    }

    public void cargar() throws ArchResultadoException {
        Path pathResultados = Paths.get(this.archivoCsv);
        List<String> lineasResultados = null;
        try {
            lineasResultados = Files.readAllLines(pathResultados);
        } catch (IOException e) {
            throw new ArchResultadoException();//this.archivoCsv);
        }
        boolean primera = true;
        for (String lineaResultado : lineasResultados) {
            if (primera) {
                primera = false;
            } else {
                // Argentina,1,2,Arabia Saudita,4
                String[] campos = lineaResultado.split(",");
                Partido partido = crearPartidoDeApuesta(campos);
                int nroRonda = Integer.parseInt(campos[4]);
                Ronda ronda = null;
                if( ! rondas.containsKey(nroRonda)) {
                    ronda = new Ronda(nroRonda);
                    rondas.put(nroRonda, ronda);
                } else {
                    ronda = rondas.get(nroRonda);
                }

                ronda.addPartido(partido);
                partido.setRonda(ronda);
                partidos.add(partido);
            }
        }
    }

    public Partido partidoDeApuesta(Equipo equipo1, Equipo equipo2)
            throws PartidoNoEncontradoException {

        for (Partido partidoCol : partidos) {
            if (partidoCol.getEquipo1().getNombre(
            ).equals(equipo1.getNombre())
                    && partidoCol.getEquipo2().getNombre(
            ).equals(equipo2.getNombre())) {

                return partidoCol;

            }
        }
        throw new PartidoNoEncontradoException(equipo1,equipo2);
    }

    public Collection<Ronda> rondas() {

        return rondas.values();
    }
}
