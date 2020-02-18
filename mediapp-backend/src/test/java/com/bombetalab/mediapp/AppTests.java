package com.bombetalab.mediapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bombetalab.mediapp.model.Usuario;
import com.bombetalab.mediapp.repo.IUsuarioRepo;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class AppTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IUsuarioRepo usuarioRepository;
	
	
	@Test
	void crearUsuario() {
		Usuario us = new Usuario();
		us.setUsername("mitotest21@gmail.com");
		us.setPassword(bcrypt.encode("123"));
		us.setEnabled(true);
		
		Usuario retorno = usuarioRepository.save(us);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
	}

}
