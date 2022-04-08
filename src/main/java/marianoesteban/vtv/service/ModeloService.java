package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Modelo;

@Service
public interface ModeloService {

	List<Modelo> listarModelos();
	Modelo agregarModelo(Modelo modelo);
}
