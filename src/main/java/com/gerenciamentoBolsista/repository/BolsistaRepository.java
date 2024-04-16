package com.gerenciamentoBolsista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciamentoBolsista.entity.Bolsista;

import jakarta.transaction.Transactional;

@Repository
public interface BolsistaRepository  extends JpaRepository<Bolsista, Long> {

	@Modifying
    @Transactional
    @Query("UPDATE Bolsista b SET b.status = false WHERE b.id = :id")
    void desativarBolsista(@Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE bolsista SET nome_completo = :nomeCompleto, " +
	        "tipo_identificador = :tipoIdentificador, numero_identificador = :numeroIdentificador, " +
	        "codigo_banco = :codigoBanco, numero_agencia = :numeroAgencia, numero_conta = :numeroConta " +
	        "WHERE id = :id", nativeQuery = true)
	void updateBolsista(@Param("id") Long id, @Param("nomeCompleto") String nomeCompleto,
	                    @Param("tipoIdentificador") Integer tipoIdentificador, @Param("numeroIdentificador") String numeroIdentificador,
	                    @Param("codigoBanco") String codigoBanco, @Param("numeroAgencia") String numeroAgencia,
	                    @Param("numeroConta") String numeroConta);

}
