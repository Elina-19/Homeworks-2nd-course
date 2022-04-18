package ru.itis.validation.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationErrorDto {

    @Schema(example = "firstName")
    private String field;

    @Schema(example = "null")
    private String object;

    @Schema(example = "Name can not be empty")
    private String message;
}
