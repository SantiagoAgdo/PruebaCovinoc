package com.crud.pruebacovinoc.computadorservice.service;
import com.crud.pruebacovinoc.computadorservice.entity.Computador;
import com.crud.pruebacovinoc.computadorservice.repository.ComputadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Servicio Computador.
 */
@Service
public class ComputadorService {

private ComputadorRepository computadorRepository;
    public ComputadorService(ComputadorRepository computadorRepository) {
        this.computadorRepository = computadorRepository;
    }
    /**
     * Busca todos los computadores.
     *
     * @return Todos los computadores creados.
     */
    public List<Computador> buscarTodos(){
        return computadorRepository.findAll();
    }

    /**
     * Crea un nuevo usuario.
     *
     * @param computador ES el computador nuevo.
     * @return El computador creado.
     */
    public  Computador guardar(Computador computador){
        return computadorRepository.save(computador);
    }

    /**
     * Busca y retorna un computador por su ID.
     *
     * @param id El ID del computador a buscar.
     * @return El computador con el ID dado, o null si no se encontró.
     */
    public Computador buscarPorId(Long id){
        return computadorRepository.findById(id).orElse(null);
    }
    /**
     * Elimina un computador existente.
     *
     * @param id El ID del computador a eliminar.
     */
    public  void eliminar(Long id){
        computadorRepository.deleteById(id);
    }
}
