package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Client;
import skys.api.com.model.Flight;
import skys.api.com.model.Ticket;
import skys.api.com.repository.ClientRepository;
import skys.api.com.repository.FlightRepository;
import skys.api.com.repository.TicketRepository;
import skys.api.com.service.TicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Ticket findById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping(value = "/reserves/{idClient}")
    public List<Ticket> findReservesByIdClient(@PathVariable Long idClient) {
        return ticketService.findReservesByIdClient(idClient);
    }

    @GetMapping(value = "/travels/{idClient}")
    public List<Ticket> findTravelsByIdClient(@PathVariable Long idClient) {
        return ticketService.findTravelsByIdClient(idClient);
    }

    @PostMapping(value = "/reserves/finalize/{id}")
    public void finalizeReserveById(@PathVariable Long id) {
        ticketService.finalizeReserveById(id);
    }

    @PostMapping
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketService.create(ticket);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }
}
