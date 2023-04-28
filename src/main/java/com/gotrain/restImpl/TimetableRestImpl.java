package com.gotrain.restImpl;

import com.gotrain.POJO.TimetableEntry;
import com.gotrain.rest.TimetableRest;
import com.gotrain.service.TimetableService;
import com.gotrain.util.GoTrainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <h1>TimetableRestImpl</h1>
 * <p>
 * Implementation of the TimetableRest API.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@RestController
public class TimetableRestImpl implements TimetableRest {

    // Timetable Service to retrieve data.
    @Autowired
    TimetableService timetableService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity getAllEntries() {
        try {
            return GoTrainUtil.formatResponse(timetableService.getAllEntries(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return GoTrainUtil.formatResponse("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity getEntriesByLineAndDepartureTime(String line, String departure) {
        try {
            // Get all entries with the given line.
            List<TimetableEntry> entriesByLine = timetableService.getEntriesByLine(line);

            // If line does not exist.
            if (entriesByLine.isEmpty()) {
                // No results found.
                return GoTrainUtil.formatResponse("No results found for given line.", HttpStatus.NOT_FOUND);
            }
            // If line does exist.
            else {
                // If departure was NOT given as a parameter in request.
                if (departure == null) {
                    // Just return all entries for the given line.
                    return GoTrainUtil.formatResponse(entriesByLine, HttpStatus.OK);
                }
                // If departure WAS given as a parameter in request.
                else {
                    // Find entry(ies) (should only be one) for given line and departure time.
                    List<TimetableEntry> entriesByLineAndDepartureTime =
                            timetableService.getEntriesByLineAndDepartureTime(line, departure);

                    // If there are no entries with the given line and departure time.
                    if (entriesByLineAndDepartureTime.isEmpty()) {
                        // No results found.
                        return GoTrainUtil.formatResponse("No results found for given time.",
                                HttpStatus.NOT_FOUND);
                    }
                    // If we did find an entry for the given line and departure time.
                    else {
                        // Return entry(ies).
                        return GoTrainUtil.formatResponse(entriesByLineAndDepartureTime, HttpStatus.OK);
                    }
                }
            }
        } catch (Exception ex) {
            return GoTrainUtil.formatResponse("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
