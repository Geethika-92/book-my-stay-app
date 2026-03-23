/**
 * UseCase6RoomAllocationService
 *
 * Demonstrates reservation confirmation and room allocation for the Hotel Booking System.
 * Processes queued booking requests in FIFO order, assigns unique room IDs, and updates inventory.
 *
 * Version: 6.1
 * Author: Geethika
 */

import java.util.*;

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
}

// Main class for Use Case 6
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("****************************************");
        System.out.println(" Hotel Booking System v6.1 - Room Allocation ");
        System.out.println("****************************************\n");

        // Simulated booking request queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();
        bookingQueue.add(new Reservation("Alice", "Single Room"));
        bookingQueue.add(new Reservation("Bob", "Double Room"));
        bookingQueue.add(new Reservation("Charlie", "Suite Room"));
        bookingQueue.add(new Reservation("David", "Single Room"));

        // Inventory (available rooms per type)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        // Allocated room IDs per room type
        Map<String, Set<String>> allocatedRooms = new HashMap<>();
        allocatedRooms.put("Single Room", new HashSet<>());
        allocatedRooms.put("Double Room", new HashSet<>());
        allocatedRooms.put("Suite Room", new HashSet<>());

        // Process bookings
        while (!bookingQueue.isEmpty()) {
            Reservation request = bookingQueue.poll(); // dequeue
            String type = request.getRoomType();
            int available = inventory.getOrDefault(type, 0);

            if (available > 0) {
                // Generate unique room ID
                String roomId = type.substring(0, 1) + (allocatedRooms.get(type).size() + 1);

                // Ensure uniqueness (Set will enforce)
                allocatedRooms.get(type).add(roomId);

                // Update inventory
                inventory.put(type, available - 1);

                // Confirm reservation
                System.out.println("Reservation confirmed for " + request.getGuestName() +
                        " | Room Type: " + type +
                        " | Room ID: " + roomId);
            } else {
                System.out.println("Sorry " + request.getGuestName() +
                        ", no " + type + " available for booking.");
            }
        }

        System.out.println("\nFinal Inventory Status:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " | Remaining: " + entry.getValue());
        }
    }
}