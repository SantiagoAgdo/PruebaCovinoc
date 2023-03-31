package com.crud.pruebacovinoc.usuarioservice.repository;

import com.crud.pruebacovinoc.usuarioservice.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Servicio Usuario.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>, CrudRepository<Usuario,Long> {
    Usuario findByusername(String username);

}
