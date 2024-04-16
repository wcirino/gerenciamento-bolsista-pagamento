package com.gerenciamentoBolsista.entity.enums;

public enum TipoIdentificador {
    CPF(1, "CPF"),
    CNH(2, "CNH"),
    PASSAPORTE(3, "PASSAPORTE");

    private final int codigo;
    private final String identificador;

    TipoIdentificador(int codigo, String identificador) {
        this.codigo = codigo;
        this.identificador = identificador;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getIdentificador() {
        return identificador;
    }
}

