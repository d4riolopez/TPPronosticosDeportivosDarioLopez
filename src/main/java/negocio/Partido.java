package negocio;
//Clase partido
public class Partido {
    private Equipo equipo1; //atributo referencial a un objeto equipo
    private Equipo equipo2; //tambien lo mismo
    private int golesEq1; //cantidad goles de equipo1
    private int golesEq2;//cantidad goles de equipo2
    private int ronda;//numero de la ronda en el que esta el partido

    public Partido(Equipo equipo1, Equipo equipo2) {//constructor de partido con  equipos 1 y 2
        super();
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }
    //costructor de partido con equipos 1 y 2 y cantidad de goles de equipo 1 y2
    public Partido(Equipo equipo1, Equipo equipo2, int golesEq1, int golesEq2) {
        super();
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEq1 = golesEq1;
        this.golesEq2 = golesEq2;
    }


    //toma ronda para  del partido
    public int getRonda() {
        return ronda;
    }
    //asigna la ronda del partido
    public void setRonda(int ronda) {
        this.ronda = ronda;
    }
    // getter de equipo 1  getters de equipo2
    public Equipo getEquipo1() {
        return equipo1;
    }
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }
    public Equipo getEquipo2() {
        return equipo2;
    }
    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }
    public int getGolesEq1() {
        return golesEq1;
    }
    public void setGolesEq1(int golesEq1) {
        this.golesEq1 = golesEq1;
    }
    public int getGolesEq2() {
        return golesEq2;
    }
    public void setGolesEq2(int golesEq2) {
        this.golesEq2 = golesEq2;
    }
    // metodo resultado de tipo enumResultado le paso un equipo  y compara los goles de los equipos y
    //asigna si es empate, ganador o perdedor
    public EnumResultado resultado(Equipo equipo) {
        if(golesEq1 == golesEq2) {
            return EnumResultado.EMPATE;
        }
        if(equipo.getNombre().equals(equipo1.getNombre())) {
            if(golesEq1>golesEq2) {
                return EnumResultado.GANADOR;
            }	else {
                return EnumResultado.PERDEDOR;
            }
        } else {
            // como equipo no es equipo1, entonces es equipo2
            if(golesEq2>golesEq1) {
                return EnumResultado.GANADOR;
            }	else {
                return EnumResultado.PERDEDOR;
            }
        }

    }
}

