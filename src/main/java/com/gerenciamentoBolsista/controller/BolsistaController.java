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
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.service.BolsistaService;

@RestController
@RequestMapping("/api/bolsistas")
public class BolsistaController {

    private final BolsistaService bolsistaService;

    @Autowired
    public BolsistaController(BolsistaService bolsistaService) {
        this.bolsistaService = bolsistaService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<BolsistaDTO>> listarBolsistas() {
        List<BolsistaDTO> bolsistas = bolsistaService.listarTodos();
        return new ResponseEntity<>(bolsistas, HttpStatus.OK);
    }

    @PostMapping("/insert-bolsista")
    public ResponseEntity<BolsistaDTO> adicionarBolsista(@RequestBody BolsistaDTO bolsistaDTO) {
        BolsistaDTO novoBolsistaDTO = bolsistaService.salvar(bolsistaDTO);
        return new ResponseEntity<>(novoBolsistaDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolsistaDTO> encontrarBolsistaPorId(@PathVariable Long id) {
        BolsistaDTO bolsistaDTO = bolsistaService.findById(id);
        return new ResponseEntity<>(bolsistaDTO, HttpStatus.OK);
    }

    @PutMapping("/alterar-bolsista")
    public ResponseEntity<BolsistaDTO> atualizarBolsista(@RequestBody BolsistaDTO bolsistaDTOAtualizado) {
        BolsistaDTO bolsistaDTO = bolsistaService.findById(bolsistaDTOAtualizado.getId());
        if (bolsistaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        BolsistaDTO bolsistaAtualizadoDTO = bolsistaService.salvar(bolsistaDTOAtualizado);
        return new ResponseEntity<>(bolsistaAtualizadoDTO, HttpStatus.OK);
    }

    @PutMapping("/desativar-bolsista/{id}")
    public ResponseEntity<String> desativarBolsista(@PathVariable Long id) {
        bolsistaService.desativarBolsista(id);
        return new ResponseEntity<>("Bolsista desativado com sucesso.",HttpStatus.OK);
    }
}