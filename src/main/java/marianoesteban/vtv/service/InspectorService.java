package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Inspector;

@Service
public interface InspectorService {

	List<Inspector> listarInspectores();
	Inspector agregarInspector(Inspector inspector);
	Inspector getInspector(long idInspector);
	Inspector editarInspector(long idInspector, Inspector inspector);
	void eliminarInspector(long idInspector);
}
