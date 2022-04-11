package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Modelo;
import marianoesteban.vtv.model.Version;
import marianoesteban.vtv.repository.VersionRepository;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionRepository versionRepository;

	@Override
	public List<Version> listarVersiones() {
		return versionRepository.findAll();
	}

	@Override
	public List<Version> listarVersionesPorModelo(long idModelo) {
		Modelo modelo = new Modelo();
		modelo.setId(idModelo);
		return versionRepository.findByModelo(modelo);
	}

	@Override
	public Version agregarVersion(Version version) {
		return versionRepository.save(version);
	}

}
