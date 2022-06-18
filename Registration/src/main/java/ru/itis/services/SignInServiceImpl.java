package ru.itis.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.dto.SignInDto;
import ru.itis.exceptions.InvalidEmailException;
import ru.itis.exceptions.InvalidPasswordException;
import ru.itis.models.Account;
import ru.itis.repositories.AccountsRepository;
import ru.itis.util.EmptyValidator;
import ru.itis.util.HashFunction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignInServiceImpl implements SignInService {

    private static final String UUID_COOKIE = "UUID";
    private static final int AGE_OF_COOKIE = 60*60*24*180;

    private final AccountsRepository accountsRepository;
    private final EmptyValidator emptyValidator;

    @Override
    public Account signIn(SignInDto form, HttpServletRequest request, HttpServletResponse response) {
        if(emptyValidator.isNull(form.getEmail()) || emptyValidator.isEmpty(form.getEmail())){
            throw new NullPointerException("Введите email");
        }

        if(emptyValidator.isNull(form.getPassword()) || emptyValidator.isEmpty(form.getPassword())){
            throw new NullPointerException("Введите пароль");
        }
        Optional<Account> accountOptional = accountsRepository.findByEmail(form.getEmail());

        if (!accountOptional.isPresent()){
            throw new InvalidEmailException("Профиля с таким email не существует");
        }

        Account account = accountOptional.get();

        if(!HashFunction.hash(form.getPassword()).equals(account.getHashPassword())){
            throw new InvalidPasswordException("Неверный пароль");
        }

        HttpSession httpSession = request.getSession(true);

        account.setUuid(httpSession.getId());
        accountsRepository.updateUuid(account.getEmail(), httpSession.getId());

        Cookie cookie = new Cookie(UUID_COOKIE, account.getUuid());
        cookie.setMaxAge(AGE_OF_COOKIE);
        response.addCookie(cookie);

        httpSession.setAttribute("account", account);

        return account;
    }

    @Override
    public boolean authenticateByUUID(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null){
            return false;
        }

        for (Cookie cookie: cookies){
            if (cookie.getName().equals(UUID_COOKIE)){
                Optional<Account> optionalAccount = accountsRepository.findByUuid(cookie.getValue());

                if (optionalAccount.isPresent()){
                    HttpSession session = request.getSession();
                    session.setAttribute("account", optionalAccount.get());

                    return true;
                }else return false;
            }
        }

        return false;
    }
}
