package com.bombetalab.mediapp.service.impl;

import java.util.List;

import com.bombetalab.mediapp.model.ConsultaExamen;

public interface IConsultaExamenService {

	List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta);
}
