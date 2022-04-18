package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Author;
import ru.itis.validation.annotations.NotSameNames;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Author")
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class AuthorDto {

    @Schema(description = "Identifier", example = "1")
    private Long id;

    @NotBlank(message = "Name can not be empty")
    @Size(min = 1, max = 30, message = "Min length of name {min}, max length {max}")
    @Schema(description = "Name", example = "Элина")
    private String firstName;

    @NotBlank(message = "Surname can not be empty")
    @Size(min = 1, max = 50, message = "Min length of surname {min}, max length {max}")
    @Column(name = "last_name")
    @Schema(description = "Surname", example = "Загидуллина")
    private String lastName;

    public static AuthorDto from(Author author){
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> from(List<Author> authors){
        return authors.stream().map(AuthorDto::from).collect(Collectors.toList());
    }
}
