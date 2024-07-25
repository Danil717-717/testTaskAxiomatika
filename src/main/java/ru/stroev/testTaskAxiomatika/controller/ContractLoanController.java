package ru.stroev.testTaskAxiomatika.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.stroev.testTaskAxiomatika.models.entities.ContractLoan;
import ru.stroev.testTaskAxiomatika.service.ContractLoanService;

import java.util.List;

/**
 * Contract loan controller
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("contract")
public class ContractLoanController {

    private final ContractLoanService contractLoanService;

    @PostMapping("/signed")
    public String signContract(@RequestParam("loanContractId") String loanContractId) {
        ContractLoan contractLoan = contractLoanService.getLoanContractById(Long.parseLong(loanContractId));
        if (contractLoan.getApplicationLoan().getStatus()) {
            contractLoanService.setContractSigned(contractLoan);
            return "contract-loan-signed";
        } else {
            throw new IllegalArgumentException("Loan not approved!");
        }
    }

    @GetMapping("/show-all-signed-contracts")
    public String showAllContracts(Model model) {
        List<ContractLoan> contracts = contractLoanService.getAllSignedContracts();
        model.addAttribute("contracts", contracts);
        return "contracts-table";
    }
}
