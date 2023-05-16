package skys.api.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.repository.ClientRepository;
import skys.api.com.service.TokenService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/signin")
    public Token signin(@RequestBody Credentials credentials) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(user);
        Client client = (Client) authentication.getPrincipal();
        return new Token(tokenService.generateToken(client));
    }

    @PostMapping(value = "/signup")
    public Client signup(@RequestBody Client client) {
        client.setPassword(bCryptPasswordEncoder
                .encode(client.getPassword()));
        return clientRepository.save(client);
    }
}
