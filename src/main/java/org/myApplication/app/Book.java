package org.myApplication.app;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements org.myApplication.domain.interfaces.Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_title")
    private String title;
    @Column(name = "book_author")
    private String author;
    @Column(name = "book_publication_year")
    private int publicationYear;
    @Column(name = "book_pages_number")
    private int pagesNumber;
    @Column(name = "book_condition")
    private int bookCondition;
    @Column(name = "text_book_condition")
    private String textBookCondition;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User bookOwner;
}
