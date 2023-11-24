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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<UsuarioResponseDTO> getAll(){
        return usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void addUsuario(@RequestBody UsuarioRequestDTO data) {
       // System.out.println("teste1 add");
      Usuario usuario = usuarioRepository.save(new Usuario(data.email(), data.senha()));
        UsuarioComum usuarioComum = usuarioComumRepository.save(new UsuarioComum(data.nome(), usuario));

    }

    @PutMapping("/{id}")
    public void editarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO data) {
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
    }



    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) { usuarioRepository.deleteById(id);}
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.map(value -> new ResponseEntity<>(new UsuarioResponseDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
