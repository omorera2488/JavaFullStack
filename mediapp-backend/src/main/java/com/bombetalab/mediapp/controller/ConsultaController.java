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

import com.bombetalab.mediapp.dto.ConsultaExamenesDTO;
import com.bombetalab.mediapp.exception.ModeloNotFoundException;
import com.bombetalab.mediapp.model.Consulta;
import com.bombetalab.mediapp.service.IConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;

	@GetMapping
	public ResponseEntity<List<Consulta>> getAll() {
		List<Consulta> lista = consultaService.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> getObject(@PathVariable Integer id) {
		Consulta obj = consultaService.listarPorId(id);
		if (obj.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID no encontrado" + id);
		}
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarConsulta(@Valid @RequestBody Consulta obj) {
		Consulta objConsulta = consultaService.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objConsulta.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = "/consultaExamenes", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarConsultaExamenes(@Valid @RequestBody ConsultaExamenesDTO dto) {
		Consulta objConsulta = consultaService.registrarConsultaExamenes(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objConsulta.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Consulta> updateConsulta(@Valid @RequestBody Consulta obj) {
		Consulta objConsulta = consultaService.registrar(obj);
		return new ResponseEntity<Consulta>(objConsulta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteConsulta(@PathVariable Integer id) {
		boolean result = consultaService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
