package br.efas.tarefas.dto;

import br.efas.tarefas.model.Usuario;
import br.efas.tarefas.model.UsuarioComum;

public record UsuarioResponseDTO(Long id, String email, String senha, String nome) {
    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getUsuarioComum().getNome());
    }
}
