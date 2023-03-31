package com.crud.pruebacovinoc.Service;

import com.crud.pruebacovinoc.Entity.Usuario;
import com.crud.pruebacovinoc.Repository.RepositoryUsuario;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public Usuario buscarPorId(Long id){
        return repositoryUsuario.findById(id).orElse(null);
    }
    public  void eliminar(Long id){
    repositoryUsuario.deleteById(id);
    }
    public  Usuario buscarPorNombre(String usuario){

        return repositoryUsuario.findByusername(usuario);
    }

}
