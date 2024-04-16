package com.gerenciamentoBolsista.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciamentoBolsista.entity.Pagamento;
import com.gerenciamentoBolsista.entity.enums.StatusPagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	boolean existsByBolsistaIdAndStatusIn(Long id, List<StatusPagamento> asList);

}
