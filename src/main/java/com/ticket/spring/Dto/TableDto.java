package com.ticket.spring.Dto;

import com.ticket.spring.Model.Code;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TableDto {

    private long id;

    private String ticketText;

    private List<Code> likes;
}
