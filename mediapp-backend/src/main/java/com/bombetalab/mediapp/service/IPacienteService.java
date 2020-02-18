package com.bombetalab.mediapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bombetalab.mediapp.model.Paciente;

public interface IPacienteService extends ICRUD<Paciente, Integer> {

	Page<Paciente> listarPageable(Pageable pageable);
}
