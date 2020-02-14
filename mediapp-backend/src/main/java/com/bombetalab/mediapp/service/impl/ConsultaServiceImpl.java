package com.bombetalab.mediapp.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bombetalab.mediapp.dto.ConsultaExamenesDTO;
import com.bombetalab.mediapp.dto.ConsultaResumenDTO;
import com.bombetalab.mediapp.dto.FiltroConsultaDTO;
import com.bombetalab.mediapp.model.Consulta;
import com.bombetalab.mediapp.repo.IConsultaExamenRepo;
import com.bombetalab.mediapp.repo.IConsultaRepo;
import com.bombetalab.mediapp.service.IConsultaService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ConsultaServiceImpl implements IConsultaService {

	@Autowired
	private IConsultaRepo consultaRepo;
	@Autowired
	private IConsultaExamenRepo consultaExamenRepo;

	@Override
	public List<Consulta> listar() {
		return consultaRepo.findAll();
	}

	@Override
	public Consulta listarPorId(Integer id) {
		Optional<Consulta> op = consultaRepo.findById(id);
		return op.isPresent() ? op.get() : new Consulta();
	}

	@Override
	public Consulta registrar(Consulta obj) {
		obj.getDetalleConsulta().forEach(detalle -> detalle.setConsulta(obj)); // Lambda
		return consultaRepo.save(obj);
	}

	@Transactional
	@Override
	public Consulta registrarConsultaExamenes(ConsultaExamenesDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(detalle -> detalle.setConsulta(dto.getConsulta())); // Lambda
		consultaRepo.save(dto.getConsulta());
		dto.getListaExamenes().forEach(examen -> consultaExamenRepo
				.registrarConsultaExamen(dto.getConsulta().getIdConsulta(), examen.getIdExamen()));
		return dto.getConsulta();
	}

	@Override
	public boolean eliminar(Integer id) {
		consultaRepo.deleteById(id);
		return true;

	}

	@Override
	public Consulta modificar(Consulta obj) {
		return consultaRepo.save(obj);
	}

	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return consultaRepo.buscar(filtro.getDni(), filtro.getNombreCompleto());

	}

	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {
		LocalDateTime fechaSgte = filtro.getFechaConsulta().plusDays(1);
		return consultaRepo.buscarFecha(filtro.getFechaConsulta(), fechaSgte);
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		
		consultaRepo.listarResumen().forEach(x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}
	
	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		
		//HashMap<String, String> params = new HashMap<String, String>();
		//params.put("txt_empresa", "MitoCode Network");
		
		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();			
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
		return data;
	}

}
