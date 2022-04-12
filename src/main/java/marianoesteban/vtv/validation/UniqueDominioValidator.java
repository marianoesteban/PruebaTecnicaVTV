package marianoesteban.vtv.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import marianoesteban.vtv.repository.AutomovilRepository;

@Component
public class UniqueDominioValidator implements ConstraintValidator<UniqueDominio, String> {
	
	@Autowired
	private AutomovilRepository automovilRepository;

	@Override
	public boolean isValid(String dominio, ConstraintValidatorContext context) {
		return !automovilRepository.existsByDominio(dominio);
	}

}
