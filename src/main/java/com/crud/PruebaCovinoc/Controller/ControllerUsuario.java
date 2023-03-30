package com.crud.PruebaCovinoc.Controller;

import com.crud.PruebaCovinoc.Entity.Usuario;
import com.crud.PruebaCovinoc.Service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
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

@Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/usuario")
    public List<Usuario> Mostrar() {
        return serviceUsuario.buscarTodos();
    }


    @PostMapping("/usuario/save")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        Usuario usuarioNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            usuarioNew = serviceUsuario.guardar(usuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", usuarioNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("usuario/{username}")
    public ResponseEntity<?> mostrarPorNombre(@PathVariable String username){
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
            usuario = serviceUsuario.buscarPorNombre(username);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (usuario == null) {
            response.put("mensaje",
                    "El usuario con el username: ".concat(username.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        Usuario usuario = null;
try{
     usuario = this.serviceUsuario.buscarPorId(id);
    this.serviceUsuario.eliminar(id);
} catch (DataAccessException e) {
    response.put("mensaje", "Error al eliminar en la base de datos");
    response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
    return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}
        if (usuario == null) {
            response.put("mensaje",
                    "El usuario con el id: ".concat(id.toString().concat(" no existe en nuestra base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje", "El cliente eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

}