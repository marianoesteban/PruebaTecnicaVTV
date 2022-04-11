package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import marianoesteban.vtv.model.Marca;

public interface MarcaRepository extends CrudRepository<Marca, Long> {

	List<Marca> findAll();
	boolean existsByNombre(String nombre);
}
