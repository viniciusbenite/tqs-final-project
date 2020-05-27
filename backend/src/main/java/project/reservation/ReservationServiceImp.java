package project.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImp implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.getReservationById(id);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteAll() {
        reservationRepository.deleteAll();
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}