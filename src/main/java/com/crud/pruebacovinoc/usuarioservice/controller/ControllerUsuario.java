package com.crud.pruebacovinoc.usuarioservice.controller;

import com.crud.pruebacovinoc.usuarioservice.Entity.Usuario;
import com.crud.pruebacovinoc.usuarioservice.service.UsuarioService;
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

/*
 * controlador de usuarios con sus endpoints correspondientes a el CRUD */
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "usuarios", description = "Modulo de usuarios")
public class ControllerUsuario {
    public static final String mensaje = "mensaje";
    public static final String error = "error";

    private final UsuarioService usuarioService;
    /**
     * Controllador Usuario.
     */
    public ControllerUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * BUSCAR TODOS LOS USUARIOS
     */
    @Operation(description = "recupera todos los usuarios")
    @ApiResponse(responseCode = "200" ,description ="OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
    @GetMapping("/todos")
    public List<Usuario> mostrar() {
        return usuarioService.buscarTodos();
    }

    /**
     * BUSCAR POR USERNAME EN LOS USUARIOS
     */
    @Operation(description = "recupera el usuario por su username")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
            @ApiResponse(responseCode = "404",description = "PRODUCT NOT FOUND"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @GetMapping("/{username}")
    public ResponseEntity mostrarPorNombre(@PathVariable String username){
        Usuario usuario;
        Map<String, Object> response = new HashMap<>();

        try {
            usuario = usuarioService.buscarPorNombre(username);
        } catch (DataAccessException e) {
            response.put(mensaje, "Error al realizar la consulta en la base de datos");
            response.put(error, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (usuario == null) {
            response.put(mensaje,
                    "El usuario con el username: ".concat(username.concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    /**
     * GUARDAR USUARIO
     */
    @Operation(description = "Guarda usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody Usuario usuario){
        Usuario usuarioNew;
        Map<String, Object> response = new HashMap<>();

        try {
            usuarioNew = usuarioService.guardar(usuario);
        } catch (DataAccessException e) {
            response.put(mensaje, "Error al realizar el insert en la base de datos");
            response.put(error, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(mensaje, "El usuario ha sido creado con exito");
        response.put("usuario", usuarioNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * ELIMINAR USUARIO POR SU ID
     */
    @Operation(description = "elimina el usuario por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "404",description = "PRODUCT NOT FOUND"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Usuario usuario;
try{
     usuario = this.usuarioService.buscarPorId(id);
    this.usuarioService.eliminar(id);
} catch (DataAccessException e) {
    response.put(mensaje, "Error al eliminar en la base de datos");
    response.put(error, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}
        if (usuario == null) {
            response.put(mensaje,
                    "El usuario con el id: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        response.put(mensaje, "Usuario eliminado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /**
     * ACTUALIZAR USUARIO POR SU ID
     */
    @Operation(description = "actualiza el usuario por su id")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "CREATED"),
            @ApiResponse(responseCode = "404",description = "PRODUCT NOT FOUND"),
            @ApiResponse(responseCode = "500",description = "INTERNAL_SERVER_ERROR")
    })
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.buscarPorId(id);
        Usuario usuarioUpdated;
        Map<String, Object> response = new HashMap<>();
        if(currentUsuario == null) {
            response.put(mensaje, "Error: no se pudo editar, El usuario ID: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try {
            currentUsuario.setUsername(usuario.getUsername());
            currentUsuario.setPassword(usuario.getPassword());
            currentUsuario.setComputadoras(usuario.getComputadoras());
            usuarioUpdated = usuarioService.guardar(currentUsuario);
        } catch(DataAccessException e) {
            response.put(mensaje, "Error al actualizar en la base de datos");
            response.put(error,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(mensaje, "El usuario ha sido actualizado con exito");
        response.put("usuario", usuarioUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}