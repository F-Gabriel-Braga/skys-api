package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skys.api.com.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM tickets WHERE status = 'reserve' AND client_id = :idClient", nativeQuery = true)
    List<Ticket> findReservesByIdClient(Long idClient);
}
