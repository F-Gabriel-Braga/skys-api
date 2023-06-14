package skys.api.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.repository.ClientRepository;
import skys.api.com.security.UserSecurity;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Token signin(Credentials credentials) {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(user);
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        return new Token(tokenService.generateToken(userSecurity));
    }

    public Client signup(Client client) {
        client.setPassword(bCryptPasswordEncoder
                .encode(client.getPassword()));
        return clientRepository.save(client);
    }
}
