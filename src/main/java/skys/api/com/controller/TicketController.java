package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id) {
        Ticket ticket = ticketService.findById(id);
        if(ticket == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(ticket);
        }
    }

    @GetMapping(value = "/reserves/{idClient}")
    public ResponseEntity<List<Ticket>> findReservesByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketService.findReservesByIdClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @GetMapping(value = "/travels/{idClient}")
    public ResponseEntity<List<Ticket>> findTravelsByIdClient(@PathVariable Long idClient) {
        List<Ticket> tickets = ticketService.findTravelsByIdClient(idClient);
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        Ticket ticketSaved = ticketService.create(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketSaved);
    }

    @PostMapping(value = "/reserves/finalize/{id}")
    public ResponseEntity<Long> finalizeReserveById(@PathVariable Long id) {
        ticketService.finalizeReserveById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }
}
