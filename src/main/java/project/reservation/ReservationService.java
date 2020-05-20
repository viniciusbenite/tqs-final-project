package project.reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> getReservationById(Long id);

    List<Reservation> getAllReservation();

    void save(Reservation reservation);
    void deleteAll();
    void deleteReservationById(Long id);
}
