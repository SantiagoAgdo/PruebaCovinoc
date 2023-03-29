package com.crud.PruebaCovinoc.Service;

import com.crud.PruebaCovinoc.Entity.Usuario;
import com.crud.PruebaCovinoc.Repository.RepositoryUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceUsuario {
    private RepositoryUsuario repositoryUsuario;

    public ServiceUsuario(RepositoryUsuario repositoryUsuario) {
        this.repositoryUsuario = repositoryUsuario;
    }

    public List<Usuario> findAll(){
        return repositoryUsuario.findAll();
    }

    public  Usuario save(Usuario usuario){
        return repositoryUsuario.save(usuario);
    }
}
