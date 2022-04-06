package marianoesteban.vtv.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Inspeccion;

@Service
public interface InspeccionService {

	List<Inspeccion> listarInspecciones();
	Inspeccion agregarInspeccion(Inspeccion inspeccion);
	Inspeccion getInspeccion(long idInspeccion);
	Inspeccion editarInspeccion(long idInspeccion, Inspeccion inspeccion);
	void eliminarInspeccion(long idInspeccion);
	List<Inspeccion> getInspeccionesRecientes(Date fechaInicio);
	List<Inspeccion> getInspeccionesRecientes(Date fechaInicio, long idInspector);
	List<Inspeccion> getInspeccionesPorPropietario(long idPropietario);
}
