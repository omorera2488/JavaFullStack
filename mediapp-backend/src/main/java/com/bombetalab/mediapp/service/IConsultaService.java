package com.bombetalab.mediapp.service;

import java.util.List;

import com.bombetalab.mediapp.dto.ConsultaExamenesDTO;
import com.bombetalab.mediapp.dto.FiltroConsultaDTO;
import com.bombetalab.mediapp.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta, Integer> {
	public Consulta registrarConsultaExamenes(ConsultaExamenesDTO dto);

	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro);

	public List<Consulta> buscar(FiltroConsultaDTO filtro);
}
