package com.gotrain.serviceImpl;

import com.gotrain.DAO.TimetableDao;
import com.gotrain.POJO.TimetableEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TimetableServiceTest {

    @Mock
    private TimetableDao timetableRepository;

    @InjectMocks
    private TimetableServiceImpl timetableService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntriesByLineWithCaching() {
        List<TimetableEntry> entries = Arrays.asList(
                new TimetableEntry(1L, "Lakeshore", "800", "900"),
                new TimetableEntry(2L, "Lakeshore", "1000", "1100")
        );

        when(timetableRepository.findByLine("Lakeshore")).thenReturn(entries);

        List<TimetableEntry> result1 = timetableService.getEntriesByLine("Lakeshore");
        List<TimetableEntry> result2 = timetableService.getEntriesByLine("Lakeshore");

        verify(timetableRepository, times(2)).findByLine("Lakeshore");

        assertEquals(entries, result1);
        assertEquals(entries, result2);
    }

    @Test
    public void testGetEntriesByLineWithoutCaching() {
        List<TimetableEntry> entries1 = Arrays.asList(
                new TimetableEntry(1L, "Lakeshore", "800", "900")
        );
        List<TimetableEntry> entries2 = Arrays.asList(
                new TimetableEntry(2L, "Barrie", "730", "930")
        );

        when(timetableRepository.findByLine("Lakeshore")).thenReturn(entries1);
        when(timetableRepository.findByLine("Barrie")).thenReturn(entries2);

        List<TimetableEntry> result1 = timetableService.getEntriesByLine("Lakeshore");
        List<TimetableEntry> result2 = timetableService.getEntriesByLine("Barrie");
        List<TimetableEntry> result3 = timetableService.getEntriesByLine("Lakeshore");

        verify(timetableRepository, times(2)).findByLine("Lakeshore");
        verify(timetableRepository, times(1)).findByLine("Barrie");

        assertEquals(entries1, result1);
        assertEquals(entries2, result2);
        assertEquals(entries1, result3);
    }

}