package com.gotrain.config;

import com.gotrain.DAO.TimetableDao;
import com.gotrain.POJO.TimetableEntry;

import jakarta.annotation.PostConstruct;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <h1>TimetableConfig</h1>
 * <p>The Timetable Config class serves the purpose of
 * loading the JSON file data into the H2 database at
 * runtime.</p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@Configuration
public class TimetableConfig {

    // Timetable DAO for data storage
    @Autowired
    private final TimetableDao timetableDao;

    // Constructor
    public TimetableConfig(TimetableDao timetableDao) {
        this.timetableDao = timetableDao;
    }

    /**
     * This method loads timetable.json from resources
     * and saves it into the TimetableDao.
     *
     * @throws IOException
     */
    @PostConstruct
    public void loadTimetableData() throws IOException {
        // Initialize mapper
        ObjectMapper mapper = new ObjectMapper();
        // Establish connection to JSON file
        ClassPathResource data = new ClassPathResource("/timetable.json");
        // Load JSON as string
        String jsonString = IOUtils.toString(data.getInputStream(), StandardCharsets.UTF_8);
        // Use mapper to convert JSON string to List of TimetableEntry objects
        List<TimetableEntry> entries = mapper.readValue(jsonString, new TypeReference<List<TimetableEntry>>() {});
        // Save list to H2 database
        timetableDao.saveAll(entries);
    }
}
