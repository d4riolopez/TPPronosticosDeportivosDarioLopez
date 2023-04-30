package entity;

//clase pronostico
public class Pronostico {
    private Partido partido;// referencia a partido
    private Equipo equipo;//referencia a equipo
    private EnumResultado resultado;//referencia a enumResultado

    //constructor con parametros partido, equipo y resultado le asigna a los atributos de la clase pronostico
    public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
        super();
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    //getters  de partido  equipo y resultado
    public Partido getPartido() {
        return this.partido;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public EnumResultado getResultado() {
        return this.resultado;
    }

    //metodo puntos  asigna al equipo que paso por parametros el resultado que toma de la puesta
    // con el resultado real del partido
    public int puntos() {
        // this.resultado -> pred
        EnumResultado resultadoReal = this.partido.resultado(
                this.equipo);
        if (this.resultado.equals(resultadoReal)) {
            return 1;
        } else {
            return 0;
        }

    }
}