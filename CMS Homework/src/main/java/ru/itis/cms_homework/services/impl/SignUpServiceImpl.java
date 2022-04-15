package ru.itis.cms_homework.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.cms_homework.dto.SignUpForm;
import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.repositories.AccountsRepository;
import ru.itis.cms_homework.services.SignUpService;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .username(signUpForm.getUsername())
                .role(Account.Role.ROLE_USER)
                .build();

        accountsRepository.save(account);
    }
}
