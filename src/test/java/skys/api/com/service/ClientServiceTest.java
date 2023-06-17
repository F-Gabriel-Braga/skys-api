package skys.api.com.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import skys.api.com.model.Client;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void returnSuccess_FindAll() {
        List<Client> clients = clientService.findAll();
        assertNotEquals(clients, null);
    }

    @Test
    public void returnSuccess_FindById() {
        Client client = clientService.findById(1L);
        assertNotEquals(client, null);
    }
}