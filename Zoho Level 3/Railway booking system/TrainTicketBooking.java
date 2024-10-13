package Interview_Programs;

import java.util.*;

class TrainTicketBooking {
    private static int PNR_COUNTER = 0;
    private static final int MAX_SEATS = 8;
    private static final int MAX_WAITING_LIST_SEATS = 2;

    private List<Ticket> tickets = new ArrayList<>();
    private List<Ticket> waitingList = new ArrayList<>();
    private Map<Integer, String[]> seatingChart = new HashMap<>();
    private boolean isWaitingListFull = false;

   

    public TrainTicketBooking() {
        // Initialize seating chart
        for (int i = 1; i <= MAX_SEATS; i++) {
            seatingChart.put(i, new String[]{"", "", "", "", ""});
        }
    }

    public void book(String from, String to, int requestedSeats) {
        int availableSeats = MAX_SEATS - getTotalConfirmedSeats(from, to);

        if (availableSeats >= requestedSeats) {
            List<Integer> allocatedSeats = allocateSeats(from, to, requestedSeats);
            PNR_COUNTER++;
            tickets.add(new Ticket(PNR_COUNTER, from, to, requestedSeats, true, allocatedSeats));
            System.out.println("Booked: " + tickets.get(tickets.size() - 1));
        } else {
            int waitingListSeats = getTotalWaitingListSeats();
            if (!isWaitingListFull && waitingListSeats + requestedSeats <= MAX_WAITING_LIST_SEATS) {
                PNR_COUNTER++;
                waitingList.add(new Ticket(PNR_COUNTER, from, to, requestedSeats, false, new ArrayList<>()));
                System.out.println("Added to Waiting List: " + waitingList.get(waitingList.size() - 1));
                if (waitingListSeats + requestedSeats >= MAX_WAITING_LIST_SEATS) {
                    isWaitingListFull = true;
                }
            } else {
                System.out.println("Not enough seats available or waiting list is full.");
            }
        }
    }
  
    private List<Integer> allocateSeats(String from, String to, int seats) {
        List<Integer> allocatedSeats = new ArrayList<>();
        for (int i = 1; i <= MAX_SEATS; i++) {
            if (isSeatAvailable(i, from, to)) {
                allocatedSeats.add(i);
                markSeatAsBooked(i, from, to);
                if (allocatedSeats.size() == seats) {
                    break;
                }
            }
        }
        return allocatedSeats;
    }

    private boolean isSeatAvailable(int seat, String from, String to) {
        int startIndex = getStationIndex(from);
        int endIndex = getStationIndex(to);
        String[] seatSegment = seatingChart.get(seat);
        for (int i = startIndex; i < endIndex; i++) {
            if (!seatSegment[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void markSeatAsBooked(int seat, String from, String to) {
        int startIndex = getStationIndex(from);
        int endIndex = getStationIndex(to);
        String[] seatSegment = seatingChart.get(seat);
        for (int i = startIndex; i < endIndex; i++) {
            seatSegment[i] = "X";
        }
    }

    private int getStationIndex(String station) {
        switch (station) {
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            case "E": return 4;
            default: throw new IllegalArgumentException("Invalid station: " + station);
        }
    }

    public void cancel(int pnr, int seatsToCancel) {
        Ticket ticket = findTicket(pnr);// validation
        if (ticket != null) {
            List<Integer> seatsToFree = new ArrayList<>(ticket.seatNumbers.subList(0, Math.min(seatsToCancel, ticket.seats)));
            ticket.seats -= seatsToFree.size();
            ticket.seatNumbers.removeAll(seatsToFree);
            freeSeats(seatsToFree, ticket.from, ticket.to);
            System.out.println("Cancelled seats " + seatsToFree + " for PNR: " + pnr);
            if (ticket.seats == 0) {
                tickets.remove(ticket);
            }
            processWaitingList();
        } else {
            System.out.println("PNR not found.");
        }
    }

    private void freeSeats(List<Integer> seatNumbers, String from, String to) {
        for (int seat : seatNumbers) {
            markSeatAsFree(seat, from, to);
        }
    }

    private void markSeatAsFree(int seat, String from, String to) {
        int startIndex = getStationIndex(from);
        int endIndex = getStationIndex(to);
        String[] seatSegment = seatingChart.get(seat);
        for (int i = startIndex; i < endIndex; i++) {
            seatSegment[i] = "";
        }
    }

    private Ticket findTicket(int pnr) {
        for (Ticket ticket : tickets) {
            if (ticket.pnr == pnr) {
                return ticket;
            }
        }
        return null;
    }

    private void processWaitingList() {

        Iterator<Ticket> iterator = waitingList.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            int availableSeats = MAX_SEATS - getTotalConfirmedSeats(ticket.from, ticket.to);
            if (availableSeats >= ticket.seats) {
                List<Integer> allocatedSeats = allocateSeats(ticket.from, ticket.to, ticket.seats);
                tickets.add(new Ticket(ticket.pnr, ticket.from, ticket.to, ticket.seats, true, allocatedSeats));
                iterator.remove();
                isWaitingListFull = false;
                System.out.println("Moved from Waiting List to Confirmed: " + tickets.get(tickets.size() - 1));
            }
        }
    }

    public void printChart() {
        System.out.println("Confirmed Tickets:");
        for (Ticket ticket : tickets) {
            if (ticket.confirmed) {
                System.out.println(ticket);
            }
        }

        System.out.println("\nWaiting List:");
        for (Ticket ticket : waitingList) {
            System.out.println(ticket);
        }

        System.out.println("\nSeating Chart:");
        System.out.println(" Seat    A   B   C   D   E");
        for (int i = 1; i <= MAX_SEATS; i++) {
            System.out.print("    " + i + " ");
            for (String segment : seatingChart.get(i)) {
                System.out.print("   " + (segment.isEmpty() ? " " : "."));
            }
            System.out.println();
        }
    }

    private int getTotalConfirmedSeats(String from, String to) {
        int totalSeats = 0;
        int startIndex = getStationIndex(from);
        int endIndex = getStationIndex(to);
        for (Ticket ticket : tickets) {
            if (ticket.confirmed) {
                if ((getStationIndex(ticket.from) < endIndex &&  getStationIndex(ticket.to) > startIndex) || // This checks if the queried segment overlaps with the segment of a ticket  
                    (getStationIndex(ticket.from) <= startIndex && getStationIndex(ticket.to) > startIndex) ||// the start station of the query is before the end station of the ticket.
                    (getStationIndex(ticket.from) < endIndex && getStationIndex(ticket.to) >= endIndex)) // The end station of the query is after the start station of the ticket.
                	{
                    totalSeats += ticket.seats;
                	}
                
            }
        }
        return totalSeats;
    }

    private int getTotalWaitingListSeats() {
        int totalSeats = 0;
        for (Ticket ticket : waitingList) {
            totalSeats += ticket.seats;
        }
        return totalSeats;
    }
}
