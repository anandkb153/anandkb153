package Lift_Management_System;

public class LiftSystem {
    private Lift[] lifts;

    public LiftSystem() {
        lifts = new Lift[]{
            new Lift("L1", 0, 0, 5), // L1 can serve floors 0-5
            new Lift("L2", 0, 0, 5), // L2 can serve floors 0-5
            new Lift("L3", 0, 6, 10), // L3 can serve floors 6-10
            new Lift("L4", 0, 6, 10), // L4 can serve floors 6-10
            new Lift("L5", 0, 0, 10)  // L5 can serve all floors 0-10
        };
    }

    public void displayLiftPositions() {
        System.out.println("Lift Positions:");
        for (Lift lift : lifts) {
            lift.printCurrentFloor();
        }
    }

    public void assignLift(int sourceFloor, int destinationFloor) {
        Lift selectedLift = null;
        int minimumDistance = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (!lift.isUnderMaintenance() && lift.canServeFloor(sourceFloor) && lift.canServeFloor(destinationFloor)) {
                // Calculate the distance from the lift's current position to the source floor
                int distance = Math.abs(lift.getCurrentFloor() - sourceFloor);
                
                // Select the lift with the minimum distance to the source floor
                if (distance < minimumDistance) {
                    minimumDistance = distance;
                    selectedLift = lift;
                }
            }
        }

        if (selectedLift != null) {
            selectedLift.moveToFloor(destinationFloor); // Move lift to destination floor
            System.out.println(selectedLift.getName() + " is assigned.");
        } else {
            System.out.println("No available lift can serve the requested floors.");
        }
    }

    public void setLiftUnderMaintenance(String liftName) {
        for (Lift lift : lifts) {
            if (lift.getName().equals(liftName)) {
                lift.setUnderMaintenance(true);
                System.out.println(liftName + " is now under maintenance.");
                return;
            }
        }
        System.out.println("Lift not found.");
    }

    public void removeLiftFromMaintenance(String liftName) {
        for (Lift lift : lifts) {
            if (lift.getName().equals(liftName)) {
                lift.setUnderMaintenance(false);
                System.out.println(liftName + " is now available.");
                return;
            }
        }
        System.out.println("Lift not found.");
    }
}
