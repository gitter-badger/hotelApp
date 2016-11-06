package com.springapp.mvc.document.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by ralbarracin on 10/02/2016.
 */
public class Estados {

    private String estado;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm", timezone="America/La_Paz")
    private Date fecha;
    private String operacion;
    private String usuario;
    private String nitExportador;
    private String tecnico;
    private Integer version;

    public Estados() {
    }

    public Estados(String estado, String operacion, String usuario, String nitExportador, String tecnico, Integer version) {
        this.estado = estado;
        this.fecha = new Date();
        this.operacion = operacion;
        this.usuario = usuario;
        this.nitExportador = nitExportador;
        this.tecnico = tecnico;
        this.version = version;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNitExportador() {
        return nitExportador;
    }

    public void setNitExportador(String nitExportador) {
        this.nitExportador = nitExportador;
    }


    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Estados{" +
                "estado='" + estado + '\'' +
                ", fecha=" + fecha +
                ", operacion='" + operacion + '\'' +
                ", usuario='" + usuario + '\'' +
                ", nitExportador='" + nitExportador + '\'' +
                ", tecnico='" + tecnico + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
