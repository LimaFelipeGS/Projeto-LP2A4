package br.efas.tarefas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="listaTarefas")
@Entity(name="listaTarefas")
public class ListaTarefas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @ManyToMany(mappedBy = "listasTarefas") // nomes de entidade e mappedBy igual
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "listaTarefas", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    public ListaTarefas(String nome, List<Usuario> usuarios, List<Tarefa> tarefas) {
        this.nome = nome;
        this.usuarios = usuarios;
        this.tarefas = tarefas;
    }
}
