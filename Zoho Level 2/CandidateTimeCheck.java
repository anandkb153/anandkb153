package Zoho_Level_2;

import java.util.Scanner;

public class CandidateTimeCheck {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        System.out.println("Enter time (e.g. 10:00 AM): ");
        String inputTime = scanner.nextLine();

        // Manually split and validate the time string
        String[] timeComponents = manualSplitTime(inputTime);

        if (timeComponents == null) {
            System.out.println("Invalid input");
            return;
        }

        // Extract hours, minutes, and period
        String hoursStr = timeComponents[0];
        String minutesStr = timeComponents[1];
        String period = timeComponents[2];

        // Convert hours and minutes to integers
        int inputHours = toInt(hoursStr);
        int inputMinutes = toInt(minutesStr);

        // Validate if hours are in 12-hour format
        if (inputHours < 1 || inputHours > 12 || inputMinutes >=60 ) {
            System.out.println("Invalid input");
            return;
        }

        boolean isPM = period.equals("PM");

        // Convert to 24-hour format
        if (isPM && inputHours != 12) {
            inputHours += 12;
        } else if (period.equals("AM") && inputHours == 12) {
            inputHours = 0;
        }

        // Set the interview time (10:00 AM)
        int interviewHours = 10;
        int interviewMinutes = 0;

        // Compare times and print the result
        if (inputHours < interviewHours || (inputHours == interviewHours && inputMinutes <= interviewMinutes)) {
            System.out.println("The candidate is on time");
        } else {
            // Calculate how late the candidate is
            int delayHours = inputHours - interviewHours;
            int delayMinutes = inputMinutes - interviewMinutes;

            System.out.println("The candidate is late by " + delayHours + " hours and " + delayMinutes + " minutes.");
        }
    }

    // Manually split the time input into hours, minutes, and AM/PM
    public static String[] manualSplitTime(String input) {
        String[] result = new String[3];
        char[] timeArr = input.toCharArray();

        // Check if the length is 8 characters for valid HH:MM AM/PM
        if (input.length() != 8) {
            return null;
        }

        // Manually extract hours
        String hours = "";
        for (int i = 0; i < 2; i++) {
            if (timeArr[i] < '0' || timeArr[i] > '9') {
                return null; // Invalid if not digits
            }
            hours += timeArr[i];
        }
        result[0] = hours;

        // Check for the colon ':'
        if (timeArr[2] != ':') {
            return null;
        }

        // Manually extract minutes
        String minutes = "";
        for (int i = 3; i < 5; i++) {
            if (timeArr[i] < '0' || timeArr[i] > '9') {
                return null; // Invalid if not digits
            }
            minutes += timeArr[i];
        }
        result[1] = minutes;

        // Manually extract AM/PM
        String ampm = "";
        for (int i = 6; i < 8; i++) {
            ampm += timeArr[i];
        }

        if (!ampm.equals("AM") && !ampm.equals("PM")) {
            return null; // Invalid if it's not AM or PM
        }
        result[2] = ampm;

        // Return the result array containing hours, minutes, and AM/PM
        return result;
    }

    // Convert string to integer without using Integer.parseInt
    public static int toInt(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = result * 10 + (str.charAt(i) - '0');
        }
        return result;
    }

    // Check if a string contains only digits
    public static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
