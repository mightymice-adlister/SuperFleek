package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.Look;
import com.mightymice.superfleek.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Looks extends CrudRepository<Look, Long> {
    List<Look> findAllByUser(User user);
//    Look findAllByUserAndProfilePicIsTrue(User user);
}
