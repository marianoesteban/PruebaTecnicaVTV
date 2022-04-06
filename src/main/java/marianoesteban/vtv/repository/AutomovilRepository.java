package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Automovil;

@Repository
public interface AutomovilRepository extends CrudRepository<Automovil, Long> {

	List<Automovil> findAll();
	Automovil findById(long id);
	@Query(value="SELECT a FROM Automovil a INNER JOIN Inspeccion i1 ON a = i1.automovil WHERE i1.fechaInspeccion = (SELECT MAX(i2.fechaInspeccion) FROM Inspeccion i2 WHERE i1.automovil = i2.automovil) AND i1.estadoInspeccion = :estadoInspeccion")
	List<Automovil> findByLastEstadoInspeccion(@Param("estadoInspeccion") String estadoInspeccion);
}
