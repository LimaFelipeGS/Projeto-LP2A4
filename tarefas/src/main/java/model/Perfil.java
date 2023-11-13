package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Perfil{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;
    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private ListaTarefas listaTarefas;
}
