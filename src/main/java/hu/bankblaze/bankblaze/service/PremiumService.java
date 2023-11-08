package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Premium;
import hu.bankblaze.bankblaze.repo.PremiumRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PremiumService {

    private PremiumRepository premiumRepository;

    public List<Premium> getAllPremium() {
        return premiumRepository.findAll();
    }

}
