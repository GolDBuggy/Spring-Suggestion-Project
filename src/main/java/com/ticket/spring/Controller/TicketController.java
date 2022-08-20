package com.ticket.spring.Controller;

import com.ticket.spring.Dto.TableDto;
import com.ticket.spring.Model.Ticket;
import com.ticket.spring.Service.CodeService;
import com.ticket.spring.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;


    private Logger logger = Logger.getLogger(TicketController.class.getName());

    @GetMapping("")
    public String getM(Model model) {
        ModelMapper modelMapper = new ModelMapper();
        model.addAttribute(new Ticket());
        List<Ticket> ticket = service.getAll();
        List<TableDto> tableDtos = ticket.stream().map(user -> modelMapper.map(user, TableDto.class)).collect(Collectors.toList());

        model.addAttribute("tickets", tableDtos);

        return "ticket1";
    }



    @PostMapping("/save")
    public String save(@ModelAttribute("ticket") Ticket ticket) {
        logger.info(ticket + "");
        service.saveTicket(ticket);

        return "redirect:";
    }


}
