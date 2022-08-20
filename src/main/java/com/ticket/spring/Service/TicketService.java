package com.ticket.spring.Service;

import com.ticket.spring.Model.Code;
import com.ticket.spring.Model.Ticket;
import com.ticket.spring.Repository.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepo repo;

    private final CodeService codeService;

    private static Logger logger = Logger.getLogger(TicketService.class.getName());


    public void saveTicket(Ticket ticket) {
        if (ticket.getTicketText().isEmpty())
            throw new RuntimeException("Textarea is empty");
        repo.save(ticket);
    }

    public List<Ticket> getAll() {
        List<Ticket> page = repo.findAll();
        Collections.sort(page,(o1,o2) -> Integer.compare(o2.getLikes().size(),o1.getLikes().size()));
        return page;
    }

    public Ticket ticketGet(long id) {
        return repo.findTicketById(id);
    }


    public void deleteTicket(String ticketId){
        ticketId=ticketId.substring(10);
        logger.info(ticketId+"");
        codeService.deletAll(Integer.valueOf(ticketId));
        logger.info(repo.findTicketById(Long.valueOf(ticketId))+" ASDASDAS");
        repo.deleteById(Long.valueOf(ticketId));
    }

}
