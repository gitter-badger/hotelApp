package com.springapp.mvc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * Created by vsantos on 03/11/2016.
 */

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Habitacion {
    private String id;
    private String nombre;
    private int precioReferencial;
    private int size;
    private String ubicacion;
    private int maximoPersonas;
    private String tipo;
    private String descripcion;
    private String observacion;
    private List<Parametricas> caracteristicas;
    private String usuarioCreacion;
    private Date fechaCreacion;
    private String usuarioModificacion;
    private Date fechaModificacion;

    public Habitacion() {
    }

    public Habitacion(String id, String nombre, int precioReferencial, int size, String ubicacion, int maximoPersonas, String tipo, String descripcion, String observacion, List<Parametricas> caracteristicas, String usuarioCreacion, Date fechaCreacion, String usuarioModificacion, Date fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.precioReferencial = precioReferencial;
        this.size = size;
        this.ubicacion = ubicacion;
        this.maximoPersonas = maximoPersonas;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.caracteristicas = caracteristicas;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecioReferencial() {
        return precioReferencial;
    }

    public void setPrecioReferencial(int precioReferencial) {
        this.precioReferencial = precioReferencial;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getMaximoPersonas() {
        return maximoPersonas;
    }

    public void setMaximoPersonas(int maximoPersonas) {
        this.maximoPersonas = maximoPersonas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<Parametricas> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Parametricas> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}

