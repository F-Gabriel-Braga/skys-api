package skys.api.com.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50, name = "type_flight")
    private String type;
    @Column(nullable = false, length = 50, name = "date_hour_flight")
    private String dateHourFlight;
    @Column(nullable = false, length = 50, name = "date_hour_landing")
    private String dateHourLanding;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Client client;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Flight flight;
    @Column(nullable = false)
    private Float price;
    @Column(nullable = false, length = 50)
    private String status;

    @Transient
    private Long idClient;
    @Transient
    private Long idFlight;

    public Ticket() {
    }

    public Ticket(Flight flight, Client client) {
        setFlight(flight);
        setClient(client);
        setType(flight.getType());
        setPrice(flight.getPrice());
        setDateHourFlight(flight.getDateHourFlight());
        setDateHourLanding(flight.getDateHourLanding());
        setStatus("reserve");
    }

    public Ticket(Long idClient, Long idFlight) {
        this.idClient = idClient;
        this.idFlight = idFlight;
    }

    public Ticket(String type, String dateHourFlight, String dateHourLanding, Client client, Flight flight, Float price, String status) {
        this.type = type;
        this.dateHourFlight = dateHourFlight;
        this.dateHourLanding = dateHourLanding;
        this.client = client;
        this.flight = flight;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateHourFlight() {
        return dateHourFlight;
    }

    public void setDateHourFlight(String dateHourFlight) {
        this.dateHourFlight = dateHourFlight;
    }

    public String getDateHourLanding() {
        return dateHourLanding;
    }

    public void setDateHourLanding(String dateHourLanding) {
        this.dateHourLanding = dateHourLanding;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Long idFlight) {
        this.idFlight = idFlight;
    }
}
