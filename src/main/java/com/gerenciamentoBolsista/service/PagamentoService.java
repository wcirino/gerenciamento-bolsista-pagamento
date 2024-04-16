package com.gerenciamentoBolsista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gerenciamentoBolsista.dto.PagamentoDTO;
import com.gerenciamentoBolsista.entity.Pagamento;
import com.gerenciamentoBolsista.repository.PagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public List<PagamentoDTO> listarTodos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PagamentoDTO findById(Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoRepository.findById(id);
        return pagamentoOptional.map(this::convertToDto).orElse(null);
    }

    public PagamentoDTO salvar(PagamentoDTO pagamentoDTO) {
        Pagamento pagamento = convertToEntity(pagamentoDTO);
        Pagamento savedPagamento = pagamentoRepository.save(pagamento);
        return convertToDto(savedPagamento);
    }
    
    public List<PagamentoDTO> findAll(Long idPagamento, Long idBolsista) {
        StringBuilder hqlQuery = new StringBuilder("SELECT p FROM Pagamento p WHERE 1=1");

        if (idPagamento != null) {
            hqlQuery.append(" AND p.id = :idPagamento");
        }
        if (idBolsista != null) {
            hqlQuery.append(" AND p.bolsistaId = :idBolsista");
        }

        Query query = entityManager.createQuery(hqlQuery.toString(), Pagamento.class);

        if (idPagamento != null) {
            query.setParameter("idPagamento", idPagamento);
        }
        if (idBolsista != null) {
            query.setParameter("idBolsista", idBolsista);
        }

        @SuppressWarnings("unchecked")
        List<Pagamento> pagamentos = query.getResultList();

        return pagamentos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void remover(Long id) {
        pagamentoRepository.deleteById(id);
    }

    private PagamentoDTO convertToDto(Pagamento pagamento) {
        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    private Pagamento convertToEntity(PagamentoDTO pagamentoDTO) {
        return modelMapper.map(pagamentoDTO, Pagamento.class);
    }
}
