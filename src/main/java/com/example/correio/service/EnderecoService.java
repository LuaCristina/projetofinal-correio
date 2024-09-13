package com.example.correio.service;

import com.example.correio.exception.ResourceNotFoundException;
import com.example.correio.model.Endereco;
import com.example.correio.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco obterPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Endereco endereco) {
        if (!enderecoRepository.existsById(endereco.getId())) {
            throw new ResourceNotFoundException("Endereço não encontrado com ID: " + endereco.getId());
        }
        return enderecoRepository.save(endereco);
    }

    public void deletar(Long id) {
        Endereco endereco = obterPorId(id);
        enderecoRepository.delete(endereco);
    }
}
