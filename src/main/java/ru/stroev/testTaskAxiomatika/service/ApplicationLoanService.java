package ru.stroev.testTaskAxiomatika.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroev.testTaskAxiomatika.models.entities.ApplicationLoan;
import ru.stroev.testTaskAxiomatika.models.entities.Client;
import ru.stroev.testTaskAxiomatika.repository.ApplicationLoanRepository;

import java.util.List;
import java.util.Random;

/**
 * Application loan service
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ApplicationLoanService {

    @Autowired
    private final ApplicationLoanRepository applicationLoanRepository;

    public ApplicationLoan createNewLoanApplication(Client client) {
        Random random = new Random();

        ApplicationLoan loanApplication = new ApplicationLoan();
        loanApplication.setClient(client);
        loanApplication.setStatus(random.nextBoolean());

        if (loanApplication.getStatus()) {
            loanApplication.setTerm(random.nextInt(1, 360));
            loanApplication.setApprovedAmount(client.getLoanAmount());
        }

        return applicationLoanRepository.saveApplication(loanApplication);
    }

    public List<ApplicationLoan> getAllApplications() {
        return applicationLoanRepository.getAllApplications();
    }
}
