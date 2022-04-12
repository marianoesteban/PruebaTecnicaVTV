package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Version;

@Service
public interface VersionService {

	List<Version> listarVersiones();
	List<Version> listarVersionesPorModelo(long idModelo);
	Version agregarVersion(Version version);
	void eliminarVersion(long idVersion);
}
