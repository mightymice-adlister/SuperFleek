package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.User;
import org.springframework.data.repository.CrudRepository;

public interface Users extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
