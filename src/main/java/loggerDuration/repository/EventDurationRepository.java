package loggerDuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import loggerDuration.model.EventDuration;

@Repository
public interface EventDurationRepository extends JpaRepository<EventDuration, String> {

}
