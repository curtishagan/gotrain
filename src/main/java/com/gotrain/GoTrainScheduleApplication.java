package com.gotrain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotrain.POJO.TimetableEntry;
import com.gotrain.service.TimetableService;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <h1>Go Train Schedule API</h1>
 * <p>
 * The Go Train Schedule Program API uses Spring Boot to
 * allow for the request of weekday train schedule information.
 * </p>
 * <p>
 * Requests include:
 * - GET /schedule
 * - GET /schedule/{line}?departure={time} (departure parameter is not required)
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@SpringBootApplication
public class GoTrainScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoTrainScheduleApplication.class, args);
	}
}
