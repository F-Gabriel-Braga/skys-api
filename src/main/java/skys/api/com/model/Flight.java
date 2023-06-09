package skys.api.com.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, name = "valueFrom")
    private String from;
    @Column(nullable = false, length = 100, name = "valueTo")
    private String to;
    @Column(nullable = false, length = 50, name = "type_flight")
    private String type;
    @Column(nullable = false, length = 50, name = "date_hour_flight")
    private String dateHourFlight;
    @Column(nullable = false, length = 50, name = "date_hour_landing")
    private String dateHourLanding;
    @Column(nullable = false)
    private Float duration;
    @Column(nullable = false)
    private Integer capacity;
    @Column(nullable = false)
    private Float price;
    @Column(nullable = false, length = 100)
    private String company;
    @Column(nullable = false, length = 100)
    private String status;

    public Flight() {
    }

    public Flight(int capacity, String company, String dateHourFlight, String dateHourLanding, float duration, String valueFrom, float price, String status, String valueTo, String typeFlight) {
        this.capacity = capacity;
        this.company = company;
        this.dateHourFlight = dateHourFlight;
        this.dateHourLanding = dateHourLanding;
        this.duration = duration;
        this.from = valueFrom;
        this.price = price;
        this.status = status;
        this.to = valueTo;
        this.type = typeFlight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
