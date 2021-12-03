package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.Movie;
import com.epam.training.ticketservice.core.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findAll();

    Optional<Screening> findByMovieAndRoomAndDateTime(Movie movie, Room room, LocalDateTime dateTime);
}
