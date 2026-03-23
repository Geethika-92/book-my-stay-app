import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Booking System (Thread-safe)
class BookingSystem {

    private Map<String, Integer> inventory = new HashMap<>();

    public BookingSystem() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    // Critical Section (synchronized)
    public synchronized void bookRoom(BookingRequest request) {

        System.out.println(Thread.currentThread().getName() +
                " trying to book " + request.roomType + " for " + request.guestName);

        int available = inventory.getOrDefault(request.roomType, 0);

        if (available > 0) {
            // simulate processing delay
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inventory.put(request.roomType, available - 1);

            System.out.println("✅ " + request.guestName +
                    " successfully booked " + request.roomType +
                    " | Remaining: " + (available - 1));
        } else {
            System.out.println("❌ " + request.guestName +
                    " failed to book " + request.roomType +
                    " (No rooms available)");
        }
    }

    public void showInventory() {
        System.out.println("\nFinal Inventory: " + inventory);
    }
}

// Thread Class
class BookingThread extends Thread {

    private BookingSystem system;
    private BookingRequest request;

    public BookingThread(BookingSystem system, BookingRequest request) {
        this.system = system;
        this.request = request;
    }

    @Override
    public void run() {
        system.bookRoom(request);
    }
}

// MAIN CLASS
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Simulating multiple users (threads)
        BookingThread t1 = new BookingThread(system, new BookingRequest("Geethika", "Single"));
        BookingThread t2 = new BookingThread(system, new BookingRequest("Ravi", "Single"));
        BookingThread t3 = new BookingThread(system, new BookingRequest("Anu", "Single"));
        BookingThread t4 = new BookingThread(system, new BookingRequest("Kiran", "Double"));
        BookingThread t5 = new BookingThread(system, new BookingRequest("Sneha", "Double"));

        // Start all threads (concurrent execution)
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Show final state
        system.showInventory();
    }
}
