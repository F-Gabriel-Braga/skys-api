package skys.api.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import skys.api.com.model.Ticket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketServiceTest {
    @Autowired
    private TicketService ticketService;

    @Test
    public void returnSuccess_FindAll() {
        List<Ticket> tickets = ticketService.findAll();
        assertNotEquals(tickets, null);
    }

    @Test
    public void returnSuccess_FindById() {
        Ticket flight = ticketService.findById(1L);
        assertNotEquals(flight, null);
    }

    @Test
    public void returnSuccess_FindReserves() {
        List<Ticket> tickets = ticketService.findReservesByIdClient(1L);
        assertNotEquals(tickets, null);
    }

    @Test
    public void returnSuccess_FindTravels() {
        List<Ticket> tickets = ticketService.findTravelsByIdClient(1L);
        assertNotEquals(tickets, null);
    }

    @Test
    public void returnSuccess_Create() {
        Ticket flight = new Ticket(2L, 1L);
        Ticket flightSaved = ticketService.create(flight);
        assertNotEquals(flightSaved, null);
    }

    @Test
    public void returnSuccess_Delete() {
        try {
            ticketService.delete(3L);
            assertEquals(true, true);
        }
        catch (Exception ex) {
            assertEquals(true, false);
        }
    }
}