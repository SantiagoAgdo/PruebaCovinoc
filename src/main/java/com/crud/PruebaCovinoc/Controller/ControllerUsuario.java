package com.crud.PruebaCovinoc.Controller;

import com.crud.PruebaCovinoc.Entity.Usuario;
import com.crud.PruebaCovinoc.Service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControllerUsuario {

@Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/usuario")
    public List<Usuario> Mostrar() {
        return serviceUsuario.buscarTodos();
    }
    @PostMapping("/usuario/save")
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        return new ResponseEntity<>(serviceUsuario.guardar(usuario), HttpStatus.CREATED);
    }
/*
    @GetMapping("usuario/{username}")
    public ResponseEntity<?> mostrarPorNombre(@PathVariable String username){
        return new ResponseEntity<>(serviceUsuario.buscarPorNombre(username), HttpStatus.OK);
    }
*/
}