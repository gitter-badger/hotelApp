package com.springapp.mvc.document.bean;

public class FormularioFooter {

    private String hashData;

    /**
     * Proxy
     */
    public FormularioFooter() {
    }

    /**
     * Constructor parametrico
     *
     * @param hashData
     */
    public FormularioFooter(String hashData) {
        this.hashData = hashData;
    }

    public String getHashData() {
        return hashData;
    }

    public void setHashData(String hashData) {
        this.hashData = hashData;
    }

}
