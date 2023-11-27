package br.efas.tarefas.dto;

import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.model.Usuario;
import org.hibernate.dialect.function.TruncFunction;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
// Código feito por Suanne, Felipe
/**
 * Data Transfer Object (DTO) para representar informações necessárias para criar ou editar uma Tarefa.
 */
public record TarefaRequestDTO(String nome, LocalDateTime data, String descricao, List<Long> usuarios, Long listaTarefas) {
//    public TarefaRequestDTO(Tarefa t){this(t.getNome(), t.getData(), t.getHorario(), t.getDescricao(), t.getUsuarios().stream().map((Usuario::new).toList()));}
}

