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
import com.bombetalab.mediapp.model.Examen;
import com.bombetalab.mediapp.service.impl.IExamenService;

@RestController
@RequestMapping("/examenes")
public class ExamenController {
	@Autowired
	private IExamenService examenService;

	@GetMapping
	public ResponseEntity<List<Examen>> getAll() {
		List<Examen> lista = examenService.listar();
		return new ResponseEntity<List<Examen>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Examen> getMembers(@PathVariable Integer id) {
		Examen obj = examenService.listarPorId(id);
		if (obj.getIdExamen() == null) {
			throw new ModeloNotFoundException("ID no encontrado" + id);
		}
		return new ResponseEntity<Examen>(obj, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarExamen(@Valid @RequestBody Examen obj) {
		Examen objExamen = examenService.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objExamen.getIdExamen()).toUri();
		// return new ResponseEntity<Examen>(objExamen, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Examen> updateExamen(@Valid @RequestBody Examen obj) {
		Examen objExamen = examenService.registrar(obj);
		return new ResponseEntity<Examen>(objExamen, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteExamen(@PathVariable Integer id) {
		boolean result = examenService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
