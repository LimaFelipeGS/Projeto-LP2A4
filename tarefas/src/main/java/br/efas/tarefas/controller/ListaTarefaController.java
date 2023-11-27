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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Código feito por Suanne

/**
 * Controlador responsável por manipular operações relacionadas a Listas de Tarefas.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("listaTarefas")
public class ListaTarefaController {

    @Autowired
    private ListaTarefasRepository listaTarefasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    /**
     * Adiciona uma nova Lista de Tarefas.
     *
     * @param data O objeto ListaTarefasRequestDTO contendo os dados da nova lista.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addListaTarefas(@RequestBody ListaTarefasRequestDTO data) {
        try {
            // Inicializa as listas como nulas
            List<Tarefa> tarefas = null;
            List<Usuario> usuarios = null;

            // Verifica se data.tarefas() não é nulo antes de tentar carregar do repositório
            if (data.tarefas() != null) {
                tarefas = tarefaRepository.findAllById(data.tarefas());
            }

            // Verifica se data.usuarios() não é nulo antes de tentar carregar do repositório
            if (data.usuarios() != null) {
                usuarios = usuarioRepository.findAllById(data.usuarios());
            }

            // Crie a ListaTarefas usando as tarefas e usuários carregados
            ListaTarefas listaTarefas = new ListaTarefas(data.nome(), usuarios, tarefas);

            // Salve a ListaTarefas no banco de dados
            ListaTarefas savedListaTarefa = listaTarefasRepository.save(listaTarefas);

            // Associe a nova lista aos usuários e tarefas correspondentes
            if (usuarios != null) {
                for (Usuario usuario : usuarios) {
                    usuario.setUsuarioComum(null);
                    List<ListaTarefas> userListasTarefas = usuario.getListasTarefas();
                    userListasTarefas.add(savedListaTarefa);
                    usuario.setListasTarefas(userListasTarefas);
                    usuarioRepository.save(usuario);
                }
            }
            if (tarefas != null) {
                for (Tarefa tarefa : tarefas) {
                    tarefa.setListaTarefas(savedListaTarefa);
                    tarefaRepository.save(tarefa);
                }
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Lista de tarefas adicionada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao adicionar a lista de tarefas: " + e.getMessage());
        }
    }

    /**
     * Edita uma Lista de Tarefas existente com base no ID.
     *
     * @param id   O ID da lista de tarefas a ser editada.
     * @param data O objeto ListaTarefasRequestDTO contendo os novos valores para a lista de tarefas.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarListaTarefas(@PathVariable Long id, @RequestBody ListaTarefasRequestDTO data) {
        try {
            // Busca a lista de tarefas no repositório com base no ID
            Optional<ListaTarefas> optionalListaTarefas = listaTarefasRepository.findById(id);

            // Verifica se a lista de tarefas existe
            if (optionalListaTarefas.isPresent()) {
                ListaTarefas listaTarefas = optionalListaTarefas.get();

                // Verifica se o nome foi fornecido no DTO e o atualiza se necessário
                if (data.nome() != null) {
                    listaTarefas.setNome(data.nome());
                }

                // Salva as alterações no repositório
                listaTarefasRepository.save(listaTarefas);

                return ResponseEntity.ok("Lista de Tarefas atualizada com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de Tarefas não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao editar a lista de tarefas: " + e.getMessage());
        }
    }

    /**
     * Deleta uma Lista de Tarefas existente com base no ID.
     *
     * @param id O ID da lista de tarefas a ser deletada.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListaTarefas(@PathVariable Long id) {
        try {
            // Busca os usuários associados à lista de tarefas
            List<Usuario> usuarios = usuarioRepository.findByListasTarefasId(id);

            // Desassocia a lista de tarefas dos usuários correspondentes
            for (Usuario usuario : usuarios) {
                usuario.setListasTarefas(null);
                usuarioRepository.save(usuario);
            }

            // Deleta a lista de tarefas do repositório
            listaTarefasRepository.deleteById(id);

            return ResponseEntity.ok("Lista de Tarefas deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar a lista de tarefas: " + e.getMessage());
        }
    }

    /**
     * Obtém uma Lista de Tarefas existente com base no ID.
     *
     * @param id O ID da lista de tarefas a ser encontrada.
     * @return ResponseEntity contendo a lista de tarefas encontrada ou status 404 se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ListaTarefas> findListaTarefas(@PathVariable Long id) {
        try {
            // Busca a lista de tarefas no repositório com base no ID
            Optional<ListaTarefas> listaTarefas = listaTarefasRepository.findById(id);

            // Verifica se a lista de tarefas foi encontrada e retorna a resposta apropriada
            return listaTarefas.map(value -> ResponseEntity.ok().body(value))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Retorna um erro interno do servidor se ocorrer uma exceção durante a execução
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
