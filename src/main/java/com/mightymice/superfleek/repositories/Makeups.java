package com.mightymice.superfleek.repositories;

import com.mightymice.superfleek.models.Makeup;
import org.springframework.data.repository.CrudRepository;

public interface Makeups extends CrudRepository<Makeup, Long> {
}
