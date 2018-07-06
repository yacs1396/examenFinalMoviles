package pe.edu.pucp.examen2.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String name;
    private String alias;
    private List<Double> posicion;

    public Usuario(String name, String alias, Double x, Double y){
        this.name = name;
        this.alias = alias;
        posicion = new ArrayList<Double>();
        posicion.add(x);
        posicion.add(y);
    }

    public Usuario(){
        posicion = new ArrayList<Double>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Double> getPosicion() {
        return posicion;
    }

    public void setPosicion(List<Double> posicion) {
        this.posicion = posicion;
    }

//    public Double getX(){
//        return posicion.get(0);
//    }
//
//    public Double getY(){
//        return posicion.get(1);
//    }
}
