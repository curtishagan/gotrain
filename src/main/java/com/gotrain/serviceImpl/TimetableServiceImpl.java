package com.gotrain.serviceImpl;

import com.gotrain.DAO.TimetableDao;
import com.gotrain.POJO.TimetableEntry;
import com.gotrain.service.TimetableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <h1>TimetableServiceImpl</h1>
 * <p>
 * Implementation of the Timetable Service.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */

@CacheConfig(cacheNames = "timetableEntries")
@Service
public class TimetableServiceImpl implements TimetableService {

    // Timetable DAO for database access.
    @Autowired
    TimetableDao timetableDao;

    /**
     * {@inheritDoc}
     */
    @Cacheable
    @Override
    public List<TimetableEntry> getAllEntries() {
        List<TimetableEntry> entries = new ArrayList<>();
        timetableDao.findAll().forEach(entries::add);
        return entries;
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable
    @Override
    public List<TimetableEntry> getEntriesByLine(String line) {
        // Return found entry
        return timetableDao.findByLine(line);
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable
    @Override
    public List<TimetableEntry> getEntriesByLineAndDepartureTime(String line, String departure) {
        // Convert user input time into military to match our data
        String formattedDeparture = parseDepartureTime(departure);
        // Return found entry
        return timetableDao.findByLineAndDeparture(line, formattedDeparture);
    }

    /**
     * This method takes an unformatted time and coverts it to
     * military time (i.e. 1400). Supported formats include:
     * 1400, 14:00, 2:00pm. Unsupported formats return "INVALID".
     *
     * @param departure Unformatted departure string from API input.
     * @return String Formatted departure time.
     */
    private String parseDepartureTime(String departure) {
        try {
            // Trim any spaces
            String trimmedDeparture = departure.trim();
            // Declare return string
            String militaryTime;

            // Check if already in military time
            if (trimmedDeparture.matches("[0-9]{3,4}")) {
                militaryTime = trimmedDeparture;
            }
            // Check if in any of our other applicable formats
            else if (trimmedDeparture.matches("(1[0-2]|0?[1-9]):?([0-5][0-9])?(([aA]|[pP])[mM])?")) {
                // Get rid of anything that is not a number and convert to an Integer
                Integer timeInt = Integer.parseInt(trimmedDeparture.replaceAll("[^0-9]", ""));
                // Isolate am/pm
                String morningAfternoon = trimmedDeparture.substring(trimmedDeparture.length() - 2)
                        .toLowerCase();
                // If user input is an afternoon time, add twelve hours to get military
                timeInt += morningAfternoon.equals("pm") ? 1200 : 0;

                // Set return string to our modified value
                militaryTime = timeInt.toString();
            }
            // If input is invalid
            else {
                militaryTime = "INVALID";
            }
            return militaryTime;

        } catch (Exception ex) {
            return "INVALID";
        }
    }
}
