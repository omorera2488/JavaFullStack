package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.Consulta;

public interface IConsultaRepo extends JpaRepository<Consulta, Integer> {

}
