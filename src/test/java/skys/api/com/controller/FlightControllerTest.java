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
import skys.api.com.model.Flight;
import skys.api.com.service.FlightService;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@SpringBootTest
@AutoConfigureMockMvc
class FlightControllerTest {
    @MockBean
    private FlightService flightService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnSuccess_FindAll() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/flights")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindById() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Mockito.when(this.flightService.findById(1L)).thenReturn(flight);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/flights/{id}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindWithFilter() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Mockito.when(this.flightService.findWithFilter(Mockito.any(Flight.class))).thenReturn(List.of(flight));
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValid)
                .body(flight)
                .when().post("/flights/filter")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_Create() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Flight flight = new Flight(200, "Latam", "2023-05-22 10:00", "2023-05-22 12:00", 2.0f, "São Paulo, SP", 100.0f, "Active", "Rio de Janeiro, RJ", "IDA");
        flight.setId(1L);
        Mockito.when(this.flightService.create(Mockito.any(Flight.class))).thenReturn(flight);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", tokenValid)
                .body(flight)
                .when().post("/flights")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void returnSuccess_Delete() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().delete("/flights/{id}", 1L)
                .then().statusCode(HttpStatus.NO_CONTENT.value());
    }
}