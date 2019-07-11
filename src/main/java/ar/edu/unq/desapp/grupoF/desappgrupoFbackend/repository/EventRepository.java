package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {

    List<Event> findAll();

    Optional<Event> getEventById(Long id);

    //POPULAR EVENTS
    Page<Event> findDistinctEventByDayOfEventGreaterThanAndCreatorEmailOrDayOfEventGreaterThanAndGuestsOrderByAttendeeCounterDesc(LocalDateTime dayOfEvent, String creatorEmail, LocalDateTime date, String guest, Pageable pageable);

    //ONGOING EVENTS
    Page<Event> findDistinctEventByDayOfEventGreaterThanAndCreatorEmailOrDayOfEventGreaterThanAndGuests(LocalDateTime dayOfEvent, String creatorEmail, LocalDateTime date, String guest, Pageable pageable);

    //LAST EVENT
    Page<Event> findDistinctEventByDayOfEventLessThanAndCreatorEmailOrDayOfEventLessThanAndGuests(LocalDateTime dayOfEvent, String creatorEmail, LocalDateTime date, String guest, Pageable pageable);
    
}
