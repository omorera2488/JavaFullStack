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
import com.bombetalab.mediapp.model.Especialidad;
import com.bombetalab.mediapp.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService especialidadService;

	@GetMapping
	public ResponseEntity<List<Especialidad>> getAll() {
		List<Especialidad> lista = especialidadService.listar();
		return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Especialidad> getMembers(@PathVariable Integer id) {
		Especialidad obj = especialidadService.listarPorId(id);
		if (obj.getIdEspecialidad() == null) {
			throw new ModeloNotFoundException("ID no encontrado" + id);
		}
		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarEspecialidad(@Valid @RequestBody Especialidad obj) {
		Especialidad objEspecialidad = especialidadService.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objEspecialidad.getIdEspecialidad()).toUri();
		// return new ResponseEntity<Especialidad>(objEspecialidad, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Especialidad> updateEspecialidad(@Valid @RequestBody Especialidad obj) {
		Especialidad objEspecialidad = especialidadService.registrar(obj);
		return new ResponseEntity<Especialidad>(objEspecialidad, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEspecialidad(@PathVariable Integer id) {
		boolean result = especialidadService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
