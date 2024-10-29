package com.test.Test.validation.SimpleTest;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = VariantValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidVariants {
    String message() default "Invalid variants";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
