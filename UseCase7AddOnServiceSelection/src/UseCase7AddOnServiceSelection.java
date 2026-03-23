import java.util.*;

// Class representing an Add-On Service
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Manager class to handle mapping between Reservation and Services
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> reservationServicesMap;

    public AddOnServiceManager() {
        reservationServicesMap = new HashMap<>();
    }

    // Add services to a reservation
    public void addService(String reservationId, AddOnService service) {
        reservationServicesMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    // Get all services for a reservation
    public List<AddOnService> getServices(String reservationId) {
        return reservationServicesMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total additional cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = getServices(reservationId);

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        System.out.println("Add-On Services for Reservation ID: " + reservationId);
        for (AddOnService service : services) {
            System.out.println("- " + service);
        }
    }
}

// Main class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample Reservation ID (from previous use case)
        System.out.print("Enter Reservation ID: ");
        String reservationId = scanner.nextLine();

        while (true) {
            System.out.println("\nSelect Add-On Service:");
            System.out.println("1. Breakfast (₹200)");
            System.out.println("2. Airport Pickup (₹500)");
            System.out.println("3. Extra Bed (₹300)");
            System.out.println("4. Done");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.addService(reservationId, new AddOnService("Breakfast", 200));
                    break;
                case 2:
                    manager.addService(reservationId, new AddOnService("Airport Pickup", 500));
                    break;
                case 3:
                    manager.addService(reservationId, new AddOnService("Extra Bed", 300));
                    break;
                case 4:
                    // Exit loop
                    System.out.println("\nFinal Summary:");
                    manager.displayServices(reservationId);

                    double total = manager.calculateTotalCost(reservationId);
                    System.out.println("Total Add-On Cost: ₹" + total);
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}