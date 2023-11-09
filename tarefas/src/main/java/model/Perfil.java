package model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;
    private ListaTarefas listaTarefas;

    public void criarPerfil(){
        //TODO
    }

    public void deletarPerfil(){
        //TODO
    }

    public void verPerfil(){
        //TODO
    }

    public void editarPerfil(){
        //TODO
    }
}
