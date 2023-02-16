package org.example.service;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Ticket;

@Getter
@Setter
public class TicketService implements Ticket {

    @Column(unique=true)
    private long id;
    private long eventId;
    private long userId;
    private Category category;
    private int place;
}
