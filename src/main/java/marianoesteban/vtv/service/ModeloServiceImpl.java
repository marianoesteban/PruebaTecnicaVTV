package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Marca;
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
	public List<Modelo> listarModelosPorMarca(long idMarca) {
		Marca marca = new Marca();
		marca.setId(idMarca);
		return modeloRepository.findByMarca(marca);
	}

	@Override
	public Modelo agregarModelo(Modelo modelo) {
		return modeloRepository.save(modelo);
	}

	@Override
	public void eliminarModelo(long idModelo) {
		modeloRepository.deleteById(idModelo);
	}

}
