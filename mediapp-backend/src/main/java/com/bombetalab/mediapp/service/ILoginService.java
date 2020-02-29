package com.bombetalab.mediapp.service;

import com.bombetalab.mediapp.model.Usuario;

public interface ILoginService {

	Usuario verificarNombreUsuario(String usuario);

	void cambiarClave(String clave, String nombre);
}
