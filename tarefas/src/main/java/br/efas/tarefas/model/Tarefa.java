package br.efas.tarefas.model;

import br.efas.tarefas.dto.TarefaRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
// Código feito por Suanne, Felipe, Eduardo
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarefas")
@Entity(name = "tarefas")
public class Tarefa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private LocalDateTime data;

    @Column
    private String descricao;

    @ManyToMany(mappedBy = "tarefas", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Usuario> usuarios;

    @ManyToOne
    @JoinColumn(name = "listaTarefas_id")
    private ListaTarefas listaTarefas;


    public Tarefa(TarefaRequestDTO data) {
        this.nome = data.nome();
        this.data = data.data();
//        this.horario = data.horario();
        this.descricao = data.descricao();
    }
}
