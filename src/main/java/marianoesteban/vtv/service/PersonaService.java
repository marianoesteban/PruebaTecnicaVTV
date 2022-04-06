package marianoesteban.vtv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import marianoesteban.vtv.model.Persona;

@Service
public interface PersonaService {

	List<Persona> listarPersonas();
}
