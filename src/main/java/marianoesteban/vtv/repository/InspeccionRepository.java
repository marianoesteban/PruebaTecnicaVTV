package marianoesteban.vtv.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Inspeccion;
import marianoesteban.vtv.model.Inspector;
import marianoesteban.vtv.model.Propietario;

@Repository
public interface InspeccionRepository extends CrudRepository<Inspeccion, Long> {

	List<Inspeccion> findAll();
	Inspeccion findById(long id);
	List<Inspeccion> findByFechaInspeccionBetweenOrderByFechaInspeccionDesc(Date startDate, Date endDate);
	List<Inspeccion> findByInspectorAndFechaInspeccionBetweenOrderByFechaInspeccionDesc(Inspector inspector, Date startDate, Date endDate);
	@Query(value="FROM Inspeccion WHERE automovil.propietario = :propietario ORDER BY fechaInspeccion DESC")
	List<Inspeccion> findByPropietario(@Param("propietario") Propietario propietario);
}
