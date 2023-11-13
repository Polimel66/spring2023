package org.myApplication.extern.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookModel {
    private String title;
    private String author;
    private int publicationYear;
    private int pagesNumber;
    private int bookCondition;
    private String textBookCondition;
}
