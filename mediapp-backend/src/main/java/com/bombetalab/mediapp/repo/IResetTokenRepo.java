package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer> {

	// from ResetToken rt where rt.token = :?
	ResetToken findByToken(String token);

}
