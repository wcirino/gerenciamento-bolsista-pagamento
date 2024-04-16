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

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.service.BolsistaService;

public class BolsistaControllerTest {

    private BolsistaController bolsistaController;
    private BolsistaService bolsistaService;

    @BeforeEach
    public void setup() {
        bolsistaService = mock(BolsistaService.class);
        bolsistaController = new BolsistaController(bolsistaService);
    }

    @Test
    public void testListarBolsistas() {
        List<BolsistaDTO> bolsistas = new ArrayList<>();
        bolsistas.add(new BolsistaDTO(1L, "Nome1", 1, "Email1", null, null, null, null, true));
        bolsistas.add(new BolsistaDTO(2L, "Nome2", 1, "Email2", null, null, null, null, true));
        when(bolsistaService.listarTodos()).thenReturn(bolsistas);
        
        ResponseEntity<List<BolsistaDTO>> response = bolsistaController.listarBolsistas();
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bolsistas, response.getBody());
    }

    @Test
    public void testAdicionarBolsista() {
        BolsistaDTO novoBolsista = new BolsistaDTO(null, "Novo Nome", 1, "novo@email.com", null, null, null, null, true);
        BolsistaDTO novoBolsistaSalvo = new BolsistaDTO(1L, "Novo Nome", 1, "novo@email.com", null, null, null, null, true);
        when(bolsistaService.salvar(any(BolsistaDTO.class))).thenReturn(novoBolsistaSalvo);
        
        ResponseEntity<BolsistaDTO> response = bolsistaController.adicionarBolsista(novoBolsista);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(novoBolsistaSalvo, response.getBody());
    }

    @Test
    public void testGetBolsistaPorId() {
        Long id = 1L;
        BolsistaDTO bolsista = new BolsistaDTO(id, "Nome", 1, "email@email.com", null, null, null, null, true);
        when(bolsistaService.findById(id)).thenReturn(bolsista);
        
        ResponseEntity<BolsistaDTO> response = bolsistaController.getBolsistaPorId(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bolsista, response.getBody());
    }

    @Test
    public void testAtualizarBolsista() {
        Long id = 1L;
        BolsistaDTO bolsistaAtualizado = new BolsistaDTO(id, "Novo Nome", 1, "novo@email.com", null, null, null, null, true);
        BolsistaDTO bolsistaExistente = new BolsistaDTO(id, "Nome Antigo", 1, "email@email.com", null, null, null, null, true);
        when(bolsistaService.findById(id)).thenReturn(bolsistaExistente);
        when(bolsistaService.salvar(any(BolsistaDTO.class))).thenReturn(bolsistaAtualizado);
        
        ResponseEntity<BolsistaDTO> response = bolsistaController.atualizarBolsista(bolsistaAtualizado);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(bolsistaAtualizado, response.getBody());
    }

    @Test
    public void testDesativarBolsista() {
        Long id = 1L;
        String mensagemSucesso = "Bolsista desativado com sucesso.";
        when(bolsistaService.desativarBolsista(id)).thenReturn("OK");
        
        ResponseEntity<String> response = bolsistaController.desativarBolsista(id);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemSucesso, response.getBody());
    }
}