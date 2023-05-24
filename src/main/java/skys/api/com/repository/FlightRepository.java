package skys.api.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import skys.api.com.model.Flight;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flights WHERE DATEDIFF(date_hour_flight, :dateHourFlight) = 0 AND value_from = :from AND value_to = :to", nativeQuery = true)
    List<Flight> findWithFilter(@Param("dateHourFlight") String dateHourFlight, @Param("from") String from, @Param("to") String to);
}
