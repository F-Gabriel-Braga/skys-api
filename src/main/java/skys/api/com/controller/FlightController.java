package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Flight;
import skys.api.com.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> findAll() {
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }

    @GetMapping(value = "/{id}")
    public Flight findById(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isPresent()) {
            return flight.get();
        }
        else {
            return null;
        }
    }

    @PostMapping
    public Flight create(@RequestBody Flight flight) {
        Flight result = flightRepository.save(flight);
        return result;
    }
}
