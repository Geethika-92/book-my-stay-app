/**
 * UseCase2RoomInitialization
 *
 * Demonstrates basic room types and their static availability
 * for the Hotel Booking System using object-oriented concepts:
 * abstraction, inheritance, and polymorphism.
 *
 * Version: 2.1
 * Author: Geethika
 */

// Abstract class representing a generic Room
abstract class Room {
    protected String type;
    protected int beds;
    protected double size;  // in sq.m
    protected double price; // per night in ₹

    public Room(String type, int beds, double size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Abstract method to display room details
    public abstract void displayRoomDetails();
}

// Concrete class for Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 15.0, 1000.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

// Concrete class for Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 25.0, 1800.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

// Concrete class for Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 40.0, 3500.0);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println(type + " | Beds: " + beds + " | Size: " + size + " sq.m | Price: ₹" + price);
    }
}

// Main class for Use Case 2
public class UseCase2RoomInitialization {

    // Static availability for each room type
    static int availableSingleRooms = 5;
    static int availableDoubleRooms = 3;
    static int availableSuiteRooms = 2;

    public static void main(String[] args) {

        System.out.println("****************************************");
        System.out.println(" Hotel Booking System v2.1 - Room Info ");
        System.out.println("****************************************\n");

        // Create room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details and availability
        single.displayRoomDetails();
        System.out.println("Available: " + availableSingleRooms + "\n");

        doubleR.displayRoomDetails();
        System.out.println("Available: " + availableDoubleRooms + "\n");

        suite.displayRoomDetails();
        System.out.println("Available: " + availableSuiteRooms + "\n");

        System.out.println("Application has displayed all room information successfully.");
    }
}