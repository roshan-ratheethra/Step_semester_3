public class TicketRegistration {

    public static class EventTicket {
        private String attendeeId;
        private double basePrice;
        private double amountPaid;

        public EventTicket(String attendeeId, double basePrice) {
            if (attendeeId == null || attendeeId.trim().length() < 4) {
                throw new IllegalArgumentException("attendeeId must be at least 4 characters");
            }
            if (basePrice < 0) {
                throw new IllegalArgumentException("basePrice cannot be negative");
            }
            this.attendeeId = attendeeId.trim();
            this.basePrice = basePrice;
            this.amountPaid = 0.0;
        }

        public void pay(double amount) {
            if (amount > 0) {
                this.amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return Math.max(0.0, basePrice - amountPaid);
        }

        public String getAttendeeId() {
            return attendeeId;
        }

        public double getBasePrice() {
            return basePrice;
        }
    }

    public static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(String attendeeId, double basePrice, String track) {
            super(attendeeId, basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        if (attendeeIds == null) {
            return "Registered: 0 | Rejected: 0";
        }

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < attendeeIds.length; i++) {
            try {
                new EventTicket(attendeeIds[i], basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new EventTicket("ST1", 500);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println(w.getBalanceDue());

        String[] batch = {"STU1", " ", "ST1", "STU2", "STU3"};
        System.out.println(registerBatch(batch, 500));
    }
}
