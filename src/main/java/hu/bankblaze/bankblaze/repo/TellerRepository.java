package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.Teller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TellerRepository extends JpaRepository<Teller, Long> {
}
