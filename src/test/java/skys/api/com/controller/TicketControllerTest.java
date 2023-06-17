package skys.api.com.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import skys.api.com.model.Client;
import skys.api.com.model.Flight;
import skys.api.com.model.Ticket;
import skys.api.com.service.TicketService;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
@AutoConfigureMockMvc
class TicketControllerTest {

    @MockBean
    private TicketService ticketService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnSuccess_FindAll() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/tickets")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindById() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client.setId(1L);
        Ticket ticket = new Ticket("IDA", "2023-05-22 10:00", "2023-05-22 12:00", client, flight, 1200.0F, "PENDENTE");
        ticket.setId(1L);
        Mockito.when(this.ticketService.findById(1L)).thenReturn(ticket);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/tickets/{id}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindReservesByIdClient() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client.setId(1L);
        Ticket ticket = new Ticket("IDA", "2023-05-22 10:00", "2023-05-22 12:00", client, flight, 1200.0F, "PENDENTE");
        ticket.setId(1L);
        Mockito.when(this.ticketService.findReservesByIdClient(1L)).thenReturn(List.of(ticket));
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .body(flight)
                .when().get("/tickets/reserves/{idClient}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindTravelsByIdClient() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client.setId(1L);
        Ticket ticket = new Ticket("IDA", "2023-05-22 10:00", "2023-05-22 12:00", client, flight, 1200.0F, "PENDENTE");
        ticket.setId(1L);
        Mockito.when(this.ticketService.findTravelsByIdClient(1L)).thenReturn(List.of(ticket));
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .body(flight)
                .when().get("/tickets/travels/{idClient}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_Create() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client.setId(1L);
        Ticket ticket = new Ticket("IDA", "2023-05-22 10:00", "2023-05-22 12:00", client, flight, 1200.0F, "PENDENTE");
        ticket.setId(1L);
        Mockito.when(this.ticketService.create(Mockito.any(Ticket.class))).thenReturn(ticket);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValid)
                .body(ticket)
                .when().post("/tickets")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void returnSuccess_Delete() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().delete("/tickets/{id}", 1L)
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void returnSuccess_Finalize() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().post("/tickets/reserves/finalize/{id}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }
}