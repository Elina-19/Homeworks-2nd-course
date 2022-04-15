package ru.itis.message_utils.impl;

import java.text.MessageFormat;
import java.util.Locale;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import ru.itis.message_utils.MessagesService;

@RequiredArgsConstructor
public class SimpleMessagesLoader extends AbstractResourceBasedMessageSource {

    private final MessagesService messagesService;

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {

        return messagesService
                .getMessageByLocaleAndCode(locale, code)
                .getMessage();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        return null;
    }
}

