package br.efas.tarefas.dto;

import br.efas.tarefas.model.ListaTarefas;
import java.util.List;

public record ListaTarefasResponseDTO(Long id, String nome, UsuarioResponseDTO perfil, List<TarefaResponseDTO> tarefa) {
//    public ListaTarefasResponseDTO(ListaTarefas listaTarefas){
//        this(listaTarefas.getId(), listaTarefas.getNome(), new UsuarioResponseDTO(listaTarefas.getUsuario()), listaTarefas.getTarefa().stream().map(TarefaResponseDTO::new).toList());
//    }
}
