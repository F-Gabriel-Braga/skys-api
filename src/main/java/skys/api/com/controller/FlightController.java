package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Flight findById(@PathVariable Long id) {
        return flightService.findById(id);
    }

    @PostMapping(value = "/filter")
    public List<Flight> findWithFilter(@RequestBody Flight flight) {
        return flightService.findWithFilter(flight);
    }

    @PostMapping
    public Flight create(@RequestBody Flight flight) {
        return flightService.create(flight);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        flightService.delete(id);
    }
}
