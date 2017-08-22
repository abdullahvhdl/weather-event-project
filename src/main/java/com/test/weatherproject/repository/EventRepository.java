package com.test.weatherproject.repository;

import com.test.weatherproject.domain.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Long>{

    List<Event> findAllByLatitudeAndLongitudeAndDate(double latitude, double longitude, @Temporal(TemporalType.DATE) Date date, Pageable pageable);

    List<Event> findAllByDate(@Temporal(TemporalType.DATE) Date date, Pageable pageable);
}
