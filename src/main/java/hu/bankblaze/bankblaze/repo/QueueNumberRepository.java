package hu.bankblaze.bankblaze.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueNumberRepository extends JpaRepository<QueueNumber, Long> {

}
