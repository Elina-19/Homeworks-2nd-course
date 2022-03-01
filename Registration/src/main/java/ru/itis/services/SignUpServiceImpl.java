package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignUpDto;
import ru.itis.exceptions.NotAvailablePasswordException;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.util.EmailValidator;
import ru.itis.util.EmptyValidator;
import ru.itis.util.HashFunction;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    private final EmptyValidator emptyValidator;
    private final EmailValidator emailValidator;

    @Override
    public void signUp(SignUpDto form) {

        if(emptyValidator.isNull(form.getFirstName()) || emptyValidator.isEmpty(form.getFirstName())){
            throw new NullPointerException("Введите имя");
        }

        if(emptyValidator.isNull(form.getLastName()) || emptyValidator.isEmpty(form.getLastName())){
            throw new NullPointerException("Введите фамилию");
        }

        if(emptyValidator.isNull(form.getEmail()) || emptyValidator.isEmpty(form.getEmail())){
            throw new NullPointerException("Введите email");
        }

        if(emptyValidator.isNull(form.getPassword()) || emptyValidator.isEmpty(form.getPassword())){
            throw new NullPointerException("Введите пароль");
        }

        emailValidator.checkEmail(form.getEmail());

        if (accountsRepository.findByEmail(form.getEmail()).isPresent()){
            throw new NotAvailablePasswordException("Профиль с таким email уже существует");
        }

        form.setPassword(HashFunction.hash(form.getPassword()));

        accountsRepository.save(Account.builder()
        .firstName(form.getFirstName())
        .lastName(form.getLastName())
        .email(form.getEmail())
        .hashPassword(form.getPassword())
        .build());
    }
}
