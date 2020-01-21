package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.Medico;

public interface IMedicoRepo extends JpaRepository<Medico, Integer> {

}
