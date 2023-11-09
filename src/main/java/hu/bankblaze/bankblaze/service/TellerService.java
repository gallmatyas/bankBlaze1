package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Teller;
import hu.bankblaze.bankblaze.repo.TellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TellerService {

    private final TellerRepository tellerRepository;

    @Autowired
    public TellerService(TellerRepository tellerRepository) {
        this.tellerRepository = tellerRepository;
    }

    public List<Teller> getAllTellers() {
        return tellerRepository.findAll();
    }

    public Teller getTellerById(Long id) {
        return tellerRepository.findById(id).orElse(null);
    }

}
