package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Propietario;

@Repository
public interface PropietarioRepository extends CrudRepository<Propietario, Long> {

	List<Propietario> findAll();
	Propietario findById(long id);
	boolean existsByDni(String dni);
	@Query("SELECT dni FROM Propietario WHERE id = :idPropietario")
	String findDniById(@Param("idPropietario") long idPropietario);
}
