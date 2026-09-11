import java.util.HashSet;
import java.util.Set;

public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }

        String trimmedName = passengerName.trim();
        for (int i = 0; i < trimmedName.length(); i++) {
            char c = trimmedName.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                throw new IllegalArgumentException("Passenger name must contain only letters and spaces");
            }
        }

        this.passengerName = trimmedName;
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in: " + passengerName);
        } else {
            System.out.println("Already checked in: " + passengerName);
        }
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        Set<String> seen = new HashSet<>();

        if (rawBookings != null) {
            for (int i = 0; i < rawBookings.length; i++) {
                if (rawBookings[i] == null || rawBookings[i].length < 2) {
                    rejected++;
                    continue;
                }
                String name = rawBookings[i][0];
                String dest = rawBookings[i][1];

                try {
                    new BusTicket(name, dest);
                    String key = name.trim().toLowerCase() + "|" + dest.trim().toLowerCase();
                    if (seen.contains(key)) {
                        duplicates++;
                    } else {
                        seen.add(key);
                        valid++;
                    }
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(rawBookings);
    }
}
