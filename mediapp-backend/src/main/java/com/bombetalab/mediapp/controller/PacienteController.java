package com.bombetalab.mediapp.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Paciente>> getAllPageable(Pageable pageable) {
		Page<Paciente> pagina = pacienteService.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pagina, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getObject(@PathVariable Integer id) {
		Paciente obj = pacienteService.listarPorId(id);
		if (obj.getIdPaciente() == null) {
			throw new ModeloNotFoundException("ID no encontrado" + id);
		}
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}
	
	/*// Ejemplo Heateoas
	@GetMapping("/hateoas/{id}")
	public EntityModel<Paciente> listarPorHateas(@PathVariable Integer id) {
		Paciente obj = pacienteService.listarPorId(id);

		EntityModel<Paciente> recurso = new EntityModel<Paciente>(obj);
		WebMvcLinkBuilder linkTo = LinkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(linkTo.withRel("paciente-resource"));
		return recurso;
	}*/
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrarPaciente(@Valid @RequestBody Paciente obj) {
		Paciente objPaciente = pacienteService.registrar(obj);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(objPaciente.getIdPaciente()).toUri();
		// return new ResponseEntity<Paciente>(objPaciente, HttpStatus.CREATED);
		return ResponseEntity.created(location).build();
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<Paciente> updatePaciente(@Valid @RequestBody Paciente obj) {
		Paciente objPaciente = pacienteService.registrar(obj);
		return new ResponseEntity<Paciente>(objPaciente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePaciente(@PathVariable Integer id) {
		boolean result = pacienteService.eliminar(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
	}
}
