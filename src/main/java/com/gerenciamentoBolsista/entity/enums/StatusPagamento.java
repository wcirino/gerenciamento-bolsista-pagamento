package com.gerenciamentoBolsista.entity.enums;

public enum StatusPagamento {
    SOLICITADO(1, "Solicitado"),
    PAGO(2, "Pago"),
    NAO_REALIZADO(3, "Não Realizado"),
    CANCELADO(4, "Cancelado");

    private final int codigo;
    private final String nome;

    StatusPagamento(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public static StatusPagamento getByCodigo(int codigo) {
        for (StatusPagamento status : values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }
}