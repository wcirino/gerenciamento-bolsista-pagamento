package com.gerenciamentoBolsista.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gerenciamentoBolsista.dto.PagamentoDTO;
import com.gerenciamentoBolsista.service.PagamentoService;

public class PagamentoControllerTest {

    private PagamentoController pagamentoController;
    private PagamentoService pagamentoService;

    @BeforeEach
    public void setup() {
        pagamentoService = mock(PagamentoService.class);
        pagamentoController = new PagamentoController();
        pagamentoController.service = pagamentoService;
    }

    @Test
    public void testListarPagamentos() {
        List<PagamentoDTO> pagamentos = new ArrayList<>();
        pagamentos.add(new PagamentoDTO(1L, 1L, null, "Banco1", "Agencia1", "Conta1", null, "Pendente"));
        pagamentos.add(new PagamentoDTO(2L, 2L, null, "Banco2", "Agencia2", "Conta2", null, "Concluído"));
        when(pagamentoService.listarTodos()).thenReturn(pagamentos);
        
        ResponseEntity<List<PagamentoDTO>> response = pagamentoController.listarPagamentos();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagamentos, response.getBody());
    }

    @Test
    public void testAdicionarPagamento() {
        PagamentoDTO novoPagamento = new PagamentoDTO(null, 1L, null, "Banco1", "Agencia1", "Conta1", null, "Pendente");
        PagamentoDTO novoPagamentoSalvo = new PagamentoDTO(1L, 1L, null, "Banco1", "Agencia1", "Conta1", null, "Pendente");
        when(pagamentoService.salvar(any(PagamentoDTO.class))).thenReturn(novoPagamentoSalvo);
        
        ResponseEntity<PagamentoDTO> response = pagamentoController.adicionarPagamento(novoPagamento);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(novoPagamentoSalvo, response.getBody());
    }

    @Test
    public void testEncontrarPagamentoPorId() {
        Long id = 1L;
        PagamentoDTO pagamento = new PagamentoDTO(id, 1L, null, "Banco1", "Agencia1", "Conta1", null, "Pendente");
        when(pagamentoService.findById(id)).thenReturn(pagamento);
        
        ResponseEntity<PagamentoDTO> response = pagamentoController.encontrarPagamentoPorId(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pagamento, response.getBody());
    }

}