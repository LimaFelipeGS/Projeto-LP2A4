package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;

import java.util.List;

/**
 * Data Transfer Object (DTO) para requisições relacionadas à entidade ListaTarefas.
 */
public record ListaTarefasRequestDTO(String nome, List<Long> usuarios, List<Long> tarefas) {
    // Construtor adicional para criar um DTO a partir de uma instância de ListaTarefas
    // public ListaTarefasRequestDTO(ListaTarefas l) { this(l.getNome()); }

    /**
     * Construtor primário que cria uma instância de ListaTarefasRequestDTO.
     *
     * @param nome     O nome da ListaTarefas.
     * @param usuarios Uma lista de IDs de usuários associados à ListaTarefas.
     * @param tarefas  Uma lista de IDs de tarefas associadas à ListaTarefas.
     */
    public ListaTarefasRequestDTO {
        // Este bloco é usado para adicionar lógica ao construtor, se necessário
        // Por exemplo, para realizar validações nos parâmetros fornecidos.
        if (nome == null) {
            throw new IllegalArgumentException("O nome da ListaTarefas não pode ser nulo.");
        }
    }
}
