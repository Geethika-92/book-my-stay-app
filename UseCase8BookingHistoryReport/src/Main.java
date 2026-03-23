import java.util.*;

// Reservation Class (NOT public → so no duplicate issue)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double basePrice;

    public Reservation(String reservationId, String guestName, String roomType, double basePrice) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "ID: " + reservationId +
                ", Name: " + guestName +
                ", Room: " + roomType +
                ", Price: ₹" + basePrice;
    }
}

// Booking History Class
class BookingHistory {
    private List<Reservation> historyList = new ArrayList<>();

    public void addReservation(Reservation r) {
        historyList.add(r);
    }

    public List<Reservation> getAllReservations() {
        return historyList;
    }
}

// Report Service Class
class BookingReportService {

    public void showAllBookings(List<Reservation> list) {
        if (list.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\n--- Booking History ---");
        for (Reservation r : list) {
            System.out.println(r);
        }
    }

    public void generateSummary(List<Reservation> list) {
        int total = list.size();
        double revenue = 0;

        for (Reservation r : list) {
            revenue += r.getBasePrice();
        }

        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Bookings: " + total);
        System.out.println("Total Revenue: ₹" + revenue);
    }
}

// ONLY ONE PUBLIC CLASS
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        while (true) {
            System.out.println("\n1. Add Booking");
            System.out.println("2. View History");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room: ");
                    String room = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    Reservation r = new Reservation(id, name, room, price);
                    history.addReservation(r);

                    System.out.println("Booking Stored!");
                    break;

                case 2:
                    report.showAllBookings(history.getAllReservations());
                    break;

                case 3:
                    report.generateSummary(history.getAllReservations());
                    break;

                case 4:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}