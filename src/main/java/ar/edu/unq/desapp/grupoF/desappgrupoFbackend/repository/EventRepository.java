package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    Page<Event> findAllByGuestsEmailOrderByAttendeeCounterDesc(String email, Pageable pageable);

    Page<Event> findAllByGuestsEmailAndDayOfEventLessThanOrderByDayOfEventDesc(String email, LocalDateTime date, Pageable pageable);

    Page<Event> findAllByGuestsEmailAndDayOfEventGreaterThanEqualOrderByDayOfEventAsc(String email, LocalDateTime date, Pageable pageable);
}
