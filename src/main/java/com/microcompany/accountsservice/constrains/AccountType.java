package com.microcompany.accountsservice.constrains;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AccountType.Validator.class})
public @interface AccountType {

     String message()
            default "El tipo de cuenta no es correcto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<AccountType, String> {
        @Override
        public void initialize(final AccountType name) {
        }

        @Override
        public boolean isValid(final String type, final ConstraintValidatorContext constraintValidatorContext) {
            String cleanType = type.trim();
            return (cleanType.length() >= 3 && cleanType.length() <= 20);
        }
    }
}
