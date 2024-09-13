package com.example.correio.service;

import com.example.correio.exception.ResourceNotFoundException;
import com.example.correio.model.Usuario;
import com.example.correio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obterPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + usuario.getId());
        }
        // Se a senha for atualizada, criptografá-la
        if (usuario.getSenha() != null && !usuario.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        } else {
            // Manter a senha atual se não for fornecida
            String senhaAtual = usuarioRepository.findById(usuario.getId())
                    .map(Usuario::getSenha)
                    .orElse(null);
            usuario.setSenha(senhaAtual);
        }
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = obterPorId(id);
        usuarioRepository.delete(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}
