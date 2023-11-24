package br.efas.tarefas.controller;

import br.efas.tarefas.dto.TarefaRequestDTO;
import br.efas.tarefas.dto.TarefaResponseDTO;
import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.repository.ListaTarefasRepository;
import br.efas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefa")
public class TarefaController {
//    @Autowired
//    private TarefaRepository tarefaRepository;
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping
//    public List<TarefaResponseDTO> getAll() {
//        return tarefaRepository.findAll().stream().map(TarefaResponseDTO::new).toList();
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping
//    public void addTarefa(@RequestBody TarefaRequestDTO data) {
//        tarefaRepository.save(new Tarefa(data));
//    }
//
//    @PutMapping("/{id}")
//    public void editarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO data) {
//        Tarefa tarefa = new Tarefa(data);
//        tarefa.setId(id);
//        tarefaRepository.save(tarefa);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteTarefa(@PathVariable Long id) {
//        tarefaRepository.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Tarefa> findTarefa(@PathVariable Long id) {
//        return tarefaRepository.findById(id);
//    }

}

