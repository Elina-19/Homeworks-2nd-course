package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.PostDtoRequest;
import ru.itis.dto.PostDtoResponse;
import ru.itis.dto.PostsPage;
import ru.itis.exceptions.PostNotFoundException;
import ru.itis.models.Author;
import ru.itis.models.FileInfo;
import ru.itis.models.Post;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.repositories.PostsRepository;
import ru.itis.services.FilesService;
import ru.itis.services.PostsService;

import java.time.LocalDateTime;

import static ru.itis.dto.PostDtoResponse.from;
import static ru.itis.models.Post.State.DELETED;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final AuthorsRepository authorsRepository;
    private final PostsRepository postsRepository;

    private final FilesService filesService;

    @Value("${blog.posts-page-size}")
    private Integer defaultPageSize;

    @Transactional
    @Override
    public PostDtoResponse addPost(Long authorId, PostDtoRequest post) {

        Author author = authorsRepository.getById(authorId);
        FileInfo fileInfo = filesService.upload(post.getFile());

        return from(postsRepository.save(
                Post.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .text(post.getText())
                    .state(post.getState())
                    .author(author)
                    .photo(fileInfo)
                    .createdAt(LocalDateTime.now())
                    .build()
        ));
    }

    @Transactional
    @Override
    public PostDtoResponse updatePost(Long postId, PostDtoRequest newData) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        FileInfo fileInfo = filesService.upload(newData.getFile());

        post.setTitle(newData.getTitle());
        post.setText(newData.getText());
        post.setState(newData.getState());
        post.setPhoto(fileInfo);

        return from(postsRepository.save(post));
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setState(DELETED);

        postsRepository.save(post);
    }

    @Override
    public PostsPage getPosts(Long authorId, int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize,
                Sort.by("createdAt").descending());

        Page<Post> postPage = postsRepository.findByAuthorId(authorId, pageRequest);

        return PostsPage.builder()
                    .posts(from(postPage.getContent()))
                    .totalPages(postPage.getTotalPages())
                    .build();
    }
}
