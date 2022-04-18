package ru.itis.services;

import ru.itis.dto.PostDtoRequest;
import ru.itis.dto.PostDtoResponse;
import ru.itis.dto.PostsPage;

public interface PostsService {
    PostDtoResponse addPost(Long authorId, PostDtoRequest post);
    PostDtoResponse updatePost(Long postId, PostDtoRequest newData);
    void deletePost(Long postId);
    PostsPage getPosts(Long authorId, int page);
}
