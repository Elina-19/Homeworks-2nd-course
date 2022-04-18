package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.Post;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Post")
public class PostDtoRequest {
    @Schema(description = "Identifier", example = "1")
    private Long id;

    @NotBlank(message = "Title can not be empty")
    @Size(min = 1, max = 50, message = "Min length of title {min}, max length {max}")
    @Schema(description = "Title", example = "Cute cats and dogs")
    private String title;

    @NotBlank(message = "Text can not be empty")
    @Size(min = 1, message = "Min length of text {min}, max length {max}")
    @Schema(description = "Text in post")
    private String text;

    @Schema(description = "State of the post", example = "PUBLISHED")
    private Post.State state;

    private MultipartFile file;

    public static PostDtoRequest from(Post post){
        return PostDtoRequest.builder()
                .id(post.getId())
                .title(post.getTitle())
                .text(post.getText())
                .state(post.getState())
                .build();
    }

    public static List<PostDtoRequest> from(List<Post> posts){
        return posts.stream().map(PostDtoRequest::from).collect(Collectors.toList());
    }
}
