package br.efas.tarefas.dto;

import br.efas.tarefas.model.Tarefa;

import java.util.Date;

public record TarefaResponseDTO(Long id, String nome, Date data, String horario, String descricao) {
    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getData(), tarefa.getHorario(), tarefa.getDescricao());
    }
}
