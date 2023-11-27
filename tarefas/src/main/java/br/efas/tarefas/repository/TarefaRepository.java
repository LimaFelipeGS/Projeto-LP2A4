package br.efas.tarefas.repository;

import br.efas.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
// Código feito por Felipe

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
