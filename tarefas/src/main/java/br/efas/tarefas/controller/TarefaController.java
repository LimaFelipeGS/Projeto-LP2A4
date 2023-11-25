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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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
    public List<TarefaResponseDTO> getAll() {
     //   System.out.println("teste1 getAll");
        return tarefaRepository.findAll().stream().map(TarefaResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void addTarefa(@RequestBody TarefaRequestDTO data) {
        List<Usuario> usuarios = null;
        ListaTarefas listaTarefas = null;

        Tarefa tarefa = new Tarefa(data);

        if (data.usuarios() != null) {
            usuarios = usuarioRepository.findAllById(data.usuarios());
        }

        if(data.listaTarefas() != null) {
            listaTarefas = listaTarefasRepository.getById(data.listaTarefas());
            tarefa.setListaTarefas(listaTarefas);
        }

        Tarefa savedTarefa = tarefaRepository.save(tarefa);
        for (Usuario usuario : usuarios){
            List<Tarefa> userTarefas = usuario.getTarefas();
            userTarefas.add(savedTarefa);
            usuario.setTarefas(userTarefas);
            usuarioRepository.save(usuario);
        }
    }

    @PutMapping("/{id}")
    public void editarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO data) {
        Tarefa tarefa = new Tarefa(data);
        tarefa.setId(id);
        tarefaRepository.save(tarefa);
    }


    @DeleteMapping("/{id}")
    public void deleteTarefa(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> findTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        return tarefa.map(value -> new ResponseEntity<>(new TarefaResponseDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }





}

