package hu.bankblaze.bankblaze.controller;
import hu.bankblaze.bankblaze.model.Teller;
import hu.bankblaze.bankblaze.service.QueueNumberService;
import hu.bankblaze.bankblaze.service.TellerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/teller")
public class TellerController {

    private TellerService tellerService;
    private QueueNumberService queueNumberService;

    @GetMapping
    public String getTeller(Model model) {
        model.addAttribute("tellers", tellerService.getAllTellers());
        return "showTeller";
    }

    @PostMapping
    public String getTeller(Model model, @RequestParam("id") int number) {
        model.addAttribute("header", "Pénztár");
        queueNumberService.modifyNumber(tellerService.generateQueueNumber(number));
        queueNumberService.modifyToTeller(true);
        return "queueNumber";
    }
}