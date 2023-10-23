package org.myApplication.app;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements org.myApplication.domain.interfaces.User {
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
//    @OneToMany(mappedBy = "user")
//    private List<Book> books;
}