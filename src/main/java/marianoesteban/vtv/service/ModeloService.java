package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Modelo;

@Service
public interface ModeloService {

	List<Modelo> listarModelos();
	List<Modelo> listarModelosPorMarca(long idMarca);
	Modelo agregarModelo(Modelo modelo);
	void eliminarModelo(long idModelo);
}
