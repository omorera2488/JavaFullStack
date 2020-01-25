package com.bombetalab.mediapp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bombetalab.mediapp.exception.ModeloNotFoundException;
import com.bombetalab.mediapp.model.Medico;
import com.bombetalab.mediapp.service.IMedicoService;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;

	@GetMapping
	public ResponseEntity<List<Medico>> getAll() {
		List<Medico> lista = medicoService.listar();
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> getMembers(@PathVariable Integer id) {
		Medico obj = medicoService.listarPorId(id);
		if (obj.getIdMedico() == null) {
			throw new ModeloNotFoundException("ID no encontrado" + id);
		}
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarMedico(@Valid @RequestBody Medico obj) {
		Medico objMedico = medicoService.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objMedico.getIdMedico()).toUri();
		// return new ResponseEntity<Medico>(objMedico, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Medico> updateMedico(@Valid @RequestBody Medico obj) {
		Medico objMedico = medicoService.registrar(obj);
		return new ResponseEntity<Medico>(objMedico, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMedico(@PathVariable Integer id) {
		boolean result = medicoService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
