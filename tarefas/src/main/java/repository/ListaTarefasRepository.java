package repository;

import model.ListaTarefas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaTarefasRepository extends JpaRepository<ListaTarefas, Long> {
}
