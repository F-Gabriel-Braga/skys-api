package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.service.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signin")
    public ResponseEntity<Token> signin(@RequestBody Credentials credentials) {
        Token token = authService.signin(credentials);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Client> signup(@RequestBody Client client) {
        Client clientSaved = authService.signup(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
    }
}
