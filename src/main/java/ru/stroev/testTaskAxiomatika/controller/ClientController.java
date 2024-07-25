package ru.stroev.testTaskAxiomatika.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stroev.testTaskAxiomatika.models.entities.Client;
import ru.stroev.testTaskAxiomatika.service.ClientService;

import java.util.List;

/**
 * Client controller
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Controller
@RequestMapping("client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    private final ClientService clientService;

    @GetMapping("/admin-part")
    public String mainPage() {
        return "admin-part";
    }

    @GetMapping("/client-part")
    public String clientPage() {
            return "client-part";
    }

    @GetMapping("/show-all-clients")
    public String showAllClients(Model model){
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        model.addAttribute("idData", "");
        model.addAttribute("phoneNumber", "");
        model.addAttribute("fullName", "");
        return "clients-table";
    }

    @GetMapping("/filterClients")
    public String showAllClientsWithFilters(@RequestParam(value = "idData", required = false) String idData,
                                            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                            @RequestParam(value = "fullName", required = false) String fullName,
                                            Model model){

        List<Client> clients = clientService.clientsByFilters(idData, phoneNumber, fullName);
        model.addAttribute("clients", clients);
        model.addAttribute("idData", idData);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("fullName", fullName);
        return "clients-table";
    }

    @GetMapping("/reset-filters")
    public String resetFilters(@RequestParam(value = "idData", required = false) String idData,
                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(value = "fullName", required = false) String fullName,
                               Model model){

        model.addAttribute("idData", "");
        model.addAttribute("phoneNumber", "");
        model.addAttribute("fullName", "");
        return "redirect:/client/show-all-clients";
    }
}
