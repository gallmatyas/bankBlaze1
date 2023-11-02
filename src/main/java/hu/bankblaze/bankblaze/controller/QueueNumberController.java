package hu.bankblaze.bankblaze.controller;

import hu.bankblaze.bankblaze.service.QueueNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/queue")
public class QueueNumberController {

    private QueueNumberService queueNumberService;

    @Autowired
    public QueueNumberController(QueueNumberService queueNumberService){
        this.queueNumberService = queueNumberService;
    }

    @GetMapping("/shownumber")
    public String showQueueNumberPage(Model model) {
        model.addAttribute("queueNumber", queueNumberService.getQueueNumber());
        return "shownumber";
    }

    @GetMapping("/queuenumber")
    public String queueQueueNumberPage(Model model) {
        model.addAttribute("newQueueNumber", new queueNumber());
        return "queuenumber";
    }

    @PostMapping("/queuenumber")
    public String generateQueueNumber(@ModelAttribute("newQueueNumber") QueueNumber newQueueNumber) {
        queueNumberService.generateQueueNumber(newQueueNumber);
        return "redirect:/queue/shownumber";
    }

    @GetMapping("/delete/{id}")
    public String deleteQueueNumber(@PathVariable Long id) {
        queueNumberService.deleteQueueNumberById(id);
        return "redirect:/queue/shownumber";
    }

}