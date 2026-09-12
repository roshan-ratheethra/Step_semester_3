public class TicketAnnouncer {

    public static class EventTicket {
        private double basePrice;

        public EventTicket(double basePrice) {
            this.basePrice = basePrice;
        }

        public double getBasePrice() {
            return basePrice;
        }

        public String printTicket() {
            return "Standard Balance: " + basePrice;
        }
    }

    public static class WorkshopTicket extends EventTicket {
        private String track;

        public WorkshopTicket(double basePrice, String track) {
            super(basePrice);
            this.track = track;
        }

        public String getTrack() {
            return track;
        }

        @Override
        public String printTicket() {
            return "Workshop | Track: " + track + " | Balance: " + getBasePrice();
        }
    }

    public static String batchPrint(EventTicket[] tickets) {
        if (tickets == null || tickets.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tickets.length; i++) {
            EventTicket ticket = tickets[i];
            if (ticket == null) continue;

            if (sb.length() > 0) {
                sb.append(" | ");
            }

            sb.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket wt = (WorkshopTicket) ticket;
                sb.append(" [Track via downcast: ").append(wt.getTrack()).append("]");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        EventTicket[] batch = {
            new EventTicket(500),
            new WorkshopTicket(1200, "AI/ML")
        };

        System.out.println(batchPrint(batch));
    }
}
