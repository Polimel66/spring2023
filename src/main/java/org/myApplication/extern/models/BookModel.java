package org.myApplication.extern.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookModel {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    @Positive
    private int publicationYear;
    @NotNull
    @Min(1)
    private int pagesNumber;
    @NotNull
    @Min(1)
    @Max(10)
    private int bookCondition;
    private String textBookCondition;
}
