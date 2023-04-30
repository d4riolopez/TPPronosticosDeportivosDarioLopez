package entity;

public class Equipo {

    //atributos nombre y descripcion de la clase Equipo
    private String nombre;
    private String descripcion;

    public Equipo(String nombre) {//constructor de la clase equipo
        this.nombre = nombre;
    }
    //getters y setters de los atributos de la clase
    public String getNombre() { //devuelve nombre al metod que lo pidio
        return nombre;
    }

    public String getDescripcion() {//asignaun nombre al atributo de la clase
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}