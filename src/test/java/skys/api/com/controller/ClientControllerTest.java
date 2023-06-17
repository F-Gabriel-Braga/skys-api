package skys.api.com.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
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
import skys.api.com.service.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnSuccess_FindAll() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/clients")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_FindById() {
        String tokenValid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnYWJyaWVsQGV4YW1wbGUuY29tIiwiaWQiOjEsImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client.setId(1L);
        Mockito.when(this.clientService.findById(1L)).thenReturn(client);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenValid)
                .when().get("/clients/{id}", 1L)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnError_FindById_TokenInvalid() {
        String tokenInvalid = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjkhg5jkh4jk5hjk4ImV4cCI6MTY4OTMzMzkzN30.FrAE1ASGbFcuoL2WaIaZ0njAcpF4mEY3z0405WTUmZzkHpepA4OMTnKMqhqJM9I7bp1HfP-d9IhceRwI4KbuVQ";
        Client client1 = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        client1.setId(1L);
        Mockito.when(this.clientService.findById(1L)).thenReturn(client1);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .header("Authorization", tokenInvalid)
                .when().get("/clients/{id}", 1L)
                .then().statusCode(HttpStatus.FORBIDDEN.value());
    }

}