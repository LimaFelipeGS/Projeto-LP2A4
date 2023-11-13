package controller;

import dto.TarefaRequestDTO;
import dto.TarefaResponseDTO;
import model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.TarefaRepository;

import java.util.List;

public class TarefaController {
    @Autowired
    TarefaRepository tarefaRepository;

    @GetMapping
    public List<TarefaResponseDTO> getAll() {
        return tarefaRepository.findAll().stream().map(TarefaResponseDTO::new).toList();
    }

    @PostMapping
    public void addTarefa(@RequestBody TarefaRequestDTO data){
        tarefaRepository.save(new Tarefa(data));
    }

    @PutMapping("/{id}")
    public void editarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO data){
        Tarefa tarefa = new Tarefa(data);
        tarefa.setIdTarefa(id);
        tarefaRepository.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public void deleteTarefa(@PathVariable Long id){
        tarefaRepository.deleteById(id);
    }
}
