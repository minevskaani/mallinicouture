package io.mallinicouture.backend.security;

import io.mallinicouture.backend.domain.Client;
import io.mallinicouture.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client user = clientRepository.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

    @Transactional
    public Client loadUserById(Long id) {
        Client user = clientRepository.getById(id);

        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }

}
