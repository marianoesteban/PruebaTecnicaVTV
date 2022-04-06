package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Automovil;

@Service
public interface AutomovilService {

	List<Automovil> listarAutomoviles();
	Automovil agregarAutomovil(Automovil automovil);
	Automovil getAutomovil(long idAutomovil);
	Automovil editarAutomovil(long idAutomovil, Automovil automovil);
	void eliminarAutomovil(long idAutomovil);
	List<Automovil> getAutomovilesPorEstadoInspeccion(String estadoInspeccion);
}
