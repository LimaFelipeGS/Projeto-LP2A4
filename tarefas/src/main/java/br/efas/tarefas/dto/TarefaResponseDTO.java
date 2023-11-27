package br.efas.tarefas.dto;

import br.efas.tarefas.model.Tarefa;

import java.time.LocalDateTime;
import java.util.Date;
// Código feito por Suanne, Felipe

/**
 * Data Transfer Object (DTO) para representar informações de resposta relacionadas à entidade Tarefa.
 */
public record TarefaResponseDTO(Long id, String nome, LocalDateTime data, String descricao) {
    /**
     * Construtor que cria um DTO a partir de uma instância da entidade Tarefa.
     *
     * @param tarefa A instância da entidade Tarefa da qual os dados serão extraídos.
     */
    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getData(), tarefa.getDescricao());
    }
}

