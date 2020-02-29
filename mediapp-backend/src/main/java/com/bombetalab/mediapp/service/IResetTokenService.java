package com.bombetalab.mediapp.service;

import com.bombetalab.mediapp.model.ResetToken;

public interface IResetTokenService {

	ResetToken findByToken(String token);

	void guardar(ResetToken token);

	void eliminar(ResetToken token);

}
