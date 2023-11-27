package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;
import java.util.List;

/**
 * Data Transfer Object (DTO) para representar informações da entidade ListaTarefas em respostas.
 */
public record ListaTarefasResponseDTO(Long id, String nome, List<TarefaResponseDTO> tarefa) {
    // Construtor adicional para criar um DTO a partir de uma instância de ListaTarefas
//    public ListaTarefasResponseDTO(ListaTarefas listaTarefas) {
//        this(listaTarefas.getId(), listaTarefas.getNome(), new UsuarioResponseDTO(listaTarefas.getUsuario()),
//                listaTarefas.getTarefa().stream().map(TarefaResponseDTO::new).toList());
//    }

    /**
     * Construtor primário que cria uma instância de ListaTarefasResponseDTO.
     *
     * @param id     O ID da ListaTarefas.
     * @param nome   O nome da ListaTarefas.
     * @param tarefa Uma lista de DTOs de TarefaResponseDTO associados à ListaTarefas.
     */
    public ListaTarefasResponseDTO {
        // Este bloco é usado para adicionar lógica ao construtor, se necessário
        // Por exemplo, para realizar validações nos parâmetros fornecidos.
        if (id == null) {
            throw new IllegalArgumentException("O ID da ListaTarefas não pode ser nulo.");
        }
        if (nome == null) {
            throw new IllegalArgumentException("O nome da ListaTarefas não pode ser nulo.");
        }
        if (tarefa == null) {
            throw new IllegalArgumentException("A lista de tarefas associadas não pode ser nula.");
        }
    }
}

