package com.springapp.mvc.document.bean;

public class Formulario {

    private String codigo;

    private String descripcion;

    private Integer version;

    /**
     * Proxy
     */
    public Formulario() {
    }

    /**
     * Constructor parametrico
     *
     * @param codigo
     * @param descripcion
     * @param version
     */
    public Formulario(String codigo, String descripcion, Integer version) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.version = version;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
