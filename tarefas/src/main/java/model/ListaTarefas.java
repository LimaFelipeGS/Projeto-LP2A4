package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaTarefas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idListaTarefa;
    @Column
    private String nome;
//    @ManyToOne(cascade = CascadeType.ALL) //TODO: Necessita correção
//    @JoinColumn(name = "perfil_id")
    @Column
    private List<Tarefa> listaTarefa;
}
