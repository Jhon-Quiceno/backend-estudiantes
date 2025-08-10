package com.backend.estudiantes.repository;

import com.backend.estudiantes.model.Role;
import com.backend.estudiantes.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RequiredArgsConstructor no se puede usar para test
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository; //inyeccion de dependecias para poder usar y hacer los test

    //Para el test [nombreDelMetodo]_[estadoEnPrueba]_[comportamientoEsperado] nombre del test
    @Test
    void findByEmail_UsuarioExiste_RetornaUsuario() {
        //constructor de usuario

        Usuario testUser = new Usuario(
                "Test",
                "User",
                "test@unicorodoba.edu.co",
                "123",
                Role.ESTUDIANTE
        );

        //guardando usuario teporalmente
        Usuario guardado = usuarioRepository.save(testUser); //guardamos el usuario en la base de datos de prueba

        //estamos haceindo busqueda y devolviendolo en un Optional
        Optional<Usuario> encontradoOptional = usuarioRepository.findByEmail(guardado.getEmail()); //buscamos el usuario por email

        //validando por si falla
        assertTrue(encontradoOptional.isPresent(), "El Usuario deberia exisitir en la base de datos"); //verificamos que el usuario fue encontrado

        //obtener realmente el usuario
        Usuario encontrado = encontradoOptional.get();

        //verificamos que el usuario encontrado sea igual al guardado
        assertEquals("test@unicorodoba.edu.co", encontrado.getEmail(),
                "El email del usuario encontrado deberia ser igual al guardado");

    }
}
