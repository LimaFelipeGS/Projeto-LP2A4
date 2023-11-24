package br.efas.tarefas.controller;

import br.efas.tarefas.dto.ListaTarefasResponseDTO;
import br.efas.tarefas.model.ListaTarefas;
import br.efas.tarefas.repository.ListaTarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("listaTarefas")
public class ListaTarefaController {
//    @Autowired
//    private ListaTarefasRepository listaTarefasRepository;
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping
//    public List<ListaTarefasResponseDTO> getAll() {
//////        return listaTarefasRepository.findAll().stream().map(ListaTarefasResponseDTO::new).toList();
////    }
//
////    @CrossOrigin(origins = "*", allowedHeaders = "*")
////    @PostMapping
////    public void addListaTarefas(@RequestBody ListaTarefasRequestDTO data) {
////        listaTarefasRepository.save(new ListaTarefas(data));
////    }
//
////    @PutMapping("/{id}")
////    public void editarListaTarefas(@PathVariable Long id, @RequestBody ListaTarefasRequestDTO data) {
////        ListaTarefas listaTarefas = listaTarefasRepository.findById(id).orElse(null);
////
////        if (listaTarefas != null) {
////            // Ajuste conforme os campos reais no ListaTarefasRequestDTO
////            listaTarefas.setNome(data.nome);
////            listaTarefas.setPerfil(UsuarioRepository.findById(data.perfil).orElse(null));
////            // Adicione aqui os campos adicionais que precisam ser atualizados
////
////            listaTarefasRepository.save(listaTarefas);
////        }
////    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteListaTarefas(@PathVariable Long id) {
//        listaTarefasRepository.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public Optional<ListaTarefas> findListaTarefas(@PathVariable Long id) {
//        return listaTarefasRepository.findById(id);
//    }
}
