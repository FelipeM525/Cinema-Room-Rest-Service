package Cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class CinemaStats {
    @JsonProperty("current_income")
    private Long currentIncome;
    @JsonProperty("number_of_available_seats")
    private Long numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private Long numberOfPurchasedTickets;

    public CinemaStats() {
    }

    public CinemaStats(Long currentIncome, Long numberOfAvailableSeats, Long numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailableSeats = numberOfAvailableSeats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public Long getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(Long currentIncome) {
        this.currentIncome = currentIncome;
    }

    public Long getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public void setNumberOfAvailableSeats(Long numberOfAvailableSeats) {
        this.numberOfAvailableSeats = numberOfAvailableSeats;
    }

    public Long getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public void setNumberOfPurchasedTickets(Long numberOfPurchasedTickets) {
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    @Override
    public String toString() {
        return "CinemaStats{" +
                "currentIncome=" + currentIncome +
                ", numberOfAvailableSeats=" + numberOfAvailableSeats +
                ", numberOfPurchasedTickets=" + numberOfPurchasedTickets +
                '}';
    }
}
