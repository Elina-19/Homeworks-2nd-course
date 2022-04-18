package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Post;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Post")
public class PostDtoResponse {

    private Long id;
    private String title;
    private String text;
    private Post.State state;
    private String link;

    public static PostDtoResponse from(Post post){
        return PostDtoResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .text(post.getText())
                .state(post.getState())
                .link(post.getPhoto().getStorageFileName())
                .build();
    }

    public static List<PostDtoResponse> from(List<Post> posts){
        return posts.stream().map(PostDtoResponse::from).collect(Collectors.toList());
    }
}
