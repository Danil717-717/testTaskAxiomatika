package ru.stroev.testTaskAxiomatika.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroev.testTaskAxiomatika.models.entities.ApplicationLoan;
import ru.stroev.testTaskAxiomatika.models.entities.ContractLoan;
import ru.stroev.testTaskAxiomatika.repository.ContractLoanRepository;

import java.util.Date;
import java.util.List;

/**
 * Contract loan service
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ContractLoanService {

    @Autowired
    private final ContractLoanRepository contractLoanRepository;

    public ContractLoan createNewLoanContract(ApplicationLoan applicationLoan) {
        ContractLoan contractLoan = new ContractLoan();
        contractLoan.setApplicationLoan(applicationLoan);
        return contractLoanRepository.saveContract(contractLoan);
    }

    public void setContractSigned(ContractLoan contractLoan) {
        contractLoan.setSignatureStatus(true);
        contractLoan.setSignatureDate(new Date());
        contractLoanRepository.saveContract(contractLoan);
    }

    public ContractLoan getLoanContractById(Long id) {
        return contractLoanRepository.getById(id);
    }

    public List<ContractLoan> getAllSignedContracts() {
        return contractLoanRepository.getAllSignedContracts();
    }
}
