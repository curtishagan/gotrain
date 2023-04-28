package com.gotrain.service;

import com.gotrain.POJO.TimetableEntry;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

/**
 * <h1>TimetableService</h1>
 * <p>
 * Timetable Service with logic to retrieve data from the
 * H2 database when given certain parameters.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@Service
public interface TimetableService {

    /**
     * Returns a list of all entries in timetable database.
     *
     * @return List<TimetableEntry>
     */
    public List<TimetableEntry> getAllEntries();

    /**
     * Returns a list of all entries with a given line in timetable database.
     *
     * @return List<TimetableEntry>
     */
    public List<TimetableEntry> getEntriesByLine(String line);

    /**
     * Returns a list of all entries with a given line and departure time in timetable database.
     *
     * @return List<TimetableEntry>
     */
    public List<TimetableEntry> getEntriesByLineAndDepartureTime(String line, String departure);
}
