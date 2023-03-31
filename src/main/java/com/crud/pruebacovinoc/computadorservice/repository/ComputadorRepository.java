package com.crud.pruebacovinoc.computadorservice.repository;
import com.crud.pruebacovinoc.computadorservice.entity.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * Repositorio Computador.
 */
@Repository
public interface ComputadorRepository extends JpaRepository<Computador,Long>, CrudRepository<Computador,Long> {
}
