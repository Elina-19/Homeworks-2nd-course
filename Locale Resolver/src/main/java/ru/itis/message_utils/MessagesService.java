package ru.itis.message_utils;

import ru.itis.message_utils.models.Message;

import java.util.Locale;

public interface MessagesService {
    Message getMessageByLocaleAndCode(Locale locale, String key);
}
