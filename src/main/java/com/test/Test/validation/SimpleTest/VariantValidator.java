package com.test.Test.validation.SimpleTest;

import com.test.Test.dto.SimpleTest.SimpleTestVariantDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class VariantValidator implements ConstraintValidator<ValidVariants, List<SimpleTestVariantDto>> {

    @Override
    public void initialize(ValidVariants constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<SimpleTestVariantDto> variants, ConstraintValidatorContext context) {
        if (variants == null) {
            return true; // Null check is handled by @NotNull in DTO
        }
        for (SimpleTestVariantDto variant : variants) {
            if (variant.getVariant() == null || variant.getContent() == null) {
                return false;
            }
        }
        return true;
    }
}
