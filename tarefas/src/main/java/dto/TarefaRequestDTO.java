package dto;

import java.util.Date;

public record TarefaRequestDTO(String nome, Date data, String horario, String descricao) {
}
