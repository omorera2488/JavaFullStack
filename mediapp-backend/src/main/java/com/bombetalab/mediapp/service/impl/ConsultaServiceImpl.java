package com.bombetalab.mediapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.dto.ConsultaExamenesDTO;
import com.bombetalab.mediapp.model.Consulta;
import com.bombetalab.mediapp.repo.IConsultaExamenRepo;
import com.bombetalab.mediapp.repo.IConsultaRepo;
import com.bombetalab.mediapp.service.IConsultaService;

@Service
public class ConsultaServiceImpl implements IConsultaService {

	@Autowired
	private IConsultaRepo consultaRepo;
	@Autowired
	private IConsultaExamenRepo consultaExamenRepo;

	@Override
	public List<Consulta> listar() {
		return consultaRepo.findAll();
	}

	@Override
	public Consulta listarPorId(Integer id) {
		Optional<Consulta> op = consultaRepo.findById(id);
		return op.isPresent() ? op.get() : new Consulta();
	}

	@Override
	public Consulta registrar(Consulta obj) {
		obj.getDetalleConsulta().forEach(detalle -> detalle.setConsulta(obj)); // Lambda
		return consultaRepo.save(obj);
	}

	@Transactional
	@Override
	public Consulta registrarConsultaExamenes(ConsultaExamenesDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(detalle -> detalle.setConsulta(dto.getConsulta())); // Lambda
		consultaRepo.save(dto.getConsulta());
		dto.getListaExamenes().forEach(examen -> consultaExamenRepo
				.registrarConsultaExamen(dto.getConsulta().getIdConsulta(), examen.getIdExamen()));
		return dto.getConsulta();
	}

	@Override
	public boolean eliminar(Integer id) {
		consultaRepo.deleteById(id);
		return true;

	}

	@Override
	public Consulta modificar(Consulta obj) {
		return consultaRepo.save(obj);
	}
}
