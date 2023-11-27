package br.efas.tarefas.controller;

import br.efas.tarefas.dto.UsuarioComumResponseDTO;
import br.efas.tarefas.dto.UsuarioRequestDTO;
import br.efas.tarefas.dto.UsuarioResponseDTO;
import br.efas.tarefas.model.Usuario;
import br.efas.tarefas.model.UsuarioComum;
import br.efas.tarefas.repository.UsuarioComumRepository;
import br.efas.tarefas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// Código feito por Suanne

/**
 * Controlador responsável por manipular operações relacionadas a Usuários.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    /**
     * Obtém todos os usuários cadastrados.
     *
     * @return ResponseEntity contendo a lista de UsuarioResponseDTO ou status 500 em caso de erro.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        try {
            // Busca todos os usuários no repositório e mapeia para UsuarioResponseDTO
            List<UsuarioResponseDTO> usuarios = usuarioRepository.findAll()
                    .stream()
                    .map(UsuarioResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    /**
     * Adiciona um novo usuário.
     *
     * @param data O objeto UsuarioRequestDTO contendo os dados do novo usuário.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody UsuarioRequestDTO data) {
        try {
            // Salva um novo usuário e um novo usuário comum associado a ele
            Usuario usuario = usuarioRepository.save(new Usuario(data.email(), data.senha()));
            UsuarioComum usuarioComum = usuarioComumRepository.save(new UsuarioComum(data.nome(), usuario));

            return ResponseEntity.status(HttpStatus.OK).body("{userId:"+ usuario.getId().toString() +"}");
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar usuário");
        }
    }

    /**
     * Edita um usuário existente com base no ID.
     *
     * @param id   O ID do usuário a ser editado.
     * @param data O objeto UsuarioRequestDTO contendo os novos valores para o usuário.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO data) {
        try {
            // Busca o usuário no repositório com base no ID
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));

            // Atualiza os valores do usuário com base nos dados fornecidos no UsuarioRequestDTO
            usuario.setEmail(data.email());
            usuario.setSenha(data.senha());

            // Verifica se o nome foi fornecido no DTO e o atualiza se necessário
            if (data.nome() != null) {
                UsuarioComum usuarioComum = usuario.getUsuarioComum();
                if (usuarioComum == null) {
                    usuarioComum = new UsuarioComum(data.nome(), usuario);
                    usuario.setUsuarioComum(usuarioComum);
                    usuarioComumRepository.save(usuarioComum);
                } else {
                    usuarioComum.setNome(data.nome());
                    usuarioComumRepository.save(usuarioComum);
                }
            } else {
                // Remove o usuário comum se o nome não estiver presente no DTO
                usuario.setUsuarioComum(null);
            }

            // Salva as alterações no repositório
            usuarioRepository.save(usuario);

            return ResponseEntity.ok("Usuário editado com sucesso");
        } catch (EntityNotFoundException e) {
            // Retorna um status 404 se o usuário não for encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar usuário");
        }
    }

    /**
     * Exclui um usuário com base no ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
            // Exclui o usuário do repositório
            usuarioRepository.deleteById(id);

            return ResponseEntity.ok("Usuário excluído com sucesso");
        } catch (EmptyResultDataAccessException e) {
            // Retorna um status 404 se o usuário não for encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o ID: " + id);
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário");
        }
    }

    /**
     * Obtém um usuário com base no ID.
     *
     * @param id O ID do usuário a ser encontrado.
     * @return ResponseEntity contendo o usuário encontrado ou status 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findUsuario(@PathVariable Long id) {
        try {
            // Busca o usuário no repositório com base no ID
            Optional<Usuario> usuario = usuarioRepository.findById(id);

            // Retorna o usuário encontrado ou status 404 se não encontrado
            return usuario.map(value -> new ResponseEntity<>(new UsuarioResponseDTO(value), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Autentica um usuário com base no e-mail e senha fornecidos.
     *
     * @param data O objeto UsuarioRequestDTO contendo o e-mail e a senha do usuário.
     * @return ResponseEntity contendo uma mensagem de sucesso ou erro.
     */
    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody UsuarioRequestDTO data) {
        try {
            // Busca o usuário pelo e-mail
            Usuario usuario = usuarioRepository.findByEmail(data.email());

            // Verifica se o usuário existe e se a senha está correta
            if (usuario != null && data.senha().equals(usuario.getSenha())) {
                return ResponseEntity.status(HttpStatus.OK).body("{userId:"+ usuario.getId().toString() +"}");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        } catch (Exception e) {
            // Retorna um erro interno do servidor em caso de exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao autenticar usuário");
        }
    }
}
