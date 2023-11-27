package br.efas.tarefas.repository;

import br.efas.tarefas.model.ListaTarefas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Código feito por Felipe

public interface ListaTarefasRepository extends JpaRepository<ListaTarefas, Long> {
}
