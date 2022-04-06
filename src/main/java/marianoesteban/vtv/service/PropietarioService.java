package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Propietario;

@Service
public interface PropietarioService {

	List<Propietario> listarPropietarios();
	Propietario agregarPropietario(Propietario propietario);
	Propietario getPropietario(long idPropietario);
	Propietario editarPropietario(long idPropietario, Propietario propietario);
	void eliminarPropietario(long idPropietario);
}
