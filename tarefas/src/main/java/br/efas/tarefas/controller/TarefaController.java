package br.efas.tarefas.controller;

import br.efas.tarefas.dto.TarefaRequestDTO;
import br.efas.tarefas.dto.TarefaResponseDTO;
import br.efas.tarefas.dto.UsuarioResponseDTO;
import br.efas.tarefas.model.ListaTarefas;
import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.model.Usuario;
import br.efas.tarefas.repository.ListaTarefasRepository;
import br.efas.tarefas.repository.TarefaRepository;
import br.efas.tarefas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("tarefa")
public class TarefaController {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ListaTarefasRepository listaTarefasRepository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> getAll() {
        try {
            List<TarefaResponseDTO> tarefas = tarefaRepository.findAll()
                    .stream()
                    .map(TarefaResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(tarefas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addTarefa(@RequestBody TarefaRequestDTO data) {
        try {
            List<Usuario> usuarios = null;
            ListaTarefas listaTarefas = null;

            Tarefa tarefa = new Tarefa(data);

            if (data.usuarios() != null) {
                usuarios = usuarioRepository.findAllById(data.usuarios());
            }

            if (data.listaTarefas() != null) {
                listaTarefas = listaTarefasRepository.findById(data.listaTarefas())
                        .orElseThrow(() -> new EntityNotFoundException("Lista de Tarefas não encontrada com o ID: " + data.listaTarefas()));
                tarefa.setListaTarefas(listaTarefas);
            }

            Tarefa savedTarefa = tarefaRepository.save(tarefa);

            if (usuarios != null) {
                for (Usuario usuario : usuarios) {
                    List<Tarefa> userTarefas = usuario.getTarefas();
                    userTarefas.add(savedTarefa);
                    usuario.setTarefas(userTarefas);
                    usuarioRepository.save(usuario);
                }
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Tarefa criada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao criar a tarefa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar a tarefa: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO data) {

        try {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com o ID: " + id));

        if (data.nome() != null) {
            tarefa.setNome(data.nome());
        }

        if (data.data() != null) {
            tarefa.setData(data.data());
        }

        if (data.descricao() != null) {
            tarefa.setDescricao(data.descricao());
        }

        tarefaRepository.save(tarefa);

            return ResponseEntity.status(HttpStatus.OK).body("Tarefa editada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao editar a tarefa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar a tarefa: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
        try {
            tarefaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída com sucesso!");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada com o ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a tarefa: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> findTarefa(@PathVariable Long id) {
        try {
            Optional<Tarefa> tarefa = tarefaRepository.findById(id);
            return tarefa.map(value -> new ResponseEntity<>(new TarefaResponseDTO(value), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

