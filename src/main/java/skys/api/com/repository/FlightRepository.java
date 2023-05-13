package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skys.api.com.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> { }
