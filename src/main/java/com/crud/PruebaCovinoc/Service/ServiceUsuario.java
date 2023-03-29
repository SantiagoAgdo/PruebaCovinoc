package com.crud.PruebaCovinoc.Service;

import com.crud.PruebaCovinoc.Entity.Usuario;
import com.crud.PruebaCovinoc.Repository.RepositoryUsuario;

import java.util.List;

public class ServiceUsuario {
    private RepositoryUsuario repositoryUsuario;

    public List<Usuario> findAll(){
        return repositoryUsuario.findAll();
    }

    public  Usuario save(Usuario usuario){
        return repositoryUsuario.save(usuario);
    }
}
