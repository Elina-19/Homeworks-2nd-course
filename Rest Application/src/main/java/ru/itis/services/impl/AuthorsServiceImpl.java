package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.dto.AuthorDto;
import ru.itis.dto.AuthorsPage;
import ru.itis.exceptions.AuthorNotFoundException;
import ru.itis.models.Author;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.services.AuthorsService;

import static ru.itis.dto.AuthorDto.from;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;

    @Value("${blog.authors-page-size}")
    private Integer defaultPageSize;

    @Override
    public AuthorsPage getAuthors(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Author> authorPage = authorsRepository.findAll(pageRequest);

        return AuthorsPage.builder()
                .authors(from(authorPage.getContent()))
                .totalPages(authorPage.getTotalPages())
                .build();
    }

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        return from(authorsRepository.save(
                Author.builder()
                    .id(author.getId())
                    .firstName(author.getFirstName())
                    .lastName(author.getLastName())
                    .build()));
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto newData) {
        Author author = authorsRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);

        author.setFirstName(newData.getFirstName());
        author.setLastName(newData.getLastName());

        return from(authorsRepository.save(author));
    }
}
