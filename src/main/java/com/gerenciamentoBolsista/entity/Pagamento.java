package com.gerenciamentoBolsista.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.gerenciamentoBolsista.entity.enums.StatusPagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagamento")
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bolsista_id")
    private Long bolsistaId;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "banco")
    private String banco;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "conta")
    private String conta;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "status")
    private StatusPagamento status;

    public Pagamento(Long id, Long bolsistaId, LocalDate dataPagamento, String banco, String agencia, String conta,
            BigDecimal valor, StatusPagamento status) {
        super();
        this.id = id;
        this.bolsistaId = bolsistaId;
        this.dataPagamento = dataPagamento;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.valor = valor;
        this.status = status;
    }

    public Pagamento() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBolsistaId() {
        return bolsistaId;
    }

    public void setBolsistaId(Long bolsistaId) {
        this.bolsistaId = bolsistaId;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

}
