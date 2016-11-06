package com.springapp.mvc.document;

import com.springapp.mvc.document.bean.*;

import javax.xml.bind.annotation.XmlAnyElement;
import java.util.ArrayList;

//@XmlRootElement

public class Documento<T> {

    private String id;

    private FormularioHeader header;

    private T data;

    private FormularioSecurity security;

    private Bitacora bitacora;

    /**
     * Proxy
     */
    public Documento() {
    }

    @Deprecated
    public Documento(String id, FormularioHeader header, T data, FormularioSecurity security) {
        this.id = id;
        this.header = header;
        this.data = data;
        this.security = security;
    }

    public Documento(String id, FormularioHeader header, T data, FormularioSecurity security, Bitacora bitacora) {
        this.id = id;
        this.header = header;
        this.data = data;
        this.security = security;
        this.bitacora = bitacora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @XmlAnyElement
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

        if (this.bitacora == null) {
            this.bitacora = bitacora;
        } else {

            if (this.bitacora.getEstado() == null) {
                this.bitacora.setEstado(new ArrayList<Estados>());
            }

            this.bitacora.getEstado().addAll(bitacora.getEstado());
        }
    }

    @Override
    public String toString() {
        return "Documento{" +
                "id='" + id + '\'' +
                ", header=" + header +
                ", data=" + data +
                ", security=" + security +
                ", bitacora=" + bitacora +
                '}';
    }
}
