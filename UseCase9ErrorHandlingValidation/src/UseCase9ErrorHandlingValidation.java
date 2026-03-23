import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation Class (non-public → no conflicts)
class Reservation {
    private String id;
    private String name;
    private String roomType;
    private double price;

    public Reservation(String id, String name, String roomType, double price) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Room: " + roomType + ", Price: ₹" + price;
    }
}

// Validator Class
class BookingValidator {

    private static final List<String> validRooms = Arrays.asList("Single", "Double", "Suite");

    public static void validate(String id, String name, String roomType, double price, int availableRooms)
            throws InvalidBookingException {

        // Validate ID
        if (id == null || id.trim().isEmpty()) {
            throw new InvalidBookingException("Reservation ID cannot be empty.");
        }

        // Validate Name
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate Room Type
        if (!validRooms.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type! Choose Single/Double/Suite.");
        }

        // Validate Price
        if (price <= 0) {
            throw new InvalidBookingException("Price must be greater than 0.");
        }

        // Validate Availability
        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available.");
        }
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    // Simulated inventory
    static int availableRooms = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n--- Booking System ---");
                System.out.println("Available Rooms: " + availableRooms);

                System.out.print("Enter Reservation ID: ");
                String id = sc.nextLine();

                System.out.print("Enter Guest Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Room Type (Single/Double/Suite): ");
                String room = sc.nextLine();

                System.out.print("Enter Price: ");
                double price = sc.nextDouble();
                sc.nextLine();

                // VALIDATION (Fail-Fast)
                BookingValidator.validate(id, name, room, price, availableRooms);

                // If valid → create booking
                Reservation r = new Reservation(id, name, room, price);

                // Update inventory
                availableRooms--;

                System.out.println("✅ Booking Successful!");
                System.out.println(r);

            } catch (InvalidBookingException e) {
                // Custom error handling
                System.out.println("❌ Booking Failed: " + e.getMessage());

            } catch (Exception e) {
                // General error (wrong input type etc.)
                System.out.println("❌ Invalid input! Please try again.");
                sc.nextLine(); // clear buffer
            }

            // Continue system safely
            System.out.println("\nDo you want to continue? (yes/no)");
            String choice = sc.nextLine();

            if (!choice.equalsIgnoreCase("yes")) {
                System.out.println("Exiting system...");
                break;
            }
        }
    }
}