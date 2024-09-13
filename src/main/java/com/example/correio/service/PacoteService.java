package com.example.correio.service;

import com.example.correio.model.Pacote;
import com.example.correio.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public List<Pacote> listarTodos() {
        return pacoteRepository.findAll();
    }

    public Optional<Pacote> obterPorId(Long id) {
        return pacoteRepository.findById(id);
    }

    public Pacote salvar(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public void deletar(Long id) {
        pacoteRepository.deleteById(id);
    }
}
