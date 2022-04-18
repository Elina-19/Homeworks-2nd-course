package ru.itis.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.PostDtoRequest;
import ru.itis.dto.PostDtoResponse;
import ru.itis.dto.PostsPage;
import ru.itis.services.PostsService;
import ru.itis.validation.http.ValidationExceptionResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostsService postsService;

    @Operation(summary = "Add post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added post",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDtoRequest.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Error of post creating",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            )
    })
    @PostMapping(value = "/authors/{author-id}/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDtoResponse> addPost(
            @Parameter(description = "Author's id who added the post") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Content of post") @Valid PostDtoRequest postDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPost(authorId, postDto));
    }

    @Operation(summary = "Get posts with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with posts",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class)
                            )
                    }
            )
    })
    @GetMapping("/authors/{author-id}/posts")
    public ResponseEntity<PostsPage> getPosts(
            @Parameter(description = "Author's id whose posts need to get") @PathVariable("author-id") Long authorId,
            @Parameter(description = "Page's number") @RequestParam("page") int page){
        return ResponseEntity.ok(postsService.getPosts(authorId, page));
    }

    @Operation(summary = "Updated post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated post",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDtoRequest.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "Error of post updating",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            )
    })
    @PutMapping(value = "/authors/{author-id}/posts/{post-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDtoResponse> updatePost(
            @Parameter(description = "Post's id whose need to update") @PathVariable("post-id") Long postId,
            @Parameter(description = "New data of post") @Valid PostDtoRequest postDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.updatePost(postId, postDto));
    }

    @Operation(summary = "Delete post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete post")
    })
    @DeleteMapping("/posts/{post-id}")
    public ResponseEntity deletePost(
            @Parameter(description = "Post's id whose need to delete") @PathVariable("post-id") Long postId){
        postsService.deletePost(postId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
