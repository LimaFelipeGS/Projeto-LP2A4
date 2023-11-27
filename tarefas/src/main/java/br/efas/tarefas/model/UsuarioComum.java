package br.efas.tarefas.model;

import br.efas.tarefas.dto.UsuarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// Código feito por Suanne

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuariosComum")
@Entity(name = "usuariosComum")
public class UsuarioComum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;


    public UsuarioComum(String nome, Usuario usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }
}
