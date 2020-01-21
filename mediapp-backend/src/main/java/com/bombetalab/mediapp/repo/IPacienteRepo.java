package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.Paciente;

public interface IPacienteRepo extends JpaRepository<Paciente, Integer> {

}
