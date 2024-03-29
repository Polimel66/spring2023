package org.myApplication.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.myApplication.domain.enums.Genres;
import org.myApplication.domain.interfaces.Book;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(exclude = "books")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements org.myApplication.domain.interfaces.User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_contact")
    private String contact;
    @Column(name = "user_city")
    private String city;
    @Column(name = "user_district")
    private String district;
    @Column(name = "user_favorite_genres")
    @Enumerated(EnumType.STRING)
    private List<Genres> favoriteGenres;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bookOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookEntity> books = new ArrayList<>();

    @Override
    public void removeBook(Book book) {
        books.remove((BookEntity) book);
        ((BookEntity) book).setBookOwner(null);
    }

    @Override
    public void addBook(Book book) {
        books.add(((BookEntity) book));
        ((BookEntity) book).setBookOwner(this);
    }
}
