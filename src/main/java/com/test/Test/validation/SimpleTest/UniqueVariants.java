package com.test.Test.validation.SimpleTest;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UniqueVariantsValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueVariants {
    String message() default "{variants.unique}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
