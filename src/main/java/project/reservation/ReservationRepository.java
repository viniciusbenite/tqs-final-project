package project.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.saloon.Saloon;
import project.user.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();
    void deleteAll();
    void deleteById(Long id);

    Optional<Reservation> getReservationById(Long id);

}