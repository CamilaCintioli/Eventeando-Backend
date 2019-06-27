package ar.edu.unq.desapp.grupoF.desappgrupoFbackend.repository;

import ar.edu.unq.desapp.grupoF.desappgrupoFbackend.model.event.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {

    List<Event> findAll();

    Optional<Event> getEventById(Long id);

    List<Event> findAllByGuestsEmailOrderByAttendeeCounterDesc(String email);
}
