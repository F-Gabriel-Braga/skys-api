package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Flight;
import skys.api.com.repository.FlightRepository;
import skys.api.com.service.FlightService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> findAll() {
        List<Flight> flights = flightService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> findById(@PathVariable Long id) {
        Flight flight = flightService.findById(id);
        if(flight == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(flight);
        }
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<List<Flight>> findWithFilter(@RequestBody Flight flight) {
        List<Flight> flights = flightService.findWithFilter(flight);
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }

    @PostMapping
    public ResponseEntity<Flight> create(@RequestBody Flight flight) {
        Flight flightSaved = flightService.create(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(flightSaved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        flightService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
    }
}
