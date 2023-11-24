package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;

import java.util.List;

public record ListaTarefasRequestDTO(String nome, Long perfil, List<Long> tarefa) {
//    public ListaTarefasRequestDTO(ListaTarefas l){this(l.getNome());}
}
