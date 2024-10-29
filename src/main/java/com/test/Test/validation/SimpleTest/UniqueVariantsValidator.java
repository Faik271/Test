package com.test.Test.validation.SimpleTest;

import com.test.Test.controller.SimpleTest.SimpleTestController;
import com.test.Test.dto.SimpleTest.SimpleTestVariantDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueVariantsValidator implements ConstraintValidator<UniqueVariants, List<SimpleTestVariantDto>> {


    private final Logger logInfo = LoggerFactory.getLogger(SimpleTestController.class);

    @Override
    public boolean isValid(List<SimpleTestVariantDto> list, ConstraintValidatorContext constraintValidatorContext) {

        if(list == null){
            return false;
        }
        Set<Character> uniqueVariants = new HashSet<>();
        Set<String> uniqueContents = new HashSet<>();
        for (SimpleTestVariantDto element : list) {
            if (!uniqueVariants.add(element.getVariant()) || !uniqueContents.add(element.getContent())){
                return false;
            }
        }

        return true;
    }
}
