package br.efas.tarefas.dto;

import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.model.Usuario;

import java.util.Date;
import java.util.List;

public record TarefaRequestDTO(String nome, Date data, String horario, String descricao, List<Long> usuarios, Long listaTarefas ) {
//    public TarefaRequestDTO(Tarefa t){this(t.getNome(), t.getData(), t.getHorario(), t.getDescricao(), t.getUsuarios().stream().map((Usuario::new).toList()));}
}
