package br.efas.tarefas.repository;

import br.efas.tarefas.model.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioComumRepository extends JpaRepository <UsuarioComum, Long> {
}
