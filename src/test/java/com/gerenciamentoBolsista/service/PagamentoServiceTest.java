package com.gerenciamentoBolsista.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.gerenciamentoBolsista.dto.PagamentoDTO;
import com.gerenciamentoBolsista.entity.Pagamento;
import com.gerenciamentoBolsista.repository.PagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;
    
    @Mock
    private EntityManager entityManager;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PagamentoService pagamentoService;

    private Pagamento pagamento;
    private PagamentoDTO pagamentoDTO;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setValor(BigDecimal.valueOf(1000.0));
        pagamento.setBolsistaId(1L);

        pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(1L);
        pagamentoDTO.setValor(BigDecimal.valueOf(1000.0));
        pagamentoDTO.setBolsistaId(1L);
    }

    @Test
    public void testListarTodos() {
        List<Pagamento> listaPagamentos = new ArrayList<>();
        listaPagamentos.add(pagamento);

        when(pagamentoRepository.findAll()).thenReturn(listaPagamentos);
        when(modelMapper.map(pagamento, PagamentoDTO.class)).thenReturn(pagamentoDTO);

        List<PagamentoDTO> resultado = pagamentoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(pagamentoDTO, resultado.get(0));
    }

    @Test
    public void testFindById() {
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(pagamento));
        when(modelMapper.map(pagamento, PagamentoDTO.class)).thenReturn(pagamentoDTO);

        PagamentoDTO resultado = pagamentoService.findById(1L);

        assertNotNull(resultado);
        assertEquals(pagamentoDTO, resultado);
    }

    @Test
    public void testSalvar() {
        when(modelMapper.map(pagamentoDTO, Pagamento.class)).thenReturn(pagamento);
        when(pagamentoRepository.save(pagamento)).thenReturn(pagamento);
        when(modelMapper.map(pagamento, PagamentoDTO.class)).thenReturn(pagamentoDTO);

        PagamentoDTO resultado = pagamentoService.salvar(pagamentoDTO);

        assertNotNull(resultado);
        assertEquals(pagamentoDTO, resultado);
    }

    @Test
    public void testFindAllByIds() {
        List<Pagamento> listaPagamentos = new ArrayList<>();
        listaPagamentos.add(pagamento);

        TypedQuery<Pagamento> typedQuery = (TypedQuery<Pagamento>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT p FROM Pagamento p WHERE 1=1 AND p.id = :idPagamento AND p.bolsistaId = :idBolsista", Pagamento.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("idPagamento", 1L)).thenReturn(typedQuery);
        when(typedQuery.setParameter("idBolsista", 1L)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(listaPagamentos);
        when(modelMapper.map(pagamento, PagamentoDTO.class)).thenReturn(pagamentoDTO);

        List<PagamentoDTO> resultado = pagamentoService.findAll(1L, 1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(pagamentoDTO, resultado.get(0));
    }


    @Test
    public void testRemover() {
        pagamentoService.remover(1L);
        verify(pagamentoRepository).deleteById(1L);
    }
        
    @Test
    public void testFindAllSemResultados() {
        List<Pagamento> listaVazia = new ArrayList<>();

        TypedQuery<Pagamento> typedQuery = (TypedQuery<Pagamento>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT p FROM Pagamento p WHERE 1=1 AND p.id = :idPagamento AND p.bolsistaId = :idBolsista", Pagamento.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("idPagamento", 1L)).thenReturn(typedQuery);
        when(typedQuery.setParameter("idBolsista", 1L)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(listaVazia);

        List<PagamentoDTO> resultado = pagamentoService.findAll(1L, 1L);

        assertNotNull(resultado);
        assertEquals(0, resultado.size()); 
    }
    
    @Test
    public void testFindAllComErroNoBancoDeDados() {
        when(entityManager.createQuery("SELECT p FROM Pagamento p WHERE 1=1 AND p.id = :idPagamento AND p.bolsistaId = :idBolsista", Pagamento.class)).thenThrow(new RuntimeException("Erro no banco de dados"));

        assertThrows(RuntimeException.class, () -> {
            pagamentoService.findAll(1L, 1L);
        });
    }

}