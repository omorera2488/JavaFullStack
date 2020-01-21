package com.bombetalab.mediapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.Especialidad;
import com.bombetalab.mediapp.repo.IEspecialidadRepo;
import com.bombetalab.mediapp.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepo especialidadRepo;

	@Override
	public List<Especialidad> listar() {
		return especialidadRepo.findAll();
	}

	@Override
	public Especialidad listarPorId(Integer id) {
		Optional<Especialidad> op = especialidadRepo.findById(id);
		return op.isPresent() ? op.get() : new Especialidad();
	}

	@Override
	public Especialidad registrar(Especialidad obj) {
		return especialidadRepo.save(obj);
	}

	@Override
	public boolean eliminar(Integer id) {
		especialidadRepo.deleteById(id);
		return true;

	}

	@Override
	public Especialidad modificar(Especialidad obj) {
		return especialidadRepo.save(obj);
	}
}
