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

/**
 * Controlador responsável por manipular operações relacionadas a Tarefas.
 */
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

    /**
     * Obtém todas as tarefas cadastradas.
     *
     * @return ResponseEntity contendo a lista de TarefaResponseDTO ou status 500 em caso de erro.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> getAll() {
        try {
            // Busca todas as tarefas no repositório e mapeia para TarefaResponseDTO
            List<TarefaResponseDTO> tarefas = tarefaRepository.findAll()
                    .stream()
                    .map(TarefaResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(tarefas);
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    /**
     * Adiciona uma nova tarefa.
     *
     * @param data O objeto TarefaRequestDTO contendo os dados da nova tarefa.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addTarefa(@RequestBody TarefaRequestDTO data) {
        try {
            // Inicializa as listas como nulas
            List<Usuario> usuarios = null;
            ListaTarefas listaTarefas = null;

            // Cria a Tarefa com base nos dados fornecidos no TarefaRequestDTO
            Tarefa tarefa = new Tarefa(data);

            // Verifica se há usuários associados à tarefa
            if (data.usuarios() != null) {
                usuarios = usuarioRepository.findAllById(data.usuarios());
            }

            // Verifica se há uma lista de tarefas associada à tarefa
            if (data.listaTarefas() != null) {
                listaTarefas = listaTarefasRepository.findById(data.listaTarefas())
                        .orElseThrow(() -> new EntityNotFoundException("Lista de Tarefas não encontrada com o ID: " + data.listaTarefas()));
                tarefa.setListaTarefas(listaTarefas);
            }

            // Salva a Tarefa no repositório
            Tarefa savedTarefa = tarefaRepository.save(tarefa);

            // Associa a nova tarefa aos usuários correspondentes
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

    /**
     * Edita uma tarefa existente com base no ID.
     *
     * @param id   O ID da tarefa a ser editada.
     * @param data O objeto TarefaRequestDTO contendo os novos valores para a tarefa.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarTarefa(@PathVariable Long id, @RequestBody TarefaRequestDTO data) {
        try {
            // Busca a tarefa no repositório com base no ID
            Tarefa tarefa = tarefaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com o ID: " + id));

            // Atualiza os valores da tarefa com base nos dados fornecidos no TarefaRequestDTO
            if (data.nome() != null) {
                tarefa.setNome(data.nome());
            }

            if (data.data() != null) {
                tarefa.setData(data.data());
            }

            if (data.descricao() != null) {
                tarefa.setDescricao(data.descricao());
            }

            // Salva as alterações no repositório
            tarefaRepository.save(tarefa);

            return ResponseEntity.status(HttpStatus.OK).body("Tarefa editada com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao editar a tarefa: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar a tarefa: " + e.getMessage());
        }
    }

    /**
     * Exclui uma tarefa com base no ID.
     *
     * @param id O ID da tarefa a ser excluída.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
        try {
            // Exclui a tarefa do repositório
            tarefaRepository.deleteById(id);

            return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída com sucesso!");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada com o ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir a tarefa: " + e.getMessage());
        }
    }

    /**
     * Obtém uma tarefa com base no ID.
     *
     * @param id O ID da tarefa a ser encontrada.
     * @return ResponseEntity contendo a tarefa encontrada ou status 404 se não encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> findTarefa(@PathVariable Long id) {
        try {
            // Busca a tarefa no repositório com base no ID
            Optional<Tarefa> tarefa = tarefaRepository.findById(id);

            // Retorna a tarefa encontrada ou status 404 se não encontrada
            return tarefa.map(value -> new ResponseEntity<>(new TarefaResponseDTO(value), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

