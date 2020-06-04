package project.reservation;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static project.constants.Paths.RESERVATION;

@RestController
@RequestMapping(value = RESERVATION)
@Api(value = "Métodos para gerenciar reservas")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @ApiOperation("Get all reservations")
    @CrossOrigin(origins="*")
    @GetMapping()
    public List<Reservation> all() {
        return reservationRepository.findAll();
    }

    @ApiOperation("Create a new reservation")
    @CrossOrigin(origins="*")
    @PostMapping("/")
    public Reservation newReservation(@RequestBody Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    @ApiOperation("Get a single reservation")
    @CrossOrigin(origins="*")
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationRepository.getReservationById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @ApiOperation("Edit a reservation")
    @CrossOrigin(origins="*")
    @PutMapping("/{id}")
    public Reservation editReservation(@RequestBody Reservation newReservation,
                                       @PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setUser(newReservation.getUser());
                    reservation.setService(newReservation.getService());
                    reservation.setDate(newReservation.getDate());
                    reservation.setTime(newReservation.getTime());
                    reservation.setId(newReservation.getId());
                    return reservationRepository.save(reservation);
                })
                .orElseGet(() -> {
                    newReservation.setId(id);
                    return reservationRepository.save(newReservation);
                });
    }

    @ApiOperation("Delete a reservation")
    @CrossOrigin(origins="*")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    @ApiOperation("Delete all operations")
    @CrossOrigin(origins="*")
    @DeleteMapping("/")
    public void purgeReservations() {
        reservationRepository.deleteAll();
    }
}
