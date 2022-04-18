package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Page with posts and quantity of pages")
public class PostsPage {
    private List<PostDtoResponse> posts;

    @Schema(description = "Quantity of available pages")
    private Integer totalPages;
}
