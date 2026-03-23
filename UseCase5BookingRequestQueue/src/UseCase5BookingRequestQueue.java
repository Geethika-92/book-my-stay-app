/**
 * UseCase5BookingRequestQueue
 *
 * Demonstrates first-come-first-served booking requests for the Hotel Booking System.
 * Booking requests are stored in a queue preserving arrival order.
 *
 * Version: 5.1
 * Author: Geethika
 */

import java.util.LinkedList;
import java.util.Queue;

// Reservation class representing a guest booking request
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Main class for Use Case 5
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("****************************************");
        System.out.println(" Hotel Booking System v5.1 - Booking Request Queue ");
        System.out.println("****************************************\n");

        // Queue to store booking requests (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        // Simulate booking requests (arrival order)
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("David", "Single Room"));

        System.out.println("Booking requests have been received in order:\n");

        // Display queued requests
        for (Reservation r : bookingQueue) {
            r.displayReservation();
        }

        System.out.println("\nNote: No allocation or inventory update occurs at this stage.");
    }
}