package org.example.service;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Event;

import java.util.Date;

@Getter
@Setter
public class EventService implements Event {

    @Column(unique=true)
    private long id;
    private String title;
    private Date date;
}
