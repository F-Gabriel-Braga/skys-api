package skys.api.com.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skys.api.com.model.Client;
import skys.api.com.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    @GetMapping(value = "/{id}")
    public Client findById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            return client.get();
        }
        else {
            return null;
        }
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
