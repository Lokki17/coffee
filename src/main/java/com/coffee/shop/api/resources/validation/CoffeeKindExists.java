package com.coffee.shop.api.resources.validation;

import com.coffee.shop.service.CoffeeKindService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Objects;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Lokki
 * @since 26.01.2017
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CoffeeKindExists.Validator.class)
public @interface CoffeeKindExists {

    String message() default "{coffee kind doesn't exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<CoffeeKindExists, String> {

        @Autowired
        private CoffeeKindService coffeeKindService;

        @Override
        public void initialize(CoffeeKindExists constraintAnnotation) {

        }

        @Override
        public boolean isValid(String coffeeKind, ConstraintValidatorContext context) {
            if (Objects.isNull(coffeeKind)) {
                return true;
            }

            return coffeeKindService.getKind(coffeeKind).isDefined();
        }
    }
}
