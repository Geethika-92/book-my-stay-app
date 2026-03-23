/**
 * UseCase4RoomSearch
 *
 * Demonstrates room search and availability check for the Hotel Booking System.
 * Only rooms with availability > 0 are displayed. Inventory state is not modified.
 *
 * Version: 4.1
 * Author: Geethika
 */

// Abstract Room class
abstract class Room {
    protected String type;
    protected int beds;
    protected double size;   // in sq.m
    protected double price;  // per night in ₹

    public Room(String type, int beds, double size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public abstract void displayRoomDetails();
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 15.0, 1000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 25.0, 1800.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 40.0, 3500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

// Main class for Use Case 4
public class UseCase4RoomSearch {

    // Simulated inventory from UC3 (number of rooms available)
    static int availableSingleRooms = 3;
    static int availableDoubleRooms = 2;
    static int availableSuiteRooms = 0;

    public static void main(String[] args) {

        System.out.println("****************************************");
        System.out.println(" Hotel Booking System v4.1 - Room Search ");
        System.out.println("****************************************\n");

        // Search and display only available rooms
        if (availableSingleRooms > 0) {
            Room single = new SingleRoom();
            single.displayRoomDetails();
            System.out.println("Available: " + availableSingleRooms + "\n");
        }

        if (availableDoubleRooms > 0) {
            Room doubleR = new DoubleRoom();
            doubleR.displayRoomDetails();
            System.out.println("Available: " + availableDoubleRooms + "\n");
        }

        if (availableSuiteRooms > 0) {
            Room suite = new SuiteRoom();
            suite.displayRoomDetails();
            System.out.println("Available: " + availableSuiteRooms + "\n");
        }

        System.out.println("Search completed. Inventory state remains unchanged.");
    }
}