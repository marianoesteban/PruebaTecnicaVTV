package marianoesteban.vtv.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=UniqueDominioValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueDominio {

	String message() default "Ya existe un automóvil con ese dominio";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
