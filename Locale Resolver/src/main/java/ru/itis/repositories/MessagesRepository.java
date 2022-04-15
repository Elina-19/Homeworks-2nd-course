package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.message_utils.models.Message;

import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m inner join m.locale l where l.language = :locale and m.key = :key")
    Optional<Message> getMessageByLocaleAndKey(@Param("locale") String locale, @Param("key") String key);
}
