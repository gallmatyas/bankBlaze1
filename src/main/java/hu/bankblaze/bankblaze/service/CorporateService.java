package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Corporate;
import hu.bankblaze.bankblaze.repo.CorporateRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CorporateService {
    private CorporateRepository corporateRepository;

    public List<Corporate> getAllCorporates() {
        return corporateRepository.findAll();
    }

    public Corporate getCorporateById(Long id) {
        return corporateRepository.findById(id).orElse(null);
    }
}
