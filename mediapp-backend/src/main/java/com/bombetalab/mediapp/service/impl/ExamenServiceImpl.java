package com.bombetalab.mediapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.Examen;
import com.bombetalab.mediapp.repo.IExamenRepo;

@Service
public class ExamenServiceImpl implements IExamenService {
	@Autowired
	private IExamenRepo consultaRepo;

	@Override
	public List<Examen> listar() {
		return consultaRepo.findAll();
	}

	@Override
	public Examen listarPorId(Integer id) {
		Optional<Examen> op = consultaRepo.findById(id);
		return op.isPresent() ? op.get() : new Examen();
	}

	@Override
	public Examen registrar(Examen obj) {
		return consultaRepo.save(obj);
	}

	@Override
	public boolean eliminar(Integer id) {
		consultaRepo.deleteById(id);
		return true;

	}

	@Override
	public Examen modificar(Examen obj) {
		return consultaRepo.save(obj);
	}
}
