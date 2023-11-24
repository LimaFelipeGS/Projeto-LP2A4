package br.efas.tarefas.dto;


import br.efas.tarefas.model.Usuario;

public record UsuarioRequestDTO( String email, String senha, String nome) {
//    public UsuarioRequestDTO(Usuario p) {this( p.getEmail(),p.getSenha());}
}
