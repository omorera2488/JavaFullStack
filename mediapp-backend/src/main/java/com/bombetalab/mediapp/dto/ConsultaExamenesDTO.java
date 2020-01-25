package com.bombetalab.mediapp.dto;

import java.util.List;

import com.bombetalab.mediapp.model.Consulta;
import com.bombetalab.mediapp.model.Examen;

public class ConsultaExamenesDTO {
	private Consulta consulta;
	private List<Examen> listaExamenes;

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Examen> getListaExamenes() {
		return listaExamenes;
	}

	public void setListaExamenes(List<Examen> listaExamenes) {
		this.listaExamenes = listaExamenes;
	}

}
