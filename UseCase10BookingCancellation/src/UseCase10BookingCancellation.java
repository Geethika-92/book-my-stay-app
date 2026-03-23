import java.util.*;

// Reservation Class (non-public)
class Reservation {
    String id;
    String name;
    String roomType;
    String roomId;

    public Reservation(String id, String name, String roomType, String roomId) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name +
                ", RoomType: " + roomType +
                ", RoomID: " + roomId;
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> bookings = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService() {
        // Initial inventory
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    // Booking (for demo)
    public void bookRoom(String id, String name, String type) {

        if (!inventory.containsKey(type)) {
            System.out.println("❌ Invalid room type.");
            return;
        }

        if (inventory.get(type) <= 0) {
            System.out.println("❌ No rooms available.");
            return;
        }

        String roomId = type.substring(0,1).toUpperCase() + (inventory.get(type));

        Reservation r = new Reservation(id, name, type, roomId);
        bookings.put(id, r);

        // decrease inventory
        inventory.put(type, inventory.get(type) - 1);

        System.out.println("✅ Booking Confirmed: " + r);
    }

    // Cancellation Logic
    public void cancelBooking(String id) {

        // Validate existence
        if (!bookings.containsKey(id)) {
            System.out.println("❌ Booking not found.");
            return;
        }

        Reservation r = bookings.get(id);

        // Push to stack (rollback tracking)
        rollbackStack.push(r.roomId);

        // Restore inventory
        inventory.put(r.roomType, inventory.get(r.roomType) + 1);

        // Remove booking
        bookings.remove(id);

        System.out.println("✅ Booking Cancelled: " + id);
        System.out.println("↩ Room Released: " + r.roomId);
    }

    public void showInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }

    public void showRollbackStack() {
        System.out.println("\n--- Rollback Stack (LIFO) ---");
        System.out.println(rollbackStack);
    }
}

// MAIN CLASS
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CancellationService service = new CancellationService();

        while (true) {
            System.out.println("\n1. Book Room");
            System.out.println("2. Cancel Booking");
            System.out.println("3. Show Inventory");
            System.out.println("4. Show Rollback Stack");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room Type (Single/Double/Suite): ");
                    String type = sc.nextLine();

                    service.bookRoom(id, name, type);
                    break;

                case 2:
                    System.out.print("Enter Reservation ID to cancel: ");
                    String cancelId = sc.nextLine();

                    service.cancelBooking(cancelId);
                    break;

                case 3:
                    service.showInventory();
                    break;

                case 4:
                    service.showRollbackStack();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}