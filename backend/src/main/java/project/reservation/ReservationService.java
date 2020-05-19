package project.reservation;

import project.saloon.Saloon;
import project.user.User;

import java.util.List;

public interface ReservationService {

    Reservation getReservationById(Long id);
    List<Reservation> getAllReservation();

    void save(Reservation reservation);
    void deleteAll();
    void deleteReservationById(Long id);
}
