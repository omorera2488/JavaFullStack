package com.bombetalab.mediapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bombetalab.mediapp.model.ConsultaExamen;
import com.bombetalab.mediapp.service.impl.IConsultaExamenService;

@RestController
@RequestMapping("/consultaExamenes")
public class ConsultaExamenController {

	@Autowired
	private IConsultaExamenService consultaExamenService;
	
	@GetMapping(value = "/{idConsulta}")
	public ResponseEntity<List<ConsultaExamen>> listar(@PathVariable("idConsulta") Integer idConsulta){
		List<ConsultaExamen> consultaExamen = new ArrayList<ConsultaExamen>();
		consultaExamen = consultaExamenService.listarExamenesPorConsulta(idConsulta);
		return new ResponseEntity<List<ConsultaExamen>>(consultaExamen, HttpStatus.OK);
	}
}
