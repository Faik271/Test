package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.SimpleTestVariantDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTestVariantDtoTest {

    @Test
    public void testGetAndSetVariant() {
        SimpleTestVariantDto dto = new SimpleTestVariantDto();
        assertNull(dto.getVariant());

        Character variant = 'B';
        dto.setVariant(variant);
        assertEquals(variant, dto.getVariant());
    }

    @Test
    public void testGetAndSetContent() {
        SimpleTestVariantDto dto = new SimpleTestVariantDto();
        assertNull(dto.getContent());

        String content = "This is variant content.";
        dto.setContent(content);
        assertEquals(content, dto.getContent());
    }
}
