package com.springapp.mvc.utils;

public enum DocumentoEstado {

    ACTUAL(0), TEMPORAL(1), HISTORICO(2), DISABLED(3);

    private DocumentoEstado(int estado) {
    }
}
