package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Retail;
import hu.bankblaze.bankblaze.repo.RetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RetailService {

    @Autowired
    private RetailRepository retailRepository;

    public List<Retail> getAllRetail () {
        return retailRepository.findAll();
    }

    public Retail getRetailById(Long id) {
        return retailRepository.findById(id).orElse(null);
    }


    /*Irni metódust a sorszám generálásra, aminek bemenetként kérünk egy id-t,
     switch-ben elbírálok és kiadom a sorszámot!*/
    public int generateQueueNumber(int number) {
        int queueNumber = 0;
        switch (number) {
            case 1 -> queueNumber = 11;
            case 2 -> queueNumber = 22;
            case 3 -> queueNumber = 33;
            case 4 -> queueNumber = 44;
        }
        return queueNumber;
    }
}
