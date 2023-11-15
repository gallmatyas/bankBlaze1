package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.QueueNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueNumberRepository extends JpaRepository<QueueNumber, Long> {

    QueueNumber findFirstByOrderByIdDesc();
}
