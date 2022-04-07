package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;

	@Override
	public List<Marca> listarMarcas() {
		return marcaRepository.findAll();
	}

}