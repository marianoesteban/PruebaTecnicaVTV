package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Modelo;
import marianoesteban.vtv.repository.ModeloRepository;

@Service
public class ModeloServiceImpl implements ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	public List<Modelo> listarModelos() {
		return modeloRepository.findAll();
	}

	@Override
	public Modelo agregarModelo(Modelo modelo) {
		return modeloRepository.save(modelo);
	}

}
