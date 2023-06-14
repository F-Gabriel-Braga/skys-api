package skys.api.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skys.api.com.model.Flight;
import skys.api.com.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findAll() {
        List<Flight> flights = flightRepository.findAll();
        return flights;
    }

    public Flight findById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if(flight.isPresent()) {
            return flight.get();
        }
        else {
            return null;
        }
    }

    public List<Flight> findWithFilter(Flight flight) {
        List<Flight> flights = flightRepository.findWithFilter(flight.getDateHourFlight(), flight.getFrom(), flight.getTo());
        return flights;
    }

    public Flight create(Flight flight) {
        Flight result = flightRepository.save(flight);
        return result;
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
