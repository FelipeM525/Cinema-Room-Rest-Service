package Cinema.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

@Component
public class Seat {
    private Integer row;
    private Integer column;
    private Long price;
    @JsonIgnore
    private Boolean seatEmpty;

    public Seat() {
    }


    public Seat(Integer row, Integer column, Long price, Boolean seatEmpty) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.seatEmpty = seatEmpty;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getSeatEmpty() {
        return seatEmpty;
    }

    public void setSeatEmpty(Boolean seatEmpty) {
        this.seatEmpty = seatEmpty;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", seatEmpty=" + seatEmpty +
                '}';
    }
}
