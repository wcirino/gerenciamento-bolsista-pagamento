package com.gerenciamentoBolsista.service;

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.entity.Bolsista;
import com.gerenciamentoBolsista.entity.enums.StatusPagamento;
import com.gerenciamentoBolsista.repository.BolsistaRepository;
import com.gerenciamentoBolsista.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BolsistaServiceTest {

    @Mock
    private BolsistaRepository bolsistaRepository;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BolsistaService bolsistaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodos_DeveRetornarListaDeBolsistasQuandoExistirem() {
        Bolsista bolsista1 = new Bolsista(1L, "João", 1, "123456", LocalDate.now(), "001", "1234", "5678", true);
        Bolsista bolsista2 = new Bolsista(2L, "Maria", 2, "654321", LocalDate.now(), "002", "5678", "1234", true);
        List<Bolsista> bolsistas = Arrays.asList(bolsista1, bolsista2);

        when(bolsistaRepository.findAll()).thenReturn(bolsistas);

        List<BolsistaDTO> bolsistaDTOList = bolsistaService.listarTodos();

        assertNotNull(bolsistaDTOList);
        assertEquals(2, bolsistaDTOList.size());
    }

    @Test
    void listarTodos_DeveRetornarListaVaziaQuandoNaoExistiremBolsistas() {
        when(bolsistaRepository.findAll()).thenReturn(new ArrayList<>());

        List<BolsistaDTO> bolsistaDTOList = bolsistaService.listarTodos();

        assertNotNull(bolsistaDTOList);
        assertTrue(bolsistaDTOList.isEmpty());
    }

//    @Test
//    void salvar_DeveSalvarBolsistaQuandoInformacoesValidasSaoFornecidas() {
//        BolsistaDTO bolsistaDTO = new BolsistaDTO(null, "Maria", 1, "123456", null, "001", "1234", "5678", true);
//        Bolsista bolsistaSalvo = new Bolsista(1L, "Maria", 1, "123456", LocalDate.now(), "001", "1234", "5678", true);
//
//        when(modelMapper.map(bolsistaDTO, Bolsista.class)).thenReturn(bolsistaSalvo);
//        when(bolsistaRepository.save(any(Bolsista.class))).thenReturn(bolsistaSalvo);
//        when(modelMapper.map(bolsistaSalvo, BolsistaDTO.class)).thenReturn(bolsistaDTO);
//
//        BolsistaDTO resultado = bolsistaService.salvar(bolsistaDTO);
//
//        assertNotNull(resultado);
//        assertEquals(bolsistaSalvo.getId(), resultado.getId());
//    }

    @Test
    void atualizarBolsista_DeveAtualizarBolsistaExistenteQuandoInformacoesValidasSaoFornecidas() throws Exception {
        Long bolsistaId = 1L;
        BolsistaDTO bolsistaDTOAtualizado = new BolsistaDTO(bolsistaId, "Maria Silva", 1, "123456", null, "001", "1234", "5678", true);
        Bolsista bolsistaExistente = new Bolsista(bolsistaId, "Maria", 1, "123456", LocalDate.now(), "001", "1234", "5678", true);

        when(bolsistaRepository.findById(bolsistaId)).thenReturn(Optional.of(bolsistaExistente));
        when(bolsistaRepository.save(any(Bolsista.class))).thenReturn(bolsistaExistente);
        when(modelMapper.map(bolsistaExistente, BolsistaDTO.class)).thenReturn(bolsistaDTOAtualizado);

        BolsistaDTO resultado = bolsistaService.atualizarBolsista(bolsistaDTOAtualizado);

        assertNotNull(resultado);
        assertEquals(bolsistaId, resultado.getId());
        assertEquals(bolsistaDTOAtualizado.getNomeCompleto(), resultado.getNomeCompleto());
    }

    @Test
    void atualizarBolsista_DeveLancarExcecaoQuandoBolsistaNaoExistir() {
        BolsistaDTO bolsistaDTOAtualizado = new BolsistaDTO(1L, "Maria Silva", 1, "123456", null, "001", "1234", "5678", true);

        when(bolsistaRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> bolsistaService.atualizarBolsista(bolsistaDTOAtualizado));
    }

    @Test
    void desativarBolsista_DeveDesativarBolsistaQuandoNaoExistiremPagamentosAssociados() {
        Long bolsistaId = 1L;

        when(pagamentoRepository.existsByBolsistaIdAndStatusIn(eq(bolsistaId), anyList())).thenReturn(false);

        String resultado = bolsistaService.desativarBolsista(bolsistaId);

        assertEquals("OK", resultado);
        verify(bolsistaRepository).desativarBolsista(bolsistaId);
    }

    @Test
    void desativarBolsista_DeveRetornarMensagemDeErroQuandoExistiremPagamentosAssociados() {
        Long bolsistaId = 1L;

        when(pagamentoRepository.existsByBolsistaIdAndStatusIn(eq(bolsistaId), anyList())).thenReturn(true);

        String resultado = bolsistaService.desativarBolsista(bolsistaId);

        assertEquals("Não é possível desativar o bolsista pois existem pagamentos pagos ou solicitados associados a ele.", resultado);
        verify(bolsistaRepository, never()).desativarBolsista(any());
    }
}
