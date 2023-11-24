package br.efas.tarefas.dto;

import br.efas.tarefas.model.UsuarioComum;

public record UsuarioComumResponseDTO(Long id, String nome) {
    public UsuarioComumResponseDTO(UsuarioComum usuarioComum){ this(usuarioComum.getId(), usuarioComum.getNome());}
}
