package marianoesteban.vtv.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import marianoesteban.vtv.repository.MarcaRepository;

@Component
public class UniqueNombreMarcaValidator implements ConstraintValidator<UniqueNombreMarca, String> {

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public boolean isValid(String nombreMarca, ConstraintValidatorContext context) {
		return !marcaRepository.existsByNombre(nombreMarca);
	}
}
