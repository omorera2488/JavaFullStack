package com.bombetalab.mediapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.ConsultaExamen;
import com.bombetalab.mediapp.repo.IConsultaExamenRepo;
import com.bombetalab.mediapp.service.impl.IConsultaExamenService;

@Service
public class ConsultaExamenService implements IConsultaExamenService{

	@Autowired
	private IConsultaExamenRepo consultaExamenRepo;

	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta) {
		return consultaExamenRepo.listarExamenesPorConsulta(idConsulta);
	}

}
