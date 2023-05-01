package negocio;

import entity.Equipo;
import entity.Ronda;

public class PartidoNoEncontradoException extends Throwable {
    public PartidoNoEncontradoException(Equipo equipo1, Equipo equipo2, Ronda ronda) {
    }
}
