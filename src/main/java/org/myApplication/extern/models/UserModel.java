package org.myApplication.extern.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myApplication.domain.enums.Genres;
import org.myApplication.domain.validation.PasswordCorrectness;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    @NotBlank
    private String nickname;
    @PasswordCorrectness
    private String password;
    @NotBlank
    private String contact;
    @NotBlank
    private String city;
    private String district;
    private List<Genres> favoriteGenres;
}
