package com.ticket.spring.Repository;

import com.ticket.spring.Model.Ticket;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket,Long> {

    @Query("select id from Ticket")
    List<Integer> idGetir();

    Ticket findTicketById(long id);

   /* @Query("select Ticket.id,Ticket.ticketText,Ticket.likes from Ticket order by likes")
    List<Ticket> siraliGetir();
    */


    @Query(value = "select p from Ticket p join p.likes ad group by p", countQuery = "select count(p) from Ticket p")
    Page<Ticket> findAllWithAddressCount(Pageable pageable);

}
