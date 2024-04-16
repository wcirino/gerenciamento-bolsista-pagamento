package com.gerenciamentoBolsista.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gerenciamentoBolsista.dto.BolsistaDTO;
import com.gerenciamentoBolsista.entity.Bolsista;
import com.gerenciamentoBolsista.repository.BolsistaRepository;

import jakarta.transaction.Transactional;

@Repository
public class BolsistaService {

	@Autowired
	private BolsistaRepository bolsistaRepository;

	@Autowired
	private ModelMapper modelMapper;
    
	public List<BolsistaDTO> listarTodos() {
        List<Bolsista> bolsistas = bolsistaRepository.findAll();
        return bolsistas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BolsistaDTO findById(Long id) {
        Optional<Bolsista> bolsistaOptional = bolsistaRepository.findById(id);
        return bolsistaOptional.map(this::convertToDto).orElse(null);
    }

    public BolsistaDTO salvar(BolsistaDTO bolsistaDTO) {
        Bolsista bolsista = convertToEntity(bolsistaDTO);
        if (bolsista.getTipoIdentificador() == null) {
            throw new IllegalArgumentException("O tipo de identificador do bolsista não pode estar vazio.");
        }

        bolsista.setDataCadastro(LocalDate.now());
        Bolsista savedBolsista = bolsistaRepository.save(bolsista);
        return convertToDto(savedBolsista);
    }
    
    @Transactional
    public BolsistaDTO atualizarBolsista(BolsistaDTO bolsistaDTOAtualizado) throws Exception {

        BolsistaDTO bolsistaDTO = findById(bolsistaDTOAtualizado.getId());
        if (bolsistaDTO == null) {
            throw new Exception("Bolsista não encontrado com o ID: " + bolsistaDTOAtualizado.getId());
        }

        bolsistaRepository.updateBolsista(
                bolsistaDTOAtualizado.getId(),
                bolsistaDTOAtualizado.getNomeCompleto(),
                bolsistaDTOAtualizado.getTipoIdentificador(),
                bolsistaDTOAtualizado.getNumeroIdentificador(),
                bolsistaDTOAtualizado.getCodigoBanco(),
                bolsistaDTOAtualizado.getNumeroAgencia(),
                bolsistaDTOAtualizado.getNumeroConta()
        );


        return findById(bolsistaDTOAtualizado.getId());
    }
    
    @Transactional
    public void desativarBolsista(Long id) {
        Bolsista bolsista = bolsistaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Bolsista não encontrado com ID: " + id));
        bolsista.setStatus(false);
        bolsistaRepository.save(bolsista);
    }

    private BolsistaDTO convertToDto(Bolsista bolsista) {
        return modelMapper.map(bolsista, BolsistaDTO.class);
    }

    private Bolsista convertToEntity(BolsistaDTO bolsistaDTO) {
        return modelMapper.map(bolsistaDTO, Bolsista.class);
    }
}