package com.ticket.spring.Repository;

import com.ticket.spring.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Optional<Admin> findAdminByName(String name);
}
