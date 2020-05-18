package io.mallinicouture.backend.service;

import io.mallinicouture.backend.domain.Client;
import io.mallinicouture.backend.domain.CreditCard;
import io.mallinicouture.backend.exception.UsernameAlreadyExistsException;
import io.mallinicouture.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client saveClient(Client client) {
        try {
            // encrypt password
            client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));

            // Username already exists exception
            return clientRepository.save(client);
        } catch (Exception e) {
            throw new UsernameAlreadyExistsException("Username '" + client.getUsername() + "' already exists");
        }
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public CreditCard addCreditCard(String username, CreditCard creditCard) {
        Client client = clientRepository.findByUsername(username);

        client.setCreditCard(creditCard);
        Client saved = clientRepository.save(client);

        return saved.getCreditCard();
    }
}
