package com.springapp.mvc.document.bean;

import java.util.Date;

public class ArchivoFirmado {

    private Usuario usuario;
    private String idCmis;
    private String hash;
    private Date fecha;
    private String clavePublica;

    /**
     * Proxy
     */
    public ArchivoFirmado() {
    }

    /**
     * Constructor
     *
     * @param usuario
     * @param idCmis
     * @param hash
     * @param fecha
     * @param clavePublica
     */
    public ArchivoFirmado(Usuario usuario, String idCmis, String hash,
                          Date fecha, String clavePublica) {
        this.usuario = usuario;
        this.idCmis = idCmis;
        this.hash = hash;
        this.fecha = fecha;
        this.clavePublica = clavePublica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIdCmis() {
        return idCmis;
    }

    public void setIdCmis(String idCmis) {
        this.idCmis = idCmis;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getClavePublica() {
        return clavePublica;
    }

    public void setClavePublica(String clavePublica) {
        this.clavePublica = clavePublica;
    }

}
