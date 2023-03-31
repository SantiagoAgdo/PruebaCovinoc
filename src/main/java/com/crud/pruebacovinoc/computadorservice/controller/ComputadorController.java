package com.crud.pruebacovinoc.computadorservice.controller;

import com.crud.pruebacovinoc.computadorservice.entity.Computador;
import com.crud.pruebacovinoc.computadorservice.service.ComputadorService;

import com.crud.pruebacovinoc.usuarioservice.Entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.crud.pruebacovinoc.usuarioservice.controller.ControllerUsuario.mensaje;
import static com.crud.pruebacovinoc.usuarioservice.controller.ControllerUsuario.error;


/**
 * Controllador Computador.
 */
@RestController
@RequestMapping("/api/computador")
@Tag(name = "computadoras", description = "Modulo de computadoras")
public class ComputadorController {

private final ComputadorService computadorService;

    public ComputadorController(ComputadorService computadorService) {
        this.computadorService = computadorService;
    }

    /**
     * BUSCAR TODOS LOS USUARIOS
     */
    @Operation(description = "recupera todos las computadoras")
    @ApiResponse(responseCode = "200" ,description ="OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Computador.class))))
    @GetMapping("/todos")
    public List<Computador> mostrar() {
        return computadorService.buscarTodos();
    }

    /**
     * GUARDAR COMPUTADOR
     */
    @Operation(description = "Guarda computadora")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Computador computador){
        Computador computadorNew;
        Map<String, Object> response = new HashMap<>();

        try {
            computadorNew = computadorService.guardar(computador);
        } catch (DataAccessException e) {
            response.put(mensaje, "Error al realizar el insert en la base de datos");
            response.put(error, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(mensaje, "El computador ha sido creado con exito");
        response.put("computador", computadorNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * ACTUALIZAR UN COMPUTADOR POR SU ID
     */
    @Operation(description = "actualiza el computador por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "404",description = "PRODUCT NOT FOUND"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Computador computador, @PathVariable Long id) {
        Computador currentComputador = this.computadorService.buscarPorId(id);
        Computador computadorUpdated;
        Map<String, Object> response = new HashMap<>();
        if(currentComputador == null) {
            response.put(mensaje, "Error: no se pudo editar, El computador ID: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentComputador.setId(computador.getId());
            currentComputador.setMarca(computador.getMarca());
            currentComputador.setModelo(computador.getModelo());
            computadorUpdated = computadorService.guardar(currentComputador);
        } catch(DataAccessException e) {
            response.put(mensaje, "Error al actualizar en la base de datos");
            response.put(error,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(mensaje, "El computador ha sido actualizado con exito");
        response.put("computador", computadorUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * ELIMINAR USUARIO POR SU ID
     */
    @Operation(description = "elimina un computador por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "PRODUCT NOT FOUND"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Computador computador;
        try{
            computador = this.computadorService.buscarPorId(id);
            this.computadorService.eliminar(id);
        } catch (DataAccessException e) {
            response.put(mensaje, "Error al eliminar en la base de datos");
            response.put(error,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (computador == null) {
            response.put(mensaje,
                    "El computador con el id: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put(mensaje, "Computador eliminado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
