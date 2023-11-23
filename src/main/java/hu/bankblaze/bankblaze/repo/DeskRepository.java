package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {
}
