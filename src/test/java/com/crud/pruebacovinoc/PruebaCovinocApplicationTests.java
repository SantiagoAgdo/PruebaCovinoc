package com.crud.pruebacovinoc;

import com.crud.pruebacovinoc.usuarioservice.Entity.Usuario;
import com.crud.pruebacovinoc.usuarioservice.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PruebaCovinocApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	@Rollback(value = false)
	public void testGuardarUsuario(){
		Usuario usuario = new Usuario("prueba","1234");
		Usuario usuarioGuardado =  usuarioRepository.save(usuario);
		assertNotNull(usuarioGuardado);

	}

	@Test
	@Rollback(value = false)
	public void testGuardarUsuario2(){
		Usuario usuario = new Usuario("prueba2","4321");
		Usuario usuarioGuardado =  usuarioRepository.save(usuario);
		assertNotNull(usuarioGuardado);

	}

	@Test
	@Rollback(value = false)
	public void testGuardarUsuario3(){
		Usuario usuario = new Usuario("prueba3","9876");
		Usuario usuarioGuardado =  usuarioRepository.save(usuario);
		assertNotNull(usuarioGuardado);

	}
	@Test
	public void buscarUsuarioPorNombre(){
		String nombre = "prueba2";
		Usuario usuario  = usuarioRepository.findByusername(nombre);
		assertEquals(usuario.getUsername(),nombre);
	}

	@Rollback(value = false)
	@Test
	public void actualizar(){
		String nombreNuevo = "pruebaTest";
		Usuario usuario = new Usuario(nombreNuevo,"9876");
		usuario.setId(1L);
		usuarioRepository.save(usuario);
		Usuario usuarioActualizado = usuarioRepository.findByusername(nombreNuevo);
		assertEquals(usuarioActualizado.getUsername(),nombreNuevo);
	}

	@Test
	public void listarUsuario(){
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		for (Usuario usuario : usuarios){
			System.out.println(usuario);
		}
		assertNotNull(usuarios);
	}
	@Test
	public void eliminar(){
		Long id = 3L;
		boolean existeIdAntes = usuarioRepository.findById(id).isPresent();
		usuarioRepository.deleteById(id);

		boolean noExisteIdDespues = usuarioRepository.findById(id).isPresent();
		assertTrue(existeIdAntes);
		assertFalse(noExisteIdDespues);
	}
}
