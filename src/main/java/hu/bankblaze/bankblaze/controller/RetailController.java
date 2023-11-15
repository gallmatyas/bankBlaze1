package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.QueueNumber;
import hu.bankblaze.bankblaze.model.Retail;
import hu.bankblaze.bankblaze.service.QueueNumberService;
import hu.bankblaze.bankblaze.service.RetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/retail")
public class RetailController {

    private QueueNumberService queueNumberService;
    private RetailService retailService;

    @GetMapping()
    public String getAllRetail(Model model){
        model.addAttribute("retails",retailService.getAllRetail());
        model.addAttribute("newQueueNumber", new QueueNumber());
        return "showRetail";
    }

    @PostMapping
    public String getAllRetail (@ModelAttribute("newQueueNumber") QueueNumber queueNumber,
                                 @RequestParam("id") int number,
                                Model model){
        model.addAttribute("header", "Lakossági");
        queueNumberService.addQueueNumber(queueNumber);
        queueNumberService.modifyNumber(retailService.generateQueueNumber(number));
        return "queueNumber";
    }
}
