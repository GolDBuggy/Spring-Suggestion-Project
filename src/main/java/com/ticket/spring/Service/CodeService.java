package com.ticket.spring.Service;

import com.ticket.spring.Model.Code;
import com.ticket.spring.Model.Ticket;
import com.ticket.spring.Repository.MailCodeRepo;
import com.ticket.spring.Repository.TicketRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CodeService {

    private final MailCodeRepo repo;

    private static Logger logger= Logger.getLogger(CodeService.class.getName());


    public List<Code> getCodeBYID(int id){
        return repo.findByTicket(id);
    }

    public List<Code> getAll() {
        return repo.findAll();
    }

    public void saveLike(String object) {
        String customKey = replaceVal(object)[0];
        String ticket_id = replaceVal(object)[1];
        logger.info(ticket_id+"         "+customKey);
        if (checkByTicketId(ticket_id, customKey))
            throw new RuntimeException("Bu mesaja oy kullandınız!");


        repo.save(setCode(ticket_id, customKey));
    }


    public String[] replaceVal(String object) {
        String[] strings = object.split("&");
        strings[0] = strings[0].substring(3);
        strings[1] = strings[1].substring(10);
        logger.info(strings[0]+"         "+strings[1]);

        return strings;
    }


    public Code setCode(String ticket_id, String id) {
        int ticketId = Integer.valueOf(ticket_id);
        Code reCode = new Code();
        reCode.setTicket(ticketId);
        reCode.setCustomKey(id);
        return reCode;
    }


    public boolean checkByTicketId(String ticketId, String customKey) {
        int ticket_id = Integer.valueOf(ticketId);
        return repo.existsByTicketAndCustomKey(ticket_id, customKey);
    }


    public void deletAll(Integer id){
        repo.deleteCodesByTicket(id);
    }

}
