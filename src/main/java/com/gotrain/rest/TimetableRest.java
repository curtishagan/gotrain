package com.gotrain.rest;

import com.gotrain.POJO.TimetableEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1>TimetableRest</h1>
 * <p>
 * Our REST API with our requests:
 * - GET /schedule
 * - GET /schedule/{line}?departure={time} (departure parameter is not required)
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@RestController
public interface TimetableRest {

    /**
     * Returns a Response Entity with all Timetable Entries in our
     * H2 database.
     *
     * @return ResponseEntity<List<TimetableEntry>>
     */
    @GetMapping(value = "/schedule")
    public ResponseEntity getAllEntries();

    /**
     * Returns entry(ies) for a given line and optional departure time.
     *
     * @param line Train line name.
     * @param departure Departure time.
     * @return ResponseEntity<List<TimetableEntry>>
     */
    @GetMapping(value = "/schedule/{line}")
    public ResponseEntity getEntriesByLineAndDepartureTime(@PathVariable String line,
                                                                                 @RequestParam(required = false)
                                                                                 String departure);
}
