package com.bombetalab.mediapp.service;

import com.bombetalab.mediapp.model.Archivo;

public interface IArchivoService {

	int guardar(Archivo archivo);

	byte[] leerArchivo(Integer idArchivo);
}
