import java.util.*;
class Journey {
    String startPoint;
    String endPoint;
    List<Integer> tollsPassed;
    int amountPaid;

    public Journey(String startPoint, String endPoint, List<Integer> tollsPassed, int amountPaid) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.tollsPassed = tollsPassed;
        this.amountPaid = amountPaid;
    }
}