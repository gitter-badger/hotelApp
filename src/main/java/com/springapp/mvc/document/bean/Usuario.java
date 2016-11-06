package com.springapp.mvc.document.bean;

public class Usuario {

    private String nombreUsuario;

    private String nombre;

    private String paterno;

    private String materno;

    private String email;

    /**
     * Proxy
     */
    public Usuario() {
    }

    /**
     * Parametrico
     *
     * @param nombreUsuario
     * @param nombre
     * @param paterno
     * @param materno
     * @param email
     */
    public Usuario(String nombreUsuario, String nombre, String paterno,
                   String materno, String email) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
