package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Marca;
import marianoesteban.vtv.model.Modelo;

@Repository
public interface ModeloRepository extends CrudRepository<Modelo, Long> {

	List<Modelo> findAll();
	List<Modelo> findByMarca(Marca marca);
	boolean existsByMarcaAndNombre(Marca marca, String nombre);
}
