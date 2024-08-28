package Interview_Programs;

import java.util.List;

public class Ticket {
    int pnr;
    String from;
    String to;
    int seats;
    boolean confirmed;
    List<Integer> seatNumbers;

    public Ticket(int pnr, String from, String to, int seats, boolean confirmed, List<Integer> seatNumbers) {
        this.pnr = pnr;
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.confirmed = confirmed;
        this.seatNumbers = seatNumbers;
    }

    public String toString() {
        return String.format("PNR: %d, From: %s, To: %s, Seats: %d, Status: %s, Seat Numbers: %s",
                pnr, from, to, seats, confirmed ? "Booked" : "Waiting List", seatNumbers);
    }
}
