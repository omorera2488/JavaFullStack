package com.bombetalab.mediapp.controller;

import java.util.List;

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

import com.bombetalab.mediapp.model.Paciente;
import com.bombetalab.mediapp.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;

	@GetMapping
	public ResponseEntity<List<Paciente>> getAll() {
		List<Paciente> lista = pacienteService.listar();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getMembers(@PathVariable Integer id) {
		Paciente obj = pacienteService.listarPorId(id);

		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente obj) {
		Paciente objPaciente = pacienteService.registrar(obj);
		return new ResponseEntity<Paciente>(objPaciente, HttpStatus.CREATED);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Paciente> updatePaciente(@RequestBody Paciente obj) {
		Paciente objPaciente = pacienteService.registrar(obj);
		return new ResponseEntity<Paciente>(objPaciente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePaciente(@PathVariable Integer id) {
		boolean result = pacienteService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
