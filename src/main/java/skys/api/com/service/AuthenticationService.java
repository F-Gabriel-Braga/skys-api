package skys.api.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import skys.api.com.model.Client;
import skys.api.com.repository.ClientRepository;
import skys.api.com.security.UserSecurity;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

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
}
