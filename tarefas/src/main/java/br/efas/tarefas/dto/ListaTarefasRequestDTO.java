package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;

import java.util.List;

public record ListaTarefasRequestDTO(String nome, List<Long> usuarios, List<Long> tarefas) {
//    public ListaTarefasRequestDTO(ListaTarefas l){this(l.getNome());}
}
