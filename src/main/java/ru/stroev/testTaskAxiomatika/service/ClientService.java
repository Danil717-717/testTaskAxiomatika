package ru.stroev.testTaskAxiomatika.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stroev.testTaskAxiomatika.models.entities.Client;
import ru.stroev.testTaskAxiomatika.repository.ClientRepository;

import java.util.List;

/**
 * Client service
 *
 * @author Строев Д.В.
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public void applyLoan(Client client) {
        clientRepository.save(client);
    }

    public Client getClientByIdData(String idData) {
        return clientRepository.getByIdData(idData);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public List<Client> clientsByFilters(String idData, String phoneNumber, String fullName) {
        return clientRepository.findByFilters(idData, phoneNumber, fullName);
    }
}
