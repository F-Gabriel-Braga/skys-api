package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Client;
import skys.api.com.model.Flight;
import skys.api.com.model.Ticket;
import skys.api.com.repository.ClientRepository;
import skys.api.com.repository.FlightRepository;
import skys.api.com.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Ticket> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    @GetMapping(value = "/{id}")
    public Ticket findById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElseThrow(() -> new IllegalStateException("Ticket não encontrado!"));
    }

    @GetMapping(value = "/reserves/{idClient}")
    public List<Ticket> findReservesByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketRepository.findReservesByIdClient(idClient);
        return tickets;
    }

    @GetMapping(value = "/travels/{idClient}")
    public List<Ticket> findTravelsByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketRepository.findTravelsByIdClient(idClient);
        return tickets;
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        Long idFlight = ticket.getIdFlight();
        Long idClient = ticket.getIdClient();
        Optional<Flight> flight = flightRepository.findById(idFlight);
        Optional<Client> client = clientRepository.findById(idClient);
        if(flight.isPresent() && client.isPresent()) {
            ticket = new Ticket(flight.get(), client.get());
            Ticket result = ticketRepository.save(ticket);
            return result;
        }
        return null;
    }
}
