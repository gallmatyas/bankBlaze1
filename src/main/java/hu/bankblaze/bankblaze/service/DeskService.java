package hu.bankblaze.bankblaze.service;

import hu.bankblaze.bankblaze.model.Desk;
import hu.bankblaze.bankblaze.repo.DeskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeskService {
    private DeskRepository deskRepository;

    public void saveDeskLayout(Desk desk) {
        deskRepository.save(desk);
    }
}
