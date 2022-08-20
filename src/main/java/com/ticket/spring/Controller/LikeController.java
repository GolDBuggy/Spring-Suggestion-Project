package com.ticket.spring.Controller;

import com.ticket.spring.Model.Code;
import com.ticket.spring.Model.Ticket;
import com.ticket.spring.Service.CodeService;
import com.ticket.spring.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final CodeService service;

    private final TicketService ticketService;

    private Logger logger = Logger.getLogger(LikeController.class.getName());

    @GetMapping("/all")
    public ResponseEntity<List<Code>> getCodes() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public List<Code> getBYId(@PathVariable int id){
        return service.getCodeBYID(id);
    }


    @GetMapping("/ticket/{id}")
    public Ticket getId(@PathVariable long id){
        return ticketService.ticketGet(id);
    }

    @PostMapping("/saveId")
    public String saveId(@RequestBody String object)  {

        service.saveLike(object);

        return "redirect:/api/ticket";
    }

    @PostMapping("/deleteId")
    public String deleteId(@RequestBody String object){
        ticketService.deleteTicket(object);
        logger.info(object+"");
        return "";
    }




}
