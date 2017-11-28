package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.Makeup;
import com.mightymice.superfleek.models.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Reviews extends CrudRepository<Review, Long> {
    List<Makeup>findAllByMakeup(Makeup makeup);
}
