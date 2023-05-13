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
    @Column(nullable = false, name = "date_hour_flight")
    private Date dateHourFlight;
    @Column(nullable = false, name = "date_hour_landing")
    private Date dateHourLanding;
    @OneToOne
    @JoinColumn(nullable = false)
    private Client client;
    @OneToOne
    @JoinColumn(nullable = false)
    private Flight flight;
    @Column(nullable = false)
    private Float price;
    @Column(nullable = false, length = 100)
    private String status;

    public Ticket() {
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

    public Date getDateHourFlight() {
        return dateHourFlight;
    }

    public void setDateHourFlight(Date dateHourFlight) {
        this.dateHourFlight = dateHourFlight;
    }

    public Date getDateHourLanding() {
        return dateHourLanding;
    }

    public void setDateHourLanding(Date dateHourLanding) {
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
}
