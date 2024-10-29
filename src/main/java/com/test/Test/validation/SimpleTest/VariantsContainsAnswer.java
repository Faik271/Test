package com.test.Test.validation.SimpleTest;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VariantsContainsAnswerValidator.class)

public @interface VariantsContainsAnswer {
    String message() default "The answer must be one of the variants.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
