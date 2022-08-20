package com.ticket.spring.Repository;

import com.ticket.spring.Model.Code;
import com.ticket.spring.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface MailCodeRepo extends JpaRepository<Code,Long> {

    public boolean existsByTicketAndCustomKey(int ticket,String customKey);

    public List<Code> findByTicket(int ticket);

   /* @Query(value = "delete from public.Code where ticket=:param",nativeQuery = true)
    public void hepsiniSil(@Param("param") int id);*/

    @Transactional
    public void deleteCodesByTicket(int id);
}
