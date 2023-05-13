package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skys.api.com.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> { }
