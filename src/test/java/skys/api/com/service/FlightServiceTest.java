package skys.api.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import skys.api.com.model.Flight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class FlightServiceTest {
    @Autowired
    private FlightService flightService;

    @Test
    public void returnSuccess_FindAll() {
        List<Flight> flights = flightService.findAll();
        assertNotEquals(flights, null);
    }

    @Test
    public void returnSuccess_FindById() {
        Flight flight = flightService.findById(1L);
        assertNotEquals(flight, null);
    }

    @Test
    public void returnSuccess_FindWithFilter() {
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        List<Flight> flights = flightService.findWithFilter(flight);
        assertNotEquals(flights, null);
    }

    @Test
    public void returnSuccess_Create() {
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        Flight flightSaved = flightService.create(flight);
        assertNotEquals(flightSaved, null);
    }

    @Test
    public void returnSuccess_Delete() {
        try {
            flightService.delete(21L);
            assertEquals(true, true);
        }
        catch (Exception ex) {
            assertEquals(true, false);
        }
    }
}