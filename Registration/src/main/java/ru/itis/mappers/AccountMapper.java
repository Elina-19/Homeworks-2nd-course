package ru.itis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.itis.dto.SignUpDto;
import ru.itis.models.Account;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "password", target = "hashPassword")
    Account fromSignUpDtoToAccount(SignUpDto form);
}
