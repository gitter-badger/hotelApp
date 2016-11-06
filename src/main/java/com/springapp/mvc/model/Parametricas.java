package com.springapp.mvc.model;

/**
 * Created by vsantos on 03/11/2016.
 */
public class Parametricas {
    private int id;
    private String nombre;
    private String observacion;

    public Parametricas() {

    }

    public Parametricas(int id, String nombre, String observacion) {
        this.id = id;
        this.nombre = nombre;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
