package br.efas.tarefas.dto;

import br.efas.tarefas.model.UsuarioComum;

/**
 * Data Transfer Object (DTO) para representar informações de resposta relacionadas à entidade UsuarioComum.
 */
public record UsuarioComumResponseDTO(Long id, String nome) {
    /**
     * Construtor que cria um DTO a partir de um objeto UsuarioComum.
     *
     * @param usuarioComum O objeto UsuarioComum do qual extrair informações para o DTO.
     */
    public UsuarioComumResponseDTO(UsuarioComum usuarioComum) {
        this(usuarioComum.getId(), usuarioComum.getNome());
    }
}

