package ru.itis.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.AuthorDto;
import ru.itis.dto.AuthorsPage;
import ru.itis.services.AuthorsService;
import ru.itis.validation.http.ValidationExceptionResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorsService authorsService;

    @Operation(summary = "Get authors with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Page with authors",
            content = {
                @Content(mediaType = "application/json",
                    schema =
                    @Schema(implementation = AuthorsPage.class)
                )
            }
        )
    })
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(
            @Parameter(description = "Page's number") @RequestParam("page") int page){
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Error of author creating",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            )
    })
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto authorDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(authorDto));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Error of author updating",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = ValidationExceptionResponse.class)
                            )
                    }
            )
    })
    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @Valid @RequestBody AuthorDto authorDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(authorId, authorDto));
    }
}
