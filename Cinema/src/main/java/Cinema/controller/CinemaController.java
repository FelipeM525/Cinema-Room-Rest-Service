package Cinema.controller;

import Cinema.dto.Cinema;
import Cinema.dto.CinemaStats;
import Cinema.dto.Seat;
import Cinema.dto.Token;
import Cinema.repository.CinemaRepository;
import Cinema.service.CinemaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {
    private final  Cinema cinema;
    private final CinemaService cinemaService;
    private final CinemaStats cinemaStats;
    private final CinemaRepository cinemaRepository;

    public CinemaController(Cinema cinema, CinemaService cinemaService, CinemaRepository cinemaRepository, CinemaStats cinemaStats) {
        this.cinema = cinema;
        this.cinemaService = cinemaService;
        this.cinemaRepository = cinemaRepository;
        this.cinemaStats = cinemaStats;
    }


    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;

    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            cinemaService.getStats();
            return new ResponseEntity<>(cinemaStats, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeats(@RequestBody Seat seat) {
        return cinemaService.purchaseSeats(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundTicket(@RequestBody Token token) {


        return cinemaService.refundTicket(token);
    }
}
