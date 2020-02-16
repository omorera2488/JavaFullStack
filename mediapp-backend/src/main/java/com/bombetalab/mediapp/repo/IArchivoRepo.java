package com.bombetalab.mediapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bombetalab.mediapp.model.Archivo;

public interface IArchivoRepo extends JpaRepository<Archivo, Integer> {

}
