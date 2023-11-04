package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.model.QueueNumber;
import hu.bankblaze.bankblaze.service.QueueNumberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/queue")
public class QueueNumberController {

    private QueueNumberService queueNumberService;


    @GetMapping("/showNumber")
    public String showQueueNumberPage(Model model) {
        model.addAttribute("queueNumber", queueNumberService.getQueueNumber());
        return "showNumber";
    }

    @GetMapping("/queueNumber")
    public String queueQueueNumberPage(Model model) {
        model.addAttribute("newQueueNumber", new QueueNumber());
        return "queueNumber";
    }

    @PostMapping("/queueNumber")
    public String generateQueueNumber(@ModelAttribute("newQueueNumber") QueueNumber newQueueNumber) {
        queueNumberService.generateQueueNumber(newQueueNumber);
        return "redirect:/queue/showNumber";
    }

    @GetMapping("/delete/{id}")
    public String deleteQueueNumber(@PathVariable Long id) {
        queueNumberService.deleteQueueNumberById(id);
        return "redirect:/queue/showNumber";
    }

}