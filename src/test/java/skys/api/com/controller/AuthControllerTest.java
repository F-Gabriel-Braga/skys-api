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
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.service.AuthService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @MockBean
    private AuthService authService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnSuccess_Signin() {
        Credentials credentials = new Credentials("gabrielbraga@example.com", "12345678");
        Token token = new Token();
        token.setAccessToken("1234567890");
        Mockito.when(this.authService.signin(Mockito.any(Credentials.class))).thenReturn(token);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(credentials)
                .when().post("/auth/signin")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void returnSuccess_Signup() {
        Client client = new Client("12345678901", "gabriel@example.com", "Gabriel", "Braga", "123456", "1234567890");
        Mockito.when(this.authService.signup(Mockito.any(Client.class))).thenReturn(client);
        given().mockMvc(mockMvc)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(client)
                .when().post("/auth/signup")
                .then().statusCode(HttpStatus.CREATED.value());
    }
}