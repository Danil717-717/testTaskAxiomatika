package ru.stroev.testTaskAxiomatika.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stroev.testTaskAxiomatika.models.entities.ApplicationLoan;
import ru.stroev.testTaskAxiomatika.models.entities.Client;
import ru.stroev.testTaskAxiomatika.models.entities.ContractLoan;
import ru.stroev.testTaskAxiomatika.models.enums.FamilyStatus;
import ru.stroev.testTaskAxiomatika.service.ApplicationLoanService;
import ru.stroev.testTaskAxiomatika.service.ClientService;
import ru.stroev.testTaskAxiomatika.service.ContractLoanService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application loan controller
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Controller
@RequestMapping("/application-loan")
@RequiredArgsConstructor
public class ApplicationLoanController {

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final ApplicationLoanService applicationLoanService;

    @Autowired
    private final ContractLoanService contractLoanService;

    @GetMapping()
    public String loanApplication() {
        return "application-loan";
    }

    @PostMapping()
    public String makeApplication(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                if (error instanceof FieldError fieldError) {

                    if (fieldError.getField().equals("phoneNumber")) {
                        client.setPhoneNumber("");
                    } else if (fieldError.getField().equals("idData")) {
                        client.setIdData("");
                    }
                }
            }

            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());

            model.addAttribute("isSingle", client.getFamilyStatus() == FamilyStatus.SINGLE);
            model.addAttribute("isMarried", client.getFamilyStatus() == FamilyStatus.MARRIED);
            model.addAttribute("isDivorced", client.getFamilyStatus() == FamilyStatus.DIVORCED);
            model.addAttribute("errors", errors);
            model.addAttribute("client", client);
            return "application-loan";
        }

        clientService.applyLoan(client);
        ApplicationLoan applicationLoan = applicationLoanService.createNewLoanApplication(client);

        if (applicationLoan.getStatus()) {
            ContractLoan contractLoan = contractLoanService.createNewLoanContract(applicationLoan);
            model.addAttribute("loanContract", contractLoan);
            model.addAttribute("loanApplication", applicationLoan);
            model.addAttribute("client", client);
            return "application-loan-accepted";
        } else {
            return "application-loan-rejected";
        }
    }


    @GetMapping("/show-all-applications")
    public String showAllApplications(Model model) {
        List<ApplicationLoan> applications = applicationLoanService.getAllApplications();
        model.addAttribute("applications", applications);
        return "applications-loan-table";
    }
}
