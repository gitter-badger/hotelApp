package com.springapp.mvc.document;

import  com.springapp.mvc.document.bean.*;

import java.util.Date;

/**
 *
 * @param <T>
 */
public class DocumentoVersion<T> {

    private VersionId id;

    private FormularioHeader header;

    private T data;

    private FormularioSecurity security;

    private Bitacora bitacora;

    /**
     * Proxy
     */
    public DocumentoVersion() {
    }

    /**
     * Constructor parametrico
     *
     * @param id                 Objeto ID que contiene el id del doc original y el numero de version
     * @param header             Cabecera del documento mongo
     * @param data               Data especifica del negocio
     * @param payload            Payload de revisiones y otros
     * @param security           Datos de seguridad
     * @param bitacora           Lista de eventos que cambian el estado del documento
     */
    public DocumentoVersion(
            VersionId id,
            FormularioHeader header,
            T data,
            FormularioPayload payload,
            FormularioSecurity security,
            Bitacora bitacora
    ) {
        this.id = id;
        this.header = header;
        this.data = data;
        this.security = security;
        this.bitacora = bitacora;
    }

    /**
     * Crea una instancia de esta clase con propiedades iguales a las de una instancia dada de la clase Documento
     *
     * @param documento Documento del cual se copian las propiedades
     */
    public static DocumentoVersion mergeFromDocument(Documento documento) {

        DocumentoVersion version = new DocumentoVersion();

        version.id = new VersionId(documento.getId(), documento.getHeader().getVersion());
        version.header = documento.getHeader();
        version.data = documento.getData();
        version.security = documento.getSecurity();
        version.bitacora = documento.getBitacora();

        //La fecha de creacion de la version es la fecha de hoy
        if (version.security != null) {
            version.security.setCreacionFecha(new Date());
        } else {
            version.security = new FormularioSecurity(null, new Date());
        }

        return version;
    }

    public VersionId getId() {
        return id;
    }

    public void setId(VersionId id) {
        this.id = id;
    }

    public FormularioHeader getHeader() {
        return header;
    }

    public void setHeader(FormularioHeader header) {
        this.header = header;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public FormularioSecurity getSecurity() {
        return security;
    }

    public void setSecurity(FormularioSecurity security) {
        this.security = security;
    }

    public Bitacora getBitacora() {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    @Override
    public String toString() {
        return "DocumentoVersion{" +
                "id=" + id +
                ", header=" + header +
                ", data=" + data +
                ", security=" + security +
                ", bitacora=" + bitacora +
                '}';
    }
}
