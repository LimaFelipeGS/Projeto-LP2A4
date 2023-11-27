package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;
import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.model.Usuario;
import br.efas.tarefas.model.UsuarioComum;
import java.util.List;
// Código feito por Suanne

/**
 * Data Transfer Object (DTO) para representar informações de resposta relacionadas à entidade Usuario.
 */
public record UsuarioResponseDTO(Long id, String email, String senha, String nome, List<ListaTarefas> listaTarefa, List<Tarefa> tarefas) {
    public UsuarioResponseDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getUsuarioComum().getNome(),
                usuario.getListasTarefas(),
                usuario.getTarefas()
        );
    }
}

