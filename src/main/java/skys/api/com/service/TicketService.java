package skys.api.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Client;
import skys.api.com.model.Flight;
import skys.api.com.model.Ticket;
import skys.api.com.repository.ClientRepository;
import skys.api.com.repository.FlightRepository;
import skys.api.com.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<Ticket> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }

    public Ticket findById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if(ticket.isPresent()) {
            return ticket.get();
        }
        else {
            return null;
        }
    }

    public List<Ticket> findReservesByIdClient(Long idClient) {
        List<Ticket> tickets = ticketRepository.findReservesByIdClient(idClient);
        return tickets;
    }

    public List<Ticket> findTravelsByIdClient(Long idClient) {
        List<Ticket> tickets = ticketRepository.findTravelsByIdClient(idClient);
        return tickets;
    }

    public void finalizeReserveById(Long id) {
        ticketRepository.updateTicketStatusTravel(id);
    }

    public Ticket create(Ticket ticket) {
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

    public void delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
    }
}
