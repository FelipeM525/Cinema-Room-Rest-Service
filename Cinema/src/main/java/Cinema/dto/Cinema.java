package Cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cinema {
    @JsonProperty("total_rows")
    private final Integer totalRows = 9;
    @JsonProperty("total_columns")
    private final Integer totalColumns = 9;
    @JsonProperty("available_seats")
    private List<Seat> availableSeats;
    @JsonIgnore
    private List<Order> allSeats;

    public Cinema() {
    }

    public Cinema(List<Order> allSeats) {
        this.allSeats = allSeats;
    }


    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }

    public List<Order> getAllSeats() {
        return allSeats;
    }

    public void setAllSeats(List<Order> allSeats) {
        this.allSeats = allSeats;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
