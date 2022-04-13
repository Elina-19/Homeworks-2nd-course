package ru.itis.cms_homework.utils;

import ru.itis.cms_homework.models.Account;
import ru.itis.cms_homework.security.details.AccountUserDetails;

public class UserDetailsHelper {

    public static Account getAccount(AccountUserDetails userDetails){
        if (userDetails == null){

            return Account.builder()
                    .role(Account.Role.USER)
                    .build();
        }else {
            return userDetails.getAccount();
        }
    }
}
