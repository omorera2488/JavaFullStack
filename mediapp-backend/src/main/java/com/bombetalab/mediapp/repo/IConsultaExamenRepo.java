package com.bombetalab.mediapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bombetalab.mediapp.model.ConsultaExamen;

public interface IConsultaExamenRepo extends JpaRepository<ConsultaExamen, Integer> {

	@Modifying
	@Query(value = "INSERT INTO consulta_examen(id_consulta, id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
	Integer registrarConsultaExamen(@Param("idConsulta") Integer idConsuta, @Param("idExamen") Integer idExamen);
	
	@Query("from ConsultaExamen ce where ce.consulta.idConsulta = :idConsulta")
	List<ConsultaExamen> listarExamenesPorConsulta(@Param("idConsulta") Integer idConsulta);
}
