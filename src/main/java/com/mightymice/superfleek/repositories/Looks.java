package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.Look;
import com.mightymice.superfleek.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Looks extends CrudRepository<Look, Long> {
    Look findAllByUser(User user);
    Look findByUserAndProfilePic(User user, Boolean isProfilePic);
}
