package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return inspectorRepository.save(inspector);
	}

	@Override
	public Inspector getInspector(long idInspector) {
		return inspectorRepository.findById(idInspector);
	}

	@Override
	public Inspector editarInspector(long idInspector, Inspector inspector) {
		inspector.setId(idInspector);
		return inspectorRepository.save(inspector);
	}

	@Override
	public void eliminarInspector(long idInspector) {
		inspectorRepository.deleteById(idInspector);
	}

}
