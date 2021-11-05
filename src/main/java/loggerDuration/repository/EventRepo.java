package loggerDuration.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import loggerDuration.model.Event;
import loggerDuration.model.EventId;

@Repository
public interface EventRepo extends JpaRepository<Event, EventId> {
	Stream<Event> findById(String id);
}
