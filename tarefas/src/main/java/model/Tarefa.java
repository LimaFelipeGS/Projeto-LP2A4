package model;

import dto.TarefaRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarefa")
@Entity(name = "tarefa")
public class Tarefa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;
    @Column
    private String nome;
    @Column
    private Date data;
    @Column
    private String horario;
    @Column
    private String descricao;

    public Tarefa(TarefaRequestDTO data) {
        this.nome = data.nome();
        this.data = data.data();
        this.horario = data.horario();
        this.descricao = data.descricao();
    }
}
