package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Propietario;

@Repository
public interface PropietarioRepository extends CrudRepository<Propietario, Long> {

	List<Propietario> findAll();
	Propietario findById(long id);
}
