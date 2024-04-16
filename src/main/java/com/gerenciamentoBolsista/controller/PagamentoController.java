package com.gerenciamentoBolsista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoBolsista.dto.PagamentoDTO;
import com.gerenciamentoBolsista.service.PagamentoService;

@RestController
@RequestMapping(value = "/api/pagamentos")
public class PagamentoController {

    @Autowired
    public PagamentoService service;


    @GetMapping("/findAll")
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        List<PagamentoDTO> pagamentos = service.listarTodos();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @PostMapping("/insert-pagamento")
    public ResponseEntity<PagamentoDTO> adicionarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        PagamentoDTO novoPagamentoDTO = service.salvar(pagamentoDTO);
        return new ResponseEntity<>(novoPagamentoDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> encontrarPagamentoPorId(@PathVariable Long id) {
        PagamentoDTO pagamentoDTO = service.findById(id);
        return new ResponseEntity<>(pagamentoDTO, HttpStatus.OK);
    }
    
    @GetMapping("/find")
    public ResponseEntity<List<PagamentoDTO>> getPagamento(
            @RequestParam(required = false) Long idPagamento,
            @RequestParam(required = false) Long idBolsista) {
        List<PagamentoDTO> pagamentos = service.findAll(idPagamento, idBolsista);
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }


    @PutMapping("/alterar-pagamento")
    public ResponseEntity<PagamentoDTO> atualizarPagamento(@RequestBody PagamentoDTO pagamentoDTOAtualizado) {
        PagamentoDTO pagamentoDTO = service.findById(pagamentoDTOAtualizado.getId());
        if (pagamentoDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PagamentoDTO pagamentoAtualizadoDTO = service.salvar(pagamentoDTOAtualizado);
        return new ResponseEntity<>(pagamentoAtualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/remover-pagamento/{id}")
    public ResponseEntity<String> removerPagamento(@PathVariable Long id) {
    	service.remover(id);
        return new ResponseEntity<>("Pagamento removido com sucesso.", HttpStatus.OK);
    }
}

