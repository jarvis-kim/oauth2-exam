package com.jarvisk.exam.ouath2.oauth2exam;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Data
@Entity
public class AppUser {

    @Id
    private String username;

    private String password;
}
