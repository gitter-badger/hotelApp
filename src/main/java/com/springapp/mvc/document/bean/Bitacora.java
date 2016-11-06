package com.springapp.mvc.document.bean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: esalamanca
 * Date: 03/02/2016
 * Time: 04:17 PM
 * .
 */
public class Bitacora {

    private String tipoDocumento;
    private String numeroDocumento;
    private String gestion;
    private String codigoAduana;
    private String nitDeclarante;

    private List<Estados> estado;

    public Bitacora() {
    }

    public Bitacora(String tipoDocumento, String numeroDocumento, String gestion, String codigoAduana, String nitDeclarante,
                    String estado, String operacion, String usuario, String nitExportador, String tecnico, Integer version) {

        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.gestion = gestion;
        this.codigoAduana = codigoAduana;
        this.nitDeclarante = nitDeclarante;
        if (this.estado == null) {
            this.estado = new ArrayList<Estados>();
        }

        Estados est = new Estados(estado, operacion, usuario, nitExportador, tecnico, version);
        this.estado.add(est);
    }

    public Bitacora(String estado, String operacion, String usuario, String nitExportador, String tecnico, Integer version) {

        if (this.estado == null) {
            this.estado = new ArrayList<Estados>();
        }
        Estados est = new Estados(estado, operacion, usuario, nitExportador, tecnico, version);
        this.estado.add(est);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public String getCodigoAduana() {
        return codigoAduana;
    }

    public void setCodigoAduana(String codigoAduana) {
        this.codigoAduana = codigoAduana;
    }

    public String getNitDeclarante() {
        return nitDeclarante;
    }

    public void setNitDeclarante(String nitDeclarante) {
        this.nitDeclarante = nitDeclarante;
    }

    public List<Estados> getEstado() {
        return estado;
    }

    public void setEstado(List<Estados> estado) {
        this.estado = estado;
    }

    public void addEstado(String estado, String operacion, String usuario, String nitExportador, String tecnico, Integer version) {
        if (this.estado == null) {
            this.estado = new ArrayList<Estados>();
        }

        this.estado.add(new Estados(estado, operacion, usuario, nitExportador,  tecnico, version));
    }

    @Override
    public String toString() {
        return "Bitacora{" +
                "tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", gestion='" + gestion + '\'' +
                ", codigoAduana='" + codigoAduana + '\'' +
                ", nitDeclarante='" + nitDeclarante + '\'' +
                ", estado=" + estado +
                '}';
    }
}
