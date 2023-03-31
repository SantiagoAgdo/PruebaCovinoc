package com.crud.pruebacovinoc.usuarioservice.service;

import com.crud.pruebacovinoc.usuarioservice.Entity.Usuario;
import com.crud.pruebacovinoc.usuarioservice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio Usuario.
 */
@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    /**
     * Busca todos los usuarios.
     *
     * @return Todos los usuarios creados.
     */
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param usuario El usuario nuevo.
     * @return El usuario creado.
     */
    public  Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca y retorna un usuario por su ID.
     *
     * @param id El ID del usuario a buscar.
     * @return El usuario con el ID dado, o null si no se encontró.
     */
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }
    /**
     * Elimina un usuario existente.
     *
     * @param id El ID del usuario a eliminar.
     */
    public  void eliminar(Long id){
    usuarioRepository.deleteById(id);
    }

    /**
     * Busca y retorna un usuario por su nombre.
     *
     * @param usuario El nombre del usuario a buscar.
     * @return El usuario con el ID dado.
     */
    public  Usuario buscarPorNombre(String usuario){

        return usuarioRepository.findByusername(usuario);
    }


}
