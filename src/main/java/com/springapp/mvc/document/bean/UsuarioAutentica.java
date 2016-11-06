package com.springapp.mvc.document.bean;

/**
 * Created with IntelliJ IDEA.
 * User: mcalla
 * Date: 19/04/16
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class UsuarioAutentica {

    private String nombreUsuario;
    private String password;

    public enum Tipo {
        INTERNO, EXTERNO
    }

    private Tipo tipo;

    /**
     *
     */
    public UsuarioAutentica() {
    }

    /**
     * @param nombreUsuario
     * @param password
     * @param tipo
     */
    public UsuarioAutentica(String nombreUsuario, String password, Tipo tipo) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.tipo = tipo;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", password="
                + password + ", tipo=" + tipo + "]";
    }

}
