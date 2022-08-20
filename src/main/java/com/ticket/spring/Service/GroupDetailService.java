package com.ticket.spring.Service;

import com.ticket.spring.Model.Admin;
import com.ticket.spring.Repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class GroupDetailService implements UserDetailsService {

    private static Logger logger=Logger.getLogger(GroupDetailService.class.getName());

    private final AdminRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin=repo.findAdminByName(username);
        logger.info(admin.get()+"");
        return admin.map(GroupDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
