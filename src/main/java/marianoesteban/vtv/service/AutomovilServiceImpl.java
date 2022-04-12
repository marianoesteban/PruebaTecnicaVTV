package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Automovil;
import marianoesteban.vtv.repository.AutomovilRepository;

@Service
public class AutomovilServiceImpl implements AutomovilService {
	
	@Autowired
	private AutomovilRepository automovilRepository;

	@Override
	public List<Automovil> listarAutomoviles() {
		return automovilRepository.findAll();
	}

	@Override
	public Automovil agregarAutomovil(Automovil automovil) {
		return automovilRepository.save(automovil);
	}

	@Override
	public Automovil getAutomovil(long idAutomovil) {
		return automovilRepository.findById(idAutomovil);
	}

	@Override
	public Automovil editarAutomovil(long idAutomovil, Automovil automovil) {
		automovil.setId(idAutomovil);
		return automovilRepository.save(automovil);
	}

	@Override
	public void eliminarAutomovil(long idAutomovil) {
		automovilRepository.deleteById(idAutomovil);
	}

	@Override
	public List<Automovil> getAutomovilesPorEstadoInspeccion(String estadoInspeccion) {
		return automovilRepository.findByLastEstadoInspeccion(estadoInspeccion);
	}

}
