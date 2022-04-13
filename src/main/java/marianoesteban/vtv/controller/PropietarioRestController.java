package marianoesteban.vtv.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import marianoesteban.vtv.exception.DniExistsException;
import marianoesteban.vtv.exception.NotFoundException;
import marianoesteban.vtv.model.Propietario;
import marianoesteban.vtv.service.PropietarioService;

@RestController
public class PropietarioRestController {

	@Autowired
	private PropietarioService propietarioService;

	@GetMapping("/api/propietarios")
	public ResponseEntity<List<Propietario>> listPropietarios() {
		return ResponseEntity.ok(propietarioService.listarPropietarios());
	}

	@GetMapping("/api/propietarios/{idPropietario}")
	public ResponseEntity<Propietario> getPropietario(@PathVariable("idPropietario") long idPropietario) {
		Propietario propietario = propietarioService.getPropietario(idPropietario);
		if (propietario == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el propietario");
		return ResponseEntity.ok(propietario);
	}

	@PostMapping("/api/propietarios")
	public ResponseEntity<?> addPropietario(@Valid @RequestBody Propietario propietario) {
		try {
			propietarioService.agregarPropietario(propietario);
		} catch (DniExistsException dniExistsException) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("No se ha podido agregar: ya existe un propietario con ese DNI");
		}

		return ResponseEntity.ok(propietario);
	}

	@PutMapping("/api/propietarios/{idPropietario}")
	public ResponseEntity<?> editPropietario(@PathVariable("idPropietario") long idPropietario,
			@Valid @RequestBody Propietario propietario) {
		try {
			propietarioService.editarPropietario(idPropietario, propietario);
		} catch (DniExistsException dniExistsException) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("No se ha podido editar: ya existe un propietario con ese DNI");
		} catch (NotFoundException notFoundException) {
			return ResponseEntity.badRequest()
					.body("No se ha podido editar: no hay ningún propietario con el ID especificado");
		}

		return ResponseEntity.ok(propietario);
	}

	@DeleteMapping("/api/propietarios/{idPropietario}")
	public ResponseEntity<?> deletePropietario(@PathVariable("idPropietario") long idPropietario) {
		try {
			propietarioService.eliminarPropietario(idPropietario);
		} catch (NotFoundException notFoundException) {
			return ResponseEntity.badRequest().body("No hay ningún propietario con el ID especificado");
		} catch (Exception exception) {
			return ResponseEntity.badRequest()
					.body("No se ha podido eliminar: el propietario es utilizado en otras entidades");
		}

		return ResponseEntity.noContent().build();
	}
}
