package com.bombetalab.mediapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.Medico;
import com.bombetalab.mediapp.repo.IMedicoRepo;
import com.bombetalab.mediapp.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepo medicoRepo;

	@Override
	public List<Medico> listar() {
		return medicoRepo.findAll();
	}

	@Override
	public Medico listarPorId(Integer id) {
		Optional<Medico> op = medicoRepo.findById(id);
		return op.isPresent() ? op.get() : new Medico();
	}

	@Override
	public Medico registrar(Medico obj) {
		return medicoRepo.save(obj);
	}

	@Override
	public boolean eliminar(Integer id) {
		medicoRepo.deleteById(id);
		return true;

	}

	@Override
	public Medico modificar(Medico obj) {
		return medicoRepo.save(obj);
	}
}
