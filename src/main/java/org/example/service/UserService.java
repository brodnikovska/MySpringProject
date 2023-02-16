package org.example.service;

import jakarta.persistence.Column;
import lombok.*;
import org.example.model.User;

@Getter
@Setter
public class UserService implements User {

    @Column(unique=true)
    private long id;
    private String name;
    @Column(unique=true)
    private String email;
}
