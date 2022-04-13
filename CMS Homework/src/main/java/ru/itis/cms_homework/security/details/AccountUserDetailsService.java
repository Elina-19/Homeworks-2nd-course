package ru.itis.cms_homework.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.cms_homework.repositories.AccountsRepository;

@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountsRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AccountUserDetails(
                accountsRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")));
    }
}
