public class TicketHierarchy {

    public static class EventTicket {
        private String attendeeId;
        private double basePrice;
        private double amountPaid;

        public EventTicket(String attendeeId, double basePrice) {
            this.attendeeId = attendeeId;
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

        public String printTicket() {
            return "Standard Event Ticket | Balance Due: " + getBalanceDue();
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

        @Override
        public String printTicket() {
            return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
        }
    }

    public static class PremiumWorkshopTicket extends WorkshopTicket {
        private double kitFee;

        public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
            super(attendeeId, basePrice, track);
            this.kitFee = kitFee;
        }

        public double getKitFee() {
            return kitFee;
        }

        @Override
        public String printTicket() {
            return "Premium Workshop Ticket | Track: " + getTrack() + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue();
        }
    }

    public static class HackathonTicket extends EventTicket {
        private String teamName;

        public HackathonTicket(String attendeeId, double basePrice, String teamName) {
            super(attendeeId, basePrice);
            this.teamName = teamName;
        }

        public String getTeamName() {
            return teamName;
        }

        @Override
        public String printTicket() {
            return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue();
        }
    }

    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Single-inheritance child";
        } else if (ticket != null) {
            return "Root base ticket";
        }
        return "Unknown";
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        if (tickets == null) {
            return 0.0;
        }
        double total = 0.0;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null) {
                total += tickets[i].getBalanceDue();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        EventTicket t1 = new EventTicket("STU1", 500);
        WorkshopTicket t2 = new WorkshopTicket("STU2", 1200, "AI/ML");
        PremiumWorkshopTicket t3 = new PremiumWorkshopTicket("STU3", 2000, "Cloud Native", 300);
        HackathonTicket t4 = new HackathonTicket("STU4", 800, "Byte Force");

        System.out.println(t1.printTicket());
        System.out.println(t2.printTicket());
        System.out.println(t3.printTicket());
        System.out.println(t4.printTicket());

        System.out.println(classifyGeneration(t3));
        System.out.println(classifyGeneration(t4));

        EventTicket[] allTickets = {t1, t2, t3, t4};
        System.out.println(getTotalBalanceDue(allTickets));
    }
}
