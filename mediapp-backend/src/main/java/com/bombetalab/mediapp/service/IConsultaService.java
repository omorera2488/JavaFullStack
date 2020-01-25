package com.bombetalab.mediapp.service;

import com.bombetalab.mediapp.dto.ConsultaExamenesDTO;
import com.bombetalab.mediapp.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer> {
	public Consulta registrarConsultaExamenes(ConsultaExamenesDTO dto);
}
