package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.QueueNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueNumberRepository extends JpaRepository<QueueNumber, Long> {

    QueueNumber findFirstByOrderByIdDesc();

    @Query(nativeQuery = true, value = "SELECT MAX(number) FROM queue_number \n" +
            "WHERE LEFT(number, 2) = 11 AND active = true")
    int getLastNumber();
}
