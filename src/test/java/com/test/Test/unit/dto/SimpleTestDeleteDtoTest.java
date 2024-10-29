package com.test.Test.unit.dto;

import com.test.Test.dto.SimpleTest.SimpleTestDeleteDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestDeleteDtoTest {

    @Test
    public void testGetAndSetIds() {
        SimpleTestDeleteDto dto = new SimpleTestDeleteDto();
        assertNull(dto.getIds());

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        dto.setIds(ids);
        assertEquals(ids, dto.getIds());
    }
}
