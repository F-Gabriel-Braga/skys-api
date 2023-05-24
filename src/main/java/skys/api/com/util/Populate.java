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
            new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(150, "Go", "2023-05-23 14:30", "2023-05-23 16:30", 2.0f, "Brasília, DF", 120.0f, "Active", "Salvador, BA", "IDA"),
            new Flight(180, "Avianca", "2023-05-24 09:45", "2023-05-24 11:45", 2.0f, "Fortaleza, CE", 90.0f, "Active", "Recife, PE", "IDA"),
            new Flight(220, "Azul", "2023-05-25 17:15", "2023-05-25 19:15", 2.0f, "Belém, PA", 150.0f, "Active", "Manaus, AM", "IDA"),
            new Flight(190, "Gol", "2023-05-26 08:30", "2023-05-26 10:30", 2.0f, "Porto Alegre, RS", 80.0f, "Active", "Florianópolis, SC", "IDA"),
            new Flight(170, "Latam", "2023-05-27 13:45", "2023-05-27 15:45", 2.0f, "Curitiba, PR", 110.0f, "Active", "São Paulo, SP", "IDA"),
            new Flight(240, "Go", "2023-05-28 11:30", "2023-05-28 13:30", 2.0f, "Rio de Janeiro, RJ", 130.0f, "Active", "Belo Horizonte, MG", "IDA"),
            new Flight(210, "Avianca", "2023-05-29 16:00", "2023-05-29 18:00", 2.0f, "Salvador, BA", 140.0f, "Active", "Recife, PE", "IDA"),
            new Flight(230, "Azul", "2023-05-30 09:15", "2023-05-30 11:15", 2.0f, "Manaus, AM", 160.0f, "Active", "Belém, PA", "IDA"),
            new Flight(200, "Gol", "2023-05-31 12:45", "2023-05-31 14:45", 2.0f, "Florianópolis, SC", 170.0f, "Active", "Porto Alegre, RS", "IDA"),
            new Flight(180, "Latam", "2023-06-01 15:30", "2023-06-01 17:30", 2.0f, "São Paulo, SP", 90.0f, "Active", "Curitiba, PR", "IDA"),
            new Flight(220, "Go", "2023-06-02 10:00", "2023-06-02 12:00", 2.0f, "Belo Horizonte, MG", 120.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(190, "Avianca", "2023-06-03 14:30", "2023-06-03 16:30", 2.0f, "Recife, PE", 100.0f, "Active", "Fortaleza, CE", "IDA"),
            new Flight(170, "Azul", "2023-06-04 09:45", "2023-06-04 11:45", 2.0f, "Manaus, AM", 130.0f, "Active", "Belém, PA", "IDA"),
            new Flight(200, "Gol", "2023-06-05 17:15", "2023-06-05 19:15", 2.0f, "Fortaleza, CE", 140.0f, "Active", "Recife, PE", "IDA"),
            new Flight(180, "Latam", "2023-06-06 08:30", "2023-06-06 10:30", 2.0f, "São Paulo, SP", 110.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(150, "Go", "2023-06-07 13:45", "2023-06-07 15:45", 2.0f, "Brasília, DF", 90.0f, "Active", "Salvador, BA", "IDA"),
            new Flight(200, "Gol", "2023-05-31 12:45", "2023-05-31 14:45", 2.0f, "Florianópolis, SC", 170.0f, "Active", "Porto Alegre, RS", "IDA"),
            new Flight(180, "Latam", "2023-06-01 15:30", "2023-06-01 17:30", 2.0f, "São Paulo, SP", 90.0f, "Active", "Curitiba, PR", "IDA"),
            new Flight(220, "Go", "2023-06-02 10:00", "2023-06-02 12:00", 2.0f, "Belo Horizonte, MG", 120.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(190, "Avianca", "2023-06-03 14:30", "2023-06-03 16:30", 2.0f, "Recife, PE", 100.0f, "Active", "Fortaleza, CE", "IDA"),
            new Flight(170, "Azul", "2023-06-04 09:45", "2023-06-04 11:45", 2.0f, "Manaus, AM", 130.0f, "Active", "Belém, PA", "IDA"),
            new Flight(200, "Gol", "2023-06-05 17:15", "2023-06-05 19:15", 2.0f, "Fortaleza, CE", 140.0f, "Active", "Recife, PE", "IDA"),
            new Flight(180, "Latam", "2023-06-06 08:30", "2023-06-06 10:30", 2.0f, "São Paulo, SP", 110.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(150, "Go", "2023-06-07 13:45", "2023-06-07 15:45", 2.0f, "Brasília, DF", 90.0f, "Active", "Salvador, BA", "IDA"),
            new Flight(210, "Avianca", "2023-06-08 11:30", "2023-06-08 13:30", 2.0f, "Recife, PE", 150.0f, "Active", "Fortaleza, CE", "IDA"),
            new Flight(240, "Azul", "2023-06-09 16:00", "2023-06-09 18:00", 2.0f, "Belém, PA", 120.0f, "Active", "Manaus, AM", "IDA"),
            new Flight(230, "Gol", "2023-06-10 09:15", "2023-06-10 11:15", 2.0f, "Porto Alegre, RS", 100.0f, "Active", "Florianópolis, SC", "IDA"),
            new Flight(200, "Latam", "2023-06-11 12:45", "2023-06-11 14:45", 2.0f, "São Paulo, SP", 130.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(180, "Go", "2023-06-12 15:30", "2023-06-12 17:30", 2.0f, "Belo Horizonte, MG", 110.0f, "Active", "Brasília, DF", "IDA"),
            new Flight(220, "Avianca", "2023-06-13 10:00", "2023-06-13 12:00", 2.0f, "Recife, PE", 120.0f, "Active", "Fortaleza, CE", "IDA"),
            new Flight(190, "Azul", "2023-06-14 14:30", "2023-06-14 16:30", 2.0f, "Salvador, BA", 140.0f, "Active", "Recife, PE", "IDA"),
            new Flight(170, "Gol", "2023-06-15 09:45", "2023-06-15 11:45", 2.0f, "Manaus, AM", 160.0f, "Active", "Belém, PA", "IDA"),
            new Flight(200, "Latam", "2023-06-16 17:15", "2023-06-16 19:15", 2.0f, "Fortaleza, CE", 90.0f, "Active", "Recife, PE", "IDA"),
            new Flight(180, "Go", "2023-06-17 08:30", "2023-06-17 10:30", 2.0f, "São Paulo, SP", 170.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
            new Flight(220, "Latam", "2023-06-18 12:00", "2023-06-18 14:00", 2.0f, "São Paulo, SP", 200.0f, "Active", "Rio de Janeiro, RJ", "IDA"),
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
