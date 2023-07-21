package com.lincon.bookstoremanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotNull
    private Integer pages;

    @NotNull
    private Integer chapters;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "(?i)^(?:(?:ISBN(?:-10)?:?\\s*)?|(?=(?:\\d{13}|(?:\\d{10}|\\d{9}[\\dxX]))$))"
            + "(?=(?:[^-\\d]|$))[-\\d ]{13}|(?:\\d{10}|\\d{9}[\\dxX])$", message = "ISBN format must be a valid format")
    private String isbn;

    @NotBlank
    @Size(max = 200)
    private String publisherName;

    @NotNull
    private AuthorDTO author;
}
