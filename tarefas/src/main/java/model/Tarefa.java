package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarefa;
    private String nome;
    private Date data;
    private String horario;
    private String descricao;

    public void criarTarefa(){
        //TODO
    }

    public void deletarTarefa(){
        //TODO
    }

    public void verTarefa(){
        //TODO
    }

    public void editarTarefa(){
        //TODO
    }
}
