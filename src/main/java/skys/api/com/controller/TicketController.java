package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Ticket>> findAll() {
        List<Ticket> tickets = ticketService.findAll();
        return ResponseEntity.status(200).body(tickets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        return ResponseEntity.status(200).body(ticket);
    }

    @GetMapping(value = "/reserves/{idClient}")
    public ResponseEntity<List<Ticket>> findReservesByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketService.findReservesByIdClient(idClient);
        return ResponseEntity.status(200).body(tickets);
    }

    @GetMapping(value = "/travels/{idClient}")
    public ResponseEntity<List<Ticket>> findTravelsByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketService.findTravelsByIdClient(idClient);
        return ResponseEntity.status(200).body(tickets);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket ticketSaved = ticketService.create(ticket);
        return ResponseEntity.status(201).body(ticketSaved);
    }

    @PostMapping(value = "/reserves/finalize/{id}")
    public ResponseEntity<Long> finalizeReserveById(@PathVariable Long id) {
        ticketService.finalizeReserveById(id);
        return ResponseEntity.status(200).body(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.status(204).body(id);
    }
}
