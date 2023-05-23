package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skys.api.com.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM tickets WHERE status = 'reserve' AND client_id = :idClient", nativeQuery = true)
    List<Ticket> findReservesByIdClient(@Param("idClient") Long idClient);

    @Query(value = "SELECT * FROM tickets WHERE status = 'travel' AND client_id = :idClient", nativeQuery = true)
    List<Ticket> findTravelsByIdClient(@Param("idClient") Long idClient);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tickets SET status = 'travel' WHERE id = :id", nativeQuery = true)
    void updateTicketStatusTravel(@Param("id") Long id);
}