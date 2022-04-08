package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.exception.DniExistsException;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.repository.PropietarioRepository;

@Service
public class PropietarioServiceImpl implements PropietarioService {

	@Autowired
	private PropietarioRepository propietarioRepository;

	@Override
	public List<Propietario> listarPropietarios() {
		return propietarioRepository.findAll();
	}

	@Override
	public Propietario agregarPropietario(Propietario propietario) {
		chequearDniUnico(propietario.getDni());
		return propietarioRepository.save(propietario);
	}

	@Override
	public Propietario getPropietario(long idPropietario) {
		return propietarioRepository.findById(idPropietario);
	}

	@Override
	public Propietario editarPropietario(long idPropietario, Propietario propietario) {
		// si el DNI cambió
		if (!propietarioRepository.findDniById(idPropietario).equals(propietario.getDni()))
			chequearDniUnico(propietario.getDni());
		propietario.setId(idPropietario);
		return propietarioRepository.save(propietario);
	}

	@Override
	public void eliminarPropietario(long idPropietario) {
		propietarioRepository.deleteById(idPropietario);
	}

	private void chequearDniUnico(String dni) {
		if (propietarioRepository.existsByDni(dni))
			throw new DniExistsException("Ya existe un propietario con ese DNI");
	}

}
