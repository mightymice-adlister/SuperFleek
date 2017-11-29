package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.MakeupList;
import com.mightymice.superfleek.models.User;
import org.springframework.data.repository.CrudRepository;

public interface MakeupLists extends CrudRepository<MakeupList, Long> {
    MakeupList findByTitle(String title);
    MakeupList findByTitleAndUser(String title, User user);
}
