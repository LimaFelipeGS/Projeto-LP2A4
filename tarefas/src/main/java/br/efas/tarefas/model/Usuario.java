package br.efas.tarefas.model;


import br.efas.tarefas.dto.UsuarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
@Entity (name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
//    @JsonIgnore
    private UsuarioComum usuarioComum;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_tarefa",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "tarefa_id")
    )
    private List<Tarefa> tarefas;

    @ManyToMany
    @JoinTable(
            name = "usuario_listaTarefas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "listaTarefas_id")
    )
    private List<ListaTarefas> listasTarefas;

    public Usuario(String email, String senha){

        this.email = email;
        this.senha = senha;
    }



}
