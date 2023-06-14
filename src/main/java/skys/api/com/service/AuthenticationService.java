package skys.api.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import skys.api.com.dto.Credentials;
import skys.api.com.dto.Token;
import skys.api.com.model.Client;
import skys.api.com.repository.ClientRepository;
import skys.api.com.security.UserSecurity;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = this.clientRepository.findByEmail(username);

        if(client.isPresent()) {
            UserSecurity userSecurity = new UserSecurity();
            userSecurity.setId(client.get().getId());
            userSecurity.setEmail(client.get().getEmail());
            userSecurity.setPassword(client.get().getPassword());
            return userSecurity;
        }
        else {
            throw new RuntimeException();
        }
    }

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
