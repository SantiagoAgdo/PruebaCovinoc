package com.crud.PruebaCovinoc.Repository;

import com.crud.PruebaCovinoc.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario,Long>, CrudRepository<Usuario,Long> {
    Usuario findByusername(String username);

}
