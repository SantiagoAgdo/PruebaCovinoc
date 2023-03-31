package com.crud.pruebacovinoc.Controller;

import com.crud.pruebacovinoc.Entity.Usuario;
import com.crud.pruebacovinoc.Service.ServiceUsuario;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ControllerUsuario {
    private static final String mensaje = "mensaje";
    private static final String error = "error";

    private final ServiceUsuario serviceUsuario;

    public ControllerUsuario(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @GetMapping("/usuario")
    public List<Usuario> mostrar() {
        return serviceUsuario.buscarTodos();
    }


    @PostMapping("/usuario/save")
    public ResponseEntity guardar(@RequestBody Usuario usuario){
        Usuario usuarioNew;
        Map<String, Object> response = new HashMap<>();

        try {
            usuarioNew = serviceUsuario.guardar(usuario);
        } catch (DataAccessException e) {
            response.put(mensaje, "Error al realizar el insert en la base de datos");
            response.put(error, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(mensaje, "El cliente ha sido creado con exito");
        response.put("cliente", usuarioNew);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("usuario/{username}")
    public ResponseEntity mostrarPorNombre(@PathVariable String username){
        Usuario usuario;
        Map<String, Object> response = new HashMap<>();

        try {
            usuario = serviceUsuario.buscarPorNombre(username);
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
    @DeleteMapping("usuario/{id}")
    public ResponseEntity eliminarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Usuario usuario;
try{
     usuario = this.serviceUsuario.buscarPorId(id);
    this.serviceUsuario.eliminar(id);
} catch (DataAccessException e) {
    response.put(mensaje, "Error al eliminar en la base de datos");
    response.put(error,e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}
        if (usuario == null) {
            response.put(mensaje,
                    "El usuario con el id: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put(mensaje, "El cliente eliminado con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}