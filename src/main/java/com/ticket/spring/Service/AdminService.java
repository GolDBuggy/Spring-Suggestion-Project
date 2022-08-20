package com.ticket.spring.Service;

import com.ticket.spring.Model.Admin;
import com.ticket.spring.Repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepo repo;

    private final BCryptPasswordEncoder encoder;

    private static String DEFAULT_ROLE="ROLE_USER";
    private static String[] ADMIN_ROLES={"ROLE_ADMIN","ROLE_MODERATOR"};
    private static String[] MODERATOR_ROLES={"ROLE_MODERATOR"};



    public List<Admin> getAll(){
        return repo.findAll();
    }

    public void saveAdmin(Admin admin){
        admin.setRoles(DEFAULT_ROLE);
        String pass=encoder.encode(admin.getPassword());
        admin.setPassword(pass);
        repo.save(admin);
    }


    public void deleteAdmins(){
        repo.deleteAll();
    }


    public Admin getById(int id){
        return repo.findById(id).get();
    }


    public Admin setRoles(int id, String role, Principal principal){
        Admin Admin=repo.findById(id).get();
        List<String> roles=getAdminRoles(principal);
        String newRole="";
        if(roles.contains(role)){
            newRole=Admin.getRoles()+","+role;
            Admin.setRoles(newRole);
        }
        return Admin;
    }


    public Admin getAdmin(Principal principal){
        return repo.findAdminByName(principal.getName()).get();
    }



    public List<String> getAdminRoles(Principal principal){
        String role=getAdmin(principal).getRoles();
        List<String> roles= Arrays.stream(role.split(",")).collect(Collectors.toList());

        if(roles.contains("ROLE_ADMIN")){
            return Arrays.stream(ADMIN_ROLES).collect(Collectors.toList());
        }
        if(roles.contains("ROLE_MODERATOR")){
            return Arrays.stream(MODERATOR_ROLES).collect(Collectors.toList());
        }
        else
            return Collections.emptyList();
    }



}
