package marianoesteban.vtv.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import marianoesteban.vtv.model.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

	List<Persona> findAll();
}
