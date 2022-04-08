package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Marca;

@Service
public interface MarcaService {

	List<Marca> listarMarcas();
	Marca agregarMarca(Marca marca);
	void eliminarMarca(long idMarca);
}
