package marianoesteban.vtv.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=UniqueNombreMarcaValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueNombreMarca {

	String message() default "Ya existe una marca con ese nombre";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
