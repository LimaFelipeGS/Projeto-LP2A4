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

@RestController
@CrossOrigin("*")
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        try {
            List<UsuarioResponseDTO> usuarios = usuarioRepository.findAll()
                    .stream()
                    .map(UsuarioResponseDTO::new)
                    .toList();

            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody UsuarioRequestDTO data) {
        try{
       // System.out.println("teste1 add");
      Usuario usuario = usuarioRepository.save(new Usuario(data.email(), data.senha()));
        UsuarioComum usuarioComum = usuarioComumRepository.save(new UsuarioComum(data.nome(), usuario));
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário adicionado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar usuário");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO data) {
        try {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + id));
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());

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
            usuario.setUsuarioComum(null);
        }
        usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuário editado com sucesso");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar usuário");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
        usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuário excluído com sucesso");
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir usuário");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findUsuario(@PathVariable Long id) {
       try{
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.map(value -> new ResponseEntity<>(new UsuarioResponseDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }
                                        // Autenticação
    @PostMapping("/login")
    public ResponseEntity autenticarUsuario(@RequestBody UsuarioRequestDTO data) {
        // Busca o usuário pelo e-mail
        Usuario usuario = usuarioRepository.findByEmail(data.email());
        // Verifica se o usuário existe e a senha está correta
        usuario.setUsuarioComum(null) ;
       if (usuario != null){
           if (data.senha().equals(usuario.getSenha())){
               System.out.println("teste1 login");
               return ResponseEntity.status(HttpStatus.OK).body("Operação bem-sucedida");
           }
       }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Algo deu errado");
    }
}
