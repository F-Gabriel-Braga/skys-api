package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.service.AuthenticationService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/signin")
    public Token signin(@RequestBody Credentials credentials) {
        return authenticationService.signin(credentials);
    }

    @PostMapping(value = "/signup")
    public Client signup(@RequestBody Client client) {
        return authenticationService.signup(client);
    }
}
