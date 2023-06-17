package skys.api.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    public void returnSuccess_Signin() {
        Credentials credentials = new Credentials("gabriel@example.com", "123456");
        Token token = authService.signin(credentials);
        assertNotEquals(token, null);
    }

    @Test
    public void returnSuccess_Signup() {
        Client client = new Client("12345078901", "gabrielb@example.com", "Gabriel", "Braga", "123456", "1234567890");
        Client clientSaved = authService.signup(client);
        assertNotEquals(clientSaved, null);
    }
}