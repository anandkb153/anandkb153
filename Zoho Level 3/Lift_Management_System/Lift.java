package Lift_Management_System;

public class Lift {
    private String name;
    private int currentFloor;
    private boolean underMaintenance;
    private int minFloor;
    private int maxFloor;

    public Lift(String name, int initialFloor, int minFloor, int maxFloor) {
        this.name = name;
        this.currentFloor = initialFloor;
        this.underMaintenance = false;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public String getName() {
        return name;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public void moveToFloor(int floor) {
        if (floor >= minFloor && floor <= maxFloor) {
            currentFloor = floor;
        } else {
            System.out.println(name + " cannot serve this floor.");
        }
    }

    public boolean canServeFloor(int floor) {
        return floor >= minFloor && floor <= maxFloor;
    }

    public void printCurrentFloor() {
        System.out.println(name + " is currently at floor " + currentFloor);
    }
}
