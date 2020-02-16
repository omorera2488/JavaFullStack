package com.bombetalab.mediapp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.model.Archivo;
import com.bombetalab.mediapp.repo.IArchivoRepo;
import com.bombetalab.mediapp.service.IArchivoService;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private IArchivoRepo archivoRepo;

	@Override
	public int guardar(Archivo archivo) {
		Archivo ar = archivoRepo.save(archivo);
		return ar.getIdArchivo() > 0 ? 1 : 0;
	}

	@Override
	public byte[] leerArchivo(Integer idArchivo) {
		Optional<Archivo> op = archivoRepo.findById(idArchivo);
		return op.isPresent() ? op.get().getValue() : new byte[0];
	}

}
