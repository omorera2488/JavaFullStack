package com.bombetalab.mediapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.Paciente;
import com.bombetalab.mediapp.repo.IPacienteRepo;
import com.bombetalab.mediapp.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteRepo pacienteRepo;

	@Override
	public List<Paciente> listar() {
		return pacienteRepo.findAll();
	}

	@Override
	public Paciente listarPorId(Integer id) {
		Optional<Paciente> op = pacienteRepo.findById(id);
		return op.isPresent() ? op.get() : new Paciente();
	}

	@Override
	public Paciente registrar(Paciente obj) {
		return pacienteRepo.save(obj);
	}

	@Override
	public boolean eliminar(Integer id) {
		pacienteRepo.deleteById(id);
		return true;

	}

	@Override
	public Paciente modificar(Paciente obj) {
		return pacienteRepo.save(obj);
	}

}
