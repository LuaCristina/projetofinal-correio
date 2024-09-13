package com.example.correio.service;

import com.example.correio.exception.ResourceNotFoundException;
import com.example.correio.model.Pacote;
import com.example.correio.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public List<Pacote> listarTodos() {
        return pacoteRepository.findAll();
    }

    public Pacote obterPorId(Long id) {
        return pacoteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pacote não encontrado com ID: " + id));
    }

    public Pacote salvar(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public Pacote atualizar(Pacote pacote) {
        if (!pacoteRepository.existsById(pacote.getId())) {
            throw new ResourceNotFoundException("Pacote não encontrado com ID: " + pacote.getId());
        }
        return pacoteRepository.save(pacote);
    }

    public void deletar(Long id) {
        Pacote pacote = obterPorId(id);
        pacoteRepository.delete(pacote);
    }
}
