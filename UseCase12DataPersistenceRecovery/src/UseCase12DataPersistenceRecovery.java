import java.io.*;
        import java.util.*;

// Reservation Class (Serializable)
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    String id;
    String name;
    String roomType;

    public Reservation(String id, String name, String roomType) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Room: " + roomType;
    }
}

// Data Wrapper (to store full system state)
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    List<Reservation> bookings;
    Map<String, Integer> inventory;

    public SystemState(List<Reservation> bookings, Map<String, Integer> inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    // Save state
    public static void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("💾 Data saved successfully!");
        } catch (IOException e) {
            System.out.println("❌ Error saving data.");
        }
    }

    // Load state
    public static SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("📂 Data loaded successfully!");
            return (SystemState) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠ No previous data found. Starting fresh...");
            return null;
        }
    }
}

// MAIN CLASS
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Reservation> bookings;
        Map<String, Integer> inventory;

        // LOAD EXISTING DATA
        SystemState state = PersistenceService.load();

        if (state != null) {
            bookings = state.bookings;
            inventory = state.inventory;
        } else {
            bookings = new ArrayList<>();
            inventory = new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 2);
            inventory.put("Suite", 1);
        }

        while (true) {
            System.out.println("\n1. Book Room");
            System.out.println("2. View Bookings");
            System.out.println("3. View Inventory");
            System.out.println("4. Save & Exit");

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

                    if (!inventory.containsKey(type) || inventory.get(type) <= 0) {
                        System.out.println("❌ Room not available!");
                        break;
                    }

                    bookings.add(new Reservation(id, name, type));
                    inventory.put(type, inventory.get(type) - 1);

                    System.out.println("✅ Booking Successful!");
                    break;

                case 2:
                    System.out.println("\n--- Bookings ---");
                    for (Reservation r : bookings) {
                        System.out.println(r);
                    }
                    break;

                case 3:
                    System.out.println("\n--- Inventory ---");
                    System.out.println(inventory);
                    break;

                case 4:
                    // SAVE BEFORE EXIT
                    PersistenceService.save(new SystemState(bookings, inventory));
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}