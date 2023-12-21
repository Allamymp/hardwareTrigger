package com.sd.fishapi.repository;


import com.sd.fishapi.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {

    Event findByDate(LocalDateTime instant);
}
