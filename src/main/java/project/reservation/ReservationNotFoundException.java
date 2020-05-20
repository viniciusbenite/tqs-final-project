package project.reservation;

public class ReservationNotFoundException extends RuntimeException {

    ReservationNotFoundException(Long id) {
        super("Could not find reservation " + id);
    }
}