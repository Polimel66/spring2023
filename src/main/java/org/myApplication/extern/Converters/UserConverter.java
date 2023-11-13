package org.myApplication.extern.Converters;

import org.myApplication.app.Entities.BookEntity;
import org.myApplication.app.Entities.UserEntity;
import org.myApplication.extern.Models.BookModel;
import org.myApplication.extern.Models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity toEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname(userModel.getNickname());
        userEntity.setPassword(userModel.getPassword());
        userEntity.setContact(userModel.getContact());
        userEntity.setCity(userModel.getCity());
        userEntity.setDistrict(userModel.getDistrict());
        return userEntity;
    }

    public UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setNickname(userEntity.getNickname());
        userModel.setPassword(userEntity.getPassword());
        userModel.setContact(userEntity.getContact());
        userModel.setCity(userEntity.getCity());
        userModel.setDistrict(userEntity.getDistrict());
        return userModel;
    }
}
