package hu.bankblaze.bankblaze.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueNumberService {

    @Autowired
    private QueueNumberRepository queueNumberRepository;

    public QueueNumber generateQueueNumber(QueueNumber newQueueNumber) {
        newQueueNumber.setQueueNumber(generateQueueNumberLogic());
        return queueNumberRepository.save(newQueueNumber);
    }

    public void deleteQueueNumberById(Long id) {
        queueNumberRepository.deleteById(id);
    }

    public void deleteAllQueueNumbers() {
        queueNumberRepository.deleteAll();
    }

    public QueueNumber getQueueNumber() {
        return queueNumberRepository.findFirstByOrderByIdAsc();
    }

    public QueueNumber getQueueNumberById(Long id) {
        return queueNumberRepository.findById(id).orElse(null);
    }

    public List<QueueNumber> getAllQueueNumbers() {
        return queueNumberRepository.findAll();
    }

    private int generateQueueNumberLogic() {
        return (int) (Math.random() * 100) + 1;
    }
}





