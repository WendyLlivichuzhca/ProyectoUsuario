package com.example.ProyectoUsuario.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoUsuario.models.entity.Usuario;
import com.example.ProyectoUsuario.models.services.UsuarioServiceImpl;


@RestController
@RequestMapping("/app")
public class UsuarioRestController {
	
	@Autowired
    private UsuarioServiceImpl UsuarioService;

    @GetMapping("/ListarUsuario")
    public ResponseEntity<List<Usuario>> listar() {
        return new ResponseEntity<>(UsuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/BuscarUsuario/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> buscar = Optional.ofNullable(UsuarioService.findById(id));
        return buscar.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/GuardarUsuario")
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario) {
        try {
        	Usuario usuarioGuardado = UsuarioService.save(usuario);
            return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }

    @PutMapping("/ModificarUsuario")
    public ResponseEntity<Usuario> modificar(@RequestBody Usuario usuario) {
        if (usuario.getIdUsuario() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 
        }

        Usuario existente = UsuarioService.findById(usuario.getIdUsuario());
        if (existente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario usuarioActualizado = UsuarioService.save(usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }


    @DeleteMapping("/EliminarUsuario/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    	UsuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
