package com.ticket.spring.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "admin_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "admin_password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "admin_name")
    private String name;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "roles")
    private String roles;
}
