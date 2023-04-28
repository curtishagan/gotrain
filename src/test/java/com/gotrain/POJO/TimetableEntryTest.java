package com.gotrain.POJO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimetableEntryTest {

    @Test
    public void testGettersAndSetters() {
        TimetableEntry entry = new TimetableEntry(1L, "Lakeshore", "900", "1000");

        assertEquals(1L, entry.getId().longValue());
        assertEquals("Lakeshore", entry.getLine());
        assertEquals("900", entry.getDeparture());
        assertEquals("1000", entry.getArrival());
    }
}
