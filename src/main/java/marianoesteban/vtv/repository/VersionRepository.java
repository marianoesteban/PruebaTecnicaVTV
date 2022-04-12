package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Modelo;
import marianoesteban.vtv.model.Version;

@Repository
public interface VersionRepository extends CrudRepository<Version, Long> {

	List<Version> findAll();
	List<Version> findByModelo(Modelo modelo);
	boolean existsByModeloAndNombre(Modelo modelo, String nombre);
}
