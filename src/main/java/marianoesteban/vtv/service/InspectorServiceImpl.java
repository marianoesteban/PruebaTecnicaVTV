package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.exception.DniExistsException;
import marianoesteban.vtv.model.Inspector;
import marianoesteban.vtv.repository.InspectorRepository;

@Service
public class InspectorServiceImpl implements InspectorService {

	@Autowired
	InspectorRepository inspectorRepository;

	@Override
	public List<Inspector> listarInspectores() {
		return inspectorRepository.findAll();
	}

	@Override
	public Inspector agregarInspector(Inspector inspector) {
		chequearDniUnico(inspector.getDni());
		inspector.setNroLegajo(proximoNroLegajo());
		return inspectorRepository.save(inspector);
	}

	@Override
	public Inspector getInspector(long idInspector) {
		return inspectorRepository.findById(idInspector);
	}

	@Override
	public Inspector editarInspector(long idInspector, Inspector inspector) {
		// si el DNI cambió, chequear que no esté repetido
		if (!inspectorRepository.findDniById(idInspector).equals(inspector.getDni()))
			chequearDniUnico(inspector.getDni());
		inspector.setId(idInspector);
		return inspectorRepository.save(inspector);
	}

	@Override
	public void eliminarInspector(long idInspector) {
		inspectorRepository.deleteById(idInspector);
	}

	private String proximoNroLegajo() {
		String lastNroLegajo = inspectorRepository.findMaxNroLegajo();
		char proxLetra = lastNroLegajo.charAt(0);
		int proxNumero = Integer.parseInt(lastNroLegajo.substring(1)) + 1;
		if (proxNumero == 1000) {
			proxNumero = 0;
			if (proxLetra == 'Z')
				throw new RuntimeException("No quedan más números de legajo disponibles");
			proxLetra++;
		}
		return String.format("%c%03d", proxLetra, proxNumero);
	}

	private void chequearDniUnico(String dni) {
		if (inspectorRepository.existsByDni(dni))
			throw new DniExistsException("Ya existe un inspector con ese DNI");
	}

}
