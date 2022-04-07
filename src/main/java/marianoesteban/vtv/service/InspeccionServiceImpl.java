package marianoesteban.vtv.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Control;
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
	public Inspeccion agregarInspeccion(Inspeccion inspeccion, List<Control> observaciones, List<Control> mediciones) {
		inspeccion.setEstadoInspeccion(calcularEstado(observaciones, mediciones));
		return agregarInspeccion(inspeccion);
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

	private String calcularEstado(List<Control> observaciones, List<Control> mediciones) {
		// ver si tiene algún "Rechazado"
		for (Control observacion : observaciones) {
			if (observacion.getEstado().equals("Rechazado"))
				return "Rechazado";
		}
		for (Control medicion : mediciones) {
			if (medicion.getEstado().equals("Rechazado"))
				return "Rechazado";
		}

		// ver si tiene algún "Condicional"
		for (Control observacion : observaciones) {
			if (observacion.getEstado().equals("Condicional"))
				return "Condicional";
		}
		for (Control medicion : mediciones) {
			if (medicion.getEstado().equals("Condicional"))
				return "Condicional";
		}

		// si no, son todos "Apto"
		return "Apto";
	}

}
