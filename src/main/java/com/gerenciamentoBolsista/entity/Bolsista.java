package com.gerenciamentoBolsista.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bolsista")
public class Bolsista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Bolsista(Long id, String nomeCompleto, Integer tipoIdentificador, String numeroIdentificador,
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
    
	public Bolsista() {
		super();
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "tipo_identificador")
    private Integer tipoIdentificador;

    @Column(name = "numero_identificador")
    private String numeroIdentificador;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "codigo_banco")
    private String codigoBanco;

    @Column(name = "numero_agencia")
    private String numeroAgencia;

    @Column(name = "numero_conta")
    private String numeroConta;
    
    @Column(name = "status_bolsista")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
}
