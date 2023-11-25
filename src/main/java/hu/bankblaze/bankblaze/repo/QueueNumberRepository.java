package hu.bankblaze.bankblaze.repo;

import hu.bankblaze.bankblaze.model.QueueNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueNumberRepository extends JpaRepository<QueueNumber, Long> {

    QueueNumber findFirstByOrderByIdDesc();

    @Query(nativeQuery = true, value = "SELECT IFNULL ( (SELECT MAX(number) FROM queue_number \n" +
            "WHERE LEFT(number, 2) = :firstDigits AND active = true), 1)")
    Integer getLastNumber(@Param("firstDigits") int numbers);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM queue_number")
    Integer countQueueNumberRows();

    int countByNumberBetween(int i, int i1);

    int countByActiveIsTrueAndToRetailIsTrue();
    int countByActiveIsTrueAndToCorporateIsTrue();
    int countByActiveIsTrueAndToTellerIsTrue();
    int countByActiveIsTrueAndToPremiumIsTrue();
}
