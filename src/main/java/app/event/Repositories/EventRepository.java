package app.event.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.event.Models.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
