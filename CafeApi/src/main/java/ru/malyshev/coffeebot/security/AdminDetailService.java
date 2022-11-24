package ru.malyshev.coffeebot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeebot.security.repo.AdminRepo;

@Service
@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepo.findByUsername(username);
    }
}