package com.crud.PruebaCovinoc.Service;

import com.crud.PruebaCovinoc.Entity.Usuario;
import com.crud.PruebaCovinoc.Repository.RepositoryUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceUsuario {
    private RepositoryUsuario repositoryUsuario;

    public ServiceUsuario(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    public List<Usuario> buscarTodos(){
        return repositoryUsuario.findAll();
    }

    public  Usuario guardar(Usuario usuario){
        return repositoryUsuario.save(usuario);
    }

    public  Usuario buscarPorNombre(String usuario){
        return (Usuario) repositoryUsuario.findByusername(usuario);
    }

}
