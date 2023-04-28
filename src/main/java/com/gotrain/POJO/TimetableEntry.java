package com.gotrain.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * <h1>TimetableEntry</h1>
 * <p>
 * Our Timetable Entry class to hold data for our
 * timetable.
 * </p>
 *
 * @author  Curtis Hagan
 * @version 1.0
 * @since   04/28/2023
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
public class TimetableEntry implements Serializable {

    public TimetableEntry() {}

    public TimetableEntry(Long id, String line, String departure, String arrival) {
        this.id = id;
        this.line = line;
        this.departure = departure;
        this.arrival = arrival;
    }

    @Id
    private Long id;

    @Column(nullable = false)
    private String line;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String arrival;
}
