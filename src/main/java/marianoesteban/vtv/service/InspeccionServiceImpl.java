package marianoesteban.vtv.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Inspeccion;
import marianoesteban.vtv.model.Inspector;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.repository.InspeccionRepository;

@Service
public class InspeccionServiceImpl implements InspeccionService {
	
	@Autowired
	InspeccionRepository inspeccionRepository;

	@Override
	public List<Inspeccion> listarInspecciones() {
		return inspeccionRepository.findAll();
	}

	@Override
	public Inspeccion agregarInspeccion(Inspeccion inspeccion) {
		return inspeccionRepository.save(inspeccion);
	}

	@Override
	public Inspeccion getInspeccion(long idInspeccion) {
		return inspeccionRepository.findById(idInspeccion);
	}

	@Override
	public Inspeccion editarInspeccion(long idInspeccion, Inspeccion inspeccion) {
		inspeccion.setId(idInspeccion);
		return inspeccionRepository.save(inspeccion);
	}

	@Override
	public void eliminarInspeccion(long idInspeccion) {
		inspeccionRepository.deleteById(idInspeccion);
	}

	@Override
	public List<Inspeccion> getInspeccionesRecientes(Date fechaInicio) {
		return inspeccionRepository.findByFechaInspeccionBetweenOrderByFechaInspeccionDesc(
				new java.sql.Date(fechaInicio.getTime()), 
				new java.sql.Date(new Date().getTime())
		);
	}

	@Override
	public List<Inspeccion> getInspeccionesRecientes(Date fechaInicio, long idInspector) {
		Inspector inspector = new Inspector();
		inspector.setId(idInspector);
		return inspeccionRepository.findByInspectorAndFechaInspeccionBetweenOrderByFechaInspeccionDesc(
				inspector,
				new java.sql.Date(fechaInicio.getTime()), 
				new java.sql.Date(new Date().getTime())
		);
	}

	@Override
	public List<Inspeccion> getInspeccionesPorPropietario(long idPropietario) {
		Propietario propietario = new Propietario();
		propietario.setId(idPropietario);
		return inspeccionRepository.findByPropietario(propietario);
	}

}
