package com.ticket.spring.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ticket_like")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;


    @Column(name = "ticket_id")
    private int ticket;

    @Column(name = "user_auth")
    private String customKey;

}
