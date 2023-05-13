package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skys.api.com.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
