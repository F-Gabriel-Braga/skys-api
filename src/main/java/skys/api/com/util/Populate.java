package skys.api.com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import skys.api.com.model.Client;
import skys.api.com.model.Flight;
import skys.api.com.model.Ticket;
import skys.api.com.repository.ClientRepository;
import skys.api.com.repository.FlightRepository;
import skys.api.com.repository.TicketRepository;

import java.util.Optional;

@Component
public class Populate implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        populateClients();
        populateFlights();
        populateTickets();
    }

    private Client[] clients = {
            new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890"),
            new Client("23456789012", "cynthia@example.com", "Cynthia", "Pinheiro", "1234567", "2345678901"),
            new Client("34567890123", "estefane@example.com", "Estefane", "Veras", "123456", "3456789012")
    };

    private Flight[] flights = {
            new Flight(200, "Company A", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "City A", 100.0f, "Active", "City B", "IDA"),
            new Flight(150, "Company B", "2023-05-23 14:30", "2023-05-23 16:30", 2.0f, "City C", 120.0f, "Active", "City D", "IDA"),
            new Flight(180, "Company C", "2023-05-24 09:45", "2023-05-24 11:45", 2.0f, "City E", 90.0f, "Active", "City F", "IDA"),
            new Flight(220, "Company D", "2023-05-25 17:15", "2023-05-25 19:15", 2.0f, "City G", 150.0f, "Active", "City H", "IDA"),
            new Flight(190, "Company E", "2023-05-26 08:30", "2023-05-26 10:30", 2.0f, "City I", 80.0f, "Active", "City J", "IDA"),
            new Flight(170, "Company F", "2023-05-27 13:45", "2023-05-27 15:45", 2.0f, "City K", 110.0f, "Active", "City L", "IDA"),
            new Flight(240, "Company G", "2023-05-28 11:30", "2023-05-28 13:30", 2.0f, "City M", 130.0f, "Active", "City N", "IDA"),
            new Flight(210, "Company H", "2023-05-29 16:00", "2023-05-29 18:00", 2.0f, "City O", 140.0f, "Active", "City P", "IDA"),
            new Flight(230, "Company I", "2023-05-30 09:15", "2023-05-30 11:15", 2.0f, "City Q", 160.0f, "Active", "City R", "IDA"),
            new Flight(200, "Company J", "2023-05-31 12:45", "2023-05-31 14:45", 2.0f, "City S", 170.0f, "Active", "City T", "IDA"),
            new Flight(180, "Company K", "2023-06-01 15:30", "2023-06-01 17:30", 2.0f, "City U", 90.0f, "Active", "City V", "IDA"),
            new Flight(220, "Company L", "2023-06-02 10:00", "2023-06-02 12:00", 2.0f, "City W", 120.0f, "Active", "City X", "IDA"),
            new Flight(190, "Company M", "2023-06-03 14:30", "2023-06-03 16:30", 2.0f, "City Y", 100.0f, "Active", "City Z", "IDA"),
            new Flight(170, "Company N", "2023-06-04 09:45", "2023-06-04 11:45", 2.0f, "City A1", 130.0f, "Active", "City B1", "IDA"),
            new Flight(200, "Company O", "2023-06-05 17:15", "2023-06-05 19:15", 2.0f, "City C1", 140.0f, "Active", "City D1", "IDA"),
            new Flight(180, "Company P", "2023-06-06 08:30", "2023-06-06 10:30", 2.0f, "City E1", 110.0f, "Active", "City F1", "IDA"),
            new Flight(150, "Company Q", "2023-06-07 13:45", "2023-06-07 15:45", 2.0f, "City G1", 90.0f, "Active", "City H1", "IDA"),
            new Flight(210, "Company R", "2023-06-08 11:30", "2023-06-08 13:30", 2.0f, "City I1", 150.0f, "Active", "City J1", "IDA"),
            new Flight(240, "Company S", "2023-06-09 16:00", "2023-06-09 18:00", 2.0f, "City K1", 120.0f, "Active", "City L1", "IDA"),
            new Flight(230, "Company T", "2023-06-10 09:15", "2023-06-10 11:15", 2.0f, "City M1", 100.0f, "Active", "City N1", "IDA"),
            new Flight(200, "Company U", "2023-06-11 12:45", "2023-06-11 14:45", 2.0f, "City O1", 130.0f, "Active", "City P1", "IDA"),
            new Flight(180, "Company V", "2023-06-12 15:30", "2023-06-12 17:30", 2.0f, "City Q1", 110.0f, "Active", "City R1", "IDA"),
            new Flight(150, "Company W", "2023-06-13 10:00", "2023-06-13 12:00", 2.0f, "City S1", 90.0f, "Active", "City T1", "IDA"),
            new Flight(190, "Company X", "2023-06-14 14:30", "2023-06-14 16:30", 2.0f, "City U1", 120.0f, "Active", "City V1", "IDA"),
            new Flight(220, "Company Y", "2023-06-15 09:45", "2023-06-15 11:45", 2.0f, "City W1", 150.0f, "Active", "City X1", "IDA"),
            new Flight(170, "Company Z", "2023-06-16 17:15", "2023-06-16 19:15", 2.0f, "City Y1", 110.0f, "Active", "City Z1", "IDA"),
            new Flight(200, "Company A1", "2023-06-17 08:30", "2023-06-17 10:30", 2.0f, "City A2", 100.0f, "Active", "City B2", "IDA"),
            new Flight(210, "Company B1", "2023-06-18 13:45", "2023-06-18 15:45", 2.0f, "City C2", 130.0f, "Active", "City D2", "IDA"),
            new Flight(180, "Company C1", "2023-06-19 11:30", "2023-06-19 13:30", 2.0f, "City E2", 120.0f, "Active", "City F2", "IDA"),
            new Flight(230, "Company D1", "2023-06-20 16:00", "2023-06-20 18:00", 2.0f, "City G2", 140.0f, "Active", "City H2", "IDA")
    };

    private Ticket[] tickets = {
        new Ticket(1L, 1L),
        new Ticket(1L, 2L),
        new Ticket(1L, 3L),
        new Ticket(1L, 4L),
        new Ticket(1L, 5L),
        new Ticket(1L, 6L),
        new Ticket(1L, 7L),
        new Ticket(1L, 8L),
        new Ticket(1L, 9L),
        new Ticket(1L, 10L),
        new Ticket(2L, 1L),
        new Ticket(2L, 2L),
        new Ticket(2L, 3L),
        new Ticket(2L, 4L),
        new Ticket(2L, 5L),
        new Ticket(2L, 6L),
        new Ticket(2L, 7L),
        new Ticket(2L, 8L),
        new Ticket(2L, 9L),
        new Ticket(2L, 10L),
        new Ticket(3L, 1L),
        new Ticket(3L, 2L),
        new Ticket(3L, 3L),
        new Ticket(3L, 4L),
        new Ticket(3L, 5L),
        new Ticket(3L, 6L),
        new Ticket(3L, 7L),
        new Ticket(3L, 8L),
        new Ticket(3L, 9L),
        new Ticket(3L, 10L),
        new Ticket(1L, 11L),
        new Ticket(2L, 12L),
        new Ticket(3L, 13L),
        new Ticket(1L, 14L),
        new Ticket(2L, 15L),
        new Ticket(3L, 16L),
        new Ticket(1L, 17L),
        new Ticket(2L, 18L),
        new Ticket(3L, 19L),
        new Ticket(1L, 20L)
    };

    private void populateClients() {
        for (Client client : clients) {
            try {
                client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
                clientRepository.save(client);
            }
            catch (Exception err) {
//                err.printStackTrace();
            }
        }
    }

    private void populateFlights() {
        for (Flight flight : flights) {
            try {
                flightRepository.save(flight);
            }
            catch (Exception err) {
//                err.printStackTrace();
            }
        }
    }

    private void populateTickets() {
        for (Ticket ticket : tickets) {
            try {
                Long idFlight = ticket.getIdFlight();
                Long idClient = ticket.getIdClient();
                Optional<Flight> flight = flightRepository.findById(idFlight);
                Optional<Client> client = clientRepository.findById(idClient);
                if(flight.isPresent() && client.isPresent()) {
                    ticket = new Ticket(flight.get(), client.get());
                    ticketRepository.save(ticket);
                }
            }
            catch (Exception err) {
//                err.printStackTrace();
            }
        }

    }
}
