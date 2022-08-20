package com.ticket.spring.Controller;

import com.ticket.spring.Model.Admin;
import com.ticket.spring.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;


    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAdmins(){
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/save")
    public Admin save(@RequestBody Admin admin){
        service.saveAdmin(admin);

        return admin;
    }


    @DeleteMapping("/delete")
    public String deleteAll(){
        service.deleteAdmins();

        return "Admins deleted successfully!";

    }


    @GetMapping("/set/{id}/{roles}")
    public Admin setRoles(@PathVariable int id, @PathVariable String roles, Principal principal){
        Admin admin=service.setRoles(id, roles, principal);

        return admin;
    }
}
