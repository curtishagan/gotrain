package com.gotrain.util;

import com.gotrain.POJO.TimetableEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <h1>GoTrainUtil</h1>
 * <p>
 * Utility class.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
public class GoTrainUtil {

    // Private Constructor
    private GoTrainUtil() {}

    /**
     * This method takes a message and http status and formats
     * a ResponseEntity object.
     *
     * @param response Message to go with Response Entity.
     * @param httpStatus HttpStatus to go with Response Entity
     * @return ResponseEntity<T> A response entity that can hold an object.
     */
    public static <T> ResponseEntity<T> formatResponse(T response, HttpStatus httpStatus) {
        return new ResponseEntity<T>(response, httpStatus);
    }
}
