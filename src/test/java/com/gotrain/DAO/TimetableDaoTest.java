package com.gotrain.DAO;

import com.gotrain.POJO.TimetableEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TimetableDaoTest {
    @Autowired
    private TimetableDao timetableDao;

    @Test
    public void testFindByLine() {
        List<TimetableEntry> entries = Arrays.asList(
                new TimetableEntry(1L, "Lakeshore", "800", "900"),
                new TimetableEntry(2L, "Barrie", "730", "930")
        );

        timetableDao.saveAll(entries);

        List<TimetableEntry> result = timetableDao.findByLine("Lakeshore");

        assertEquals(1, result.size());
        assertEquals("Lakeshore", result.get(0).getLine());
        assertEquals("800", result.get(0).getDeparture());
        assertEquals("900", result.get(0).getArrival());
    }
}
