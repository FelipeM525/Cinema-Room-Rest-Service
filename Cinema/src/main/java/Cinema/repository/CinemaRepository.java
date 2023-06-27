package Cinema.repository;

import Cinema.dto.Cinema;
import Cinema.dto.Order;
import Cinema.dto.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository

public class CinemaRepository {
    private final Cinema cinema;

    public CinemaRepository(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Order> ticketGenerator() {
        List<Order> totalSeats = new ArrayList<>();
        for (int row = 1; row <= cinema.getTotalRows(); row++) {
            for (int column = 1; column <= cinema.getTotalColumns(); column++) {
                totalSeats.add(new Order(UUID.randomUUID(), new Seat(row, column, 0L, true)));
            }
        }
        return totalSeats;
    }

    public List<Order> ticketPricer() {
        List<Order> collectedSeats = ticketGenerator().stream().map(ticket -> {
            if (ticket.getSeat().getRow() <= 4) {
                ticket.getSeat().setPrice(10L);
            } else {
                ticket.getSeat().setPrice(8L);
            }
            return ticket;
        }).collect(Collectors.toList());

        List<Order> pricedSeats = collectedSeats;
        return pricedSeats;
    }

    public List<Seat> seatGenerator() {
        List<Seat> totalSeats = new ArrayList<>();
        for (int row = 1; row <= cinema.getTotalRows(); row++) {
            for (int column = 1; column <= cinema.getTotalColumns(); column++) {
                totalSeats.add(new Seat(row, column, 0L, true));
            }
        }
        return totalSeats;
    }

    public List<Seat> seatPricer() {
        List<Seat> pricedSeats = seatGenerator().stream().map(seat -> {
            if (seat.getRow() <= 4) {
                seat.setPrice(10L);
            } else {
                seat.setPrice(8L);
            }
            return seat;
        }).collect(Collectors.toList());
        return pricedSeats;
    }
}
