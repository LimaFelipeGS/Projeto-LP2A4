package br.efas.tarefas.controller;

import br.efas.tarefas.dto.ListaTarefasResponseDTO;
import br.efas.tarefas.dto.ListaTarefasRequestDTO;
import br.efas.tarefas.model.ListaTarefas;
import br.efas.tarefas.model.Tarefa;
import br.efas.tarefas.model.Usuario;
import br.efas.tarefas.model.UsuarioComum;
import br.efas.tarefas.repository.ListaTarefasRepository;
import br.efas.tarefas.repository.TarefaRepository;
import br.efas.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("listaTarefas")
public class ListaTarefaController {
    @Autowired
    private ListaTarefasRepository listaTarefasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void addListaTarefas(@RequestBody ListaTarefasRequestDTO data) {
        // Inicialize as listas como nulas
        List<Tarefa> tarefas = null;
        List<Usuario> usuarios = null;

        // Verifique se data.tarefas() não é nulo antes de tentar carregar do repositório
        if (data.tarefas() != null) {
            tarefas = tarefaRepository.findAllById(data.tarefas());
        }

        // Verifique se data.usuarios() não é nulo antes de tentar carregar do repositório
        if (data.usuarios() != null) {
            usuarios = usuarioRepository.findAllById(data.usuarios());
        }

        // Crie a ListaTarefas usando as tarefas e usuários carregados
        ListaTarefas listaTarefas = new ListaTarefas(data.nome(), usuarios, tarefas);

        // Salve a ListaTarefas no banco de dados
        ListaTarefas savedListaTarefa = listaTarefasRepository.save(listaTarefas);

        if(usuarios != null) {
            for (Usuario usuario : usuarios){
                usuario.setUsuarioComum(null);
                List<ListaTarefas> userListasTarefas = usuario.getListasTarefas();
                userListasTarefas.add(savedListaTarefa);
                usuario.setListasTarefas(userListasTarefas);
                usuarioRepository.save(usuario);
            }
        }
        if(tarefas != null) {
            for (Tarefa tarefa : tarefas){
                tarefa.setListaTarefas(savedListaTarefa);
                tarefaRepository.save(tarefa);
            }
        }

    }
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
    @GetMapping("/{id}")
    public Optional<ListaTarefas> findListaTarefas(@PathVariable Long id) {
        return listaTarefasRepository.findById(id);
    }
}
