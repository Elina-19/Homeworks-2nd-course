package ru.itis.message_utils.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.exceptions.NoSuchMessageException;
import ru.itis.repositories.MessagesRepository;
import ru.itis.message_utils.MessagesService;
import ru.itis.message_utils.models.LocaleModel;
import ru.itis.message_utils.models.Message;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class MessagesServiceImpl implements MessagesService {

    private final MessagesRepository messagesRepository;

    @Override
    public Message getMessageByLocaleAndCode(Locale locale, String key) {

        LocaleModel localeModel = LocaleModel.builder()
                                        .language(locale.getLanguage())
                                        .build();

        return messagesRepository
                .getMessageByLocaleAndKey(localeModel.getLanguage(), key)
                .orElseThrow(NoSuchMessageException::new);
    }
}
