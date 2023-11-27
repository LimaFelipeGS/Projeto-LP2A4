package br.efas.tarefas.dto;


import br.efas.tarefas.model.Usuario;
// Código feito por Suanne, Felipe
/**
 * Data Transfer Object (DTO) para representar informações de solicitação relacionadas à entidade Usuario.
 */
public record UsuarioRequestDTO(String email, String senha, String nome) {
//    public UsuarioRequestDTO(Usuario usuario) {
//        this(usuario.getEmail(), usuario.getSenha(), usuario.getNome());
//    }
}

