package org.myApplication.extern.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myApplication.domain.enums.Genres;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private String nickname;
    private String password;
    private String contact;
    private String city;
    private String district;
    private List<Genres> favoriteGenres;
}
