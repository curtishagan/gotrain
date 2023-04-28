package com.gotrain.DAO;

import com.gotrain.POJO.TimetableEntry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.*;

/**
 * <h1>TimetableDao</h1>
 * <p>
 * The Timetable DAO (Data Access Object) is used with JPA
 * store and access our timetable data.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
public interface TimetableDao extends JpaRepository<TimetableEntry, Long> {
    List<TimetableEntry> findByLine(String line);
    List<TimetableEntry> findByLineAndDeparture(String line, String departure);
}
