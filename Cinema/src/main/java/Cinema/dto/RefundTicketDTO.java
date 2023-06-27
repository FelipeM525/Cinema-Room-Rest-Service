package Cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class RefundTicketDTO {
    @JsonIgnore
    private UUID token;
    @JsonProperty("returned_ticket")
    private Seat seat;

    public RefundTicketDTO() {
    }

    public RefundTicketDTO(UUID token, Seat seat) {
        this.token = token;
        this.seat = seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Order{" +
                "token=" + token +
                ", seat=" + seat +
                '}';
    }
}
