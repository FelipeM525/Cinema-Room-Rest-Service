package Cinema.service;

import Cinema.dto.*;
import Cinema.repository.CinemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    private final Cinema cinema;
    private final CinemaRepository cinemaRepository;

    private final CinemaStats cinemaStats;
    private final ModelMapper modelMapper;

    public CinemaService(Cinema cinema, CinemaRepository cinemaRepository, CinemaStats cinemaStats, ModelMapper modelMapper) {
        this.cinema = cinema;
        this.cinemaRepository = cinemaRepository;
        this.cinemaStats = cinemaStats;
        this.modelMapper = modelMapper;
        cinema.setAllSeats(cinemaRepository.ticketPricer());
        cinema.setAvailableSeats(cinemaRepository.seatPricer());
    }

    public ResponseEntity<?> purchaseSeats(Seat seat) {
        if (seat.getRow() == null || seat.getColumn() == null) {
            System.out.println("null value requested");
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        Optional<Order> optionalOrder = cinema.getAllSeats().stream()
                .filter(ticketInList -> ticketInList.getSeat().getRow() == seat.getRow() && ticketInList.getSeat().getColumn() == seat.getColumn())
                .findFirst();
        Order selectedOrder;
        if (optionalOrder.isPresent()) {
            selectedOrder = optionalOrder.get();
        } else {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        }
        System.out.println(selectedOrder + " seat was purchased");
        if (selectedOrder.getSeat().getSeatEmpty() == true) {
            selectedOrder.getSeat().setSeatEmpty(false);
        } else {
            return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(selectedOrder);
    }

    public ResponseEntity<?> refundTicket(Token token) {
        Optional<Order> ticket = cinema.getAllSeats().stream().filter(ticketInList -> ticketInList.getToken().equals(token.getToken())).findFirst();
        Order selectedTicket;
        if (ticket.isPresent()) {
            selectedTicket = ticket.get();

        } else {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
        if (selectedTicket.getSeat().getSeatEmpty() == false) {
            selectedTicket.getSeat().setSeatEmpty(true);
            System.out.println(selectedTicket.getSeat() + " was refunded");
            return new ResponseEntity<>(refundTicketDTO(selectedTicket), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }

    public void getStats() {
        cinemaStats.setNumberOfAvailableSeats(cinema.getAllSeats().stream().filter(ticket -> ticket.getSeat().getSeatEmpty() == true).count());
        cinemaStats.setNumberOfPurchasedTickets(cinema.getAllSeats().stream().filter(ticket -> ticket.getSeat().getSeatEmpty() == false).count());
        List<Order> purchasedSeats = cinema.getAllSeats().stream().filter(ticket -> ticket.getSeat().getSeatEmpty() == false).collect(Collectors.toList());
        Optional<Long> incomeOptional = purchasedSeats.stream().map(ticket -> ticket.getSeat().getPrice()).reduce(Long::sum);
        Long income = incomeOptional.orElse(0L);
        cinemaStats.setCurrentIncome(income);
    }

    public RefundTicketDTO refundTicketDTO(Order order) {
        return modelMapper.map(order, RefundTicketDTO.class);
    }
}
