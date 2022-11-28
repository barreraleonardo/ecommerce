package com.nocountry.ecommerce.domain.usecase;

import com.nocountry.ecommerce.domain.model.User;

public interface UserService {

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getByIdIfExist(Long id);

}
