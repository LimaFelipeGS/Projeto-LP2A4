package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListaTarefas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idListaTarefa;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL) //TODO: Necessita correção
    private List<Tarefa>;

    public void criarLista(){
        //TODO
    }

    public void deletarLista(){
        //TODO
    }

    public void verLista){
        //TODO
    }

    public void editarLista(){
        //TODO
    }
}
