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
    public List<Usuario> index() {
        return serviceUsuario.findAll();
    }
    @PostMapping("/usuario/save")
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        return new ResponseEntity<>(serviceUsuario.save(usuario), HttpStatus.CREATED);
    }


}