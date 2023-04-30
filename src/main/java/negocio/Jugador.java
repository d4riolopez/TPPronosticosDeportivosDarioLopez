package negocio;

import entity.Pronostico;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private List<Pronostico> pronosticos;
    private Integer puntosXBonus;

    public Jugador(String nombre) {
        super();
        this.nombre = nombre;
        this.pronosticos = new ArrayList<Pronostico>();
        this.puntosXBonus = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }



    public void addPronostico(Pronostico pronostico){
        this.pronosticos.add(pronostico);
    }



    public int puntosPorPronostico() {
        int puntos = 0;
        for (Pronostico pronostico : pronosticos) {
            puntos += pronostico.puntos();
        }
        return puntos;
    }

    public void incPuntosPorBonus(int i) {
        puntosXBonus += i;
    }

    public int puntosTotales() {
        return this.puntosPorPronostico() + this.puntosXBonus;
    }

}
