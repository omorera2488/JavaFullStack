package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.Examen;

public interface IExamenRepo extends JpaRepository<Examen, Integer> {

}
