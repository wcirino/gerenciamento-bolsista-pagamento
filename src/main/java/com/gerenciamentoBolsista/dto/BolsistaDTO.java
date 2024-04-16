package com.gerenciamentoBolsista.dto;

import java.time.LocalDate;

public class BolsistaDTO {
	
	
    
	public BolsistaDTO(Long id, String nomeCompleto, Integer tipoIdentificador, String numeroIdentificador,
			LocalDate dataCadastro, String codigoBanco, String numeroAgencia, String numeroConta, Boolean status) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.tipoIdentificador = tipoIdentificador;
		this.numeroIdentificador = numeroIdentificador;
		this.dataCadastro = dataCadastro;
		this.codigoBanco = codigoBanco;
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
		this.status = status;
	}
	
	public BolsistaDTO() {
		super();
	}

	private Long id;
	private String nomeCompleto;
    private Integer tipoIdentificador;
    private String numeroIdentificador;
    private LocalDate dataCadastro;
    private String codigoBanco;
    private String numeroAgencia;
    private String numeroConta;
    private Boolean status;
    
    
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public Integer getTipoIdentificador() {
		return tipoIdentificador;
	}
	public void setTipoIdentificador(Integer tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}
	public String getNumeroIdentificador() {
		return numeroIdentificador;
	}
	public void setNumeroIdentificador(String numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public String getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

    
}
