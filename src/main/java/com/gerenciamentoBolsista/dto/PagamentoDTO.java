package com.gerenciamentoBolsista.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long bolsistaId;
	private LocalDate dataPagamento;
	private String banco;
	private String agencia;
	private String conta;
	private BigDecimal valor;
	private String status;
	
		
	public PagamentoDTO(Long id, Long bolsistaId, LocalDate dataPagamento, String banco, String agencia, String conta,
			BigDecimal valor, String status) {
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

	public Long getBolsistaId() {
		return bolsistaId;
	}

	public void setBolsistaId(Long bolsistaId) {
		this.bolsistaId = bolsistaId;
	}

	public PagamentoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
