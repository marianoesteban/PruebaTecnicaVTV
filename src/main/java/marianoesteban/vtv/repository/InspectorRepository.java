package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Inspector;

@Repository
public interface InspectorRepository extends CrudRepository<Inspector, Long> {

	List<Inspector> findAll();
	Inspector findById(long id);
	@Query("SELECT max(nroLegajo) FROM Inspector")
	String findMaxNroLegajo();
	boolean existsByDni(String dni);
	@Query("SELECT dni FROM Inspector WHERE id = :idInspector")
	String findDniById(@Param("idInspector") long idInspector);
}
