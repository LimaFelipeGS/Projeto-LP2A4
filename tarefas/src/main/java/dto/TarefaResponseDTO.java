package dto;

import model.Tarefa;

import java.util.Date;

public record TarefaResponseDTO(Long id, String nome, Date data, String horario, String descricao) {
    public TarefaResponseDTO(Tarefa t) {
        this(t.getIdTarefa(), t.getNome(), t.getData(), t.getHorario(), t.getDescricao());
    }
}
