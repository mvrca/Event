package app.event.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.event.Models.Event;
import app.event.Models.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByEvent(Event event);
    Guest findByPhone(String phone);
}
