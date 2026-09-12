public class RaceAnnouncer {

    public static class RaceEntry {
        private String bibNumber;
        private double entryFee;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
        }

        public String getBibNumber() {
            return bibNumber;
        }

        public double getEntryFee() {
            return entryFee;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber + " | Balance: " + entryFee;
        }
    }

    public static class RunnerEntry extends RaceEntry {
        private String category;

        public RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String announce() {
            return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getEntryFee();
        }
    }

    public static class RelayTeamEntry extends RaceEntry {
        private int teamSize;

        public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
            super(bibNumber, entryFee);
            this.teamSize = teamSize;
        }

        public int getTeamSize() {
            return teamSize;
        }

        @Override
        public String announce() {
            return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getEntryFee();
        }
    }

    public static String announceAll(RaceEntry[] entries) {
        if (entries == null || entries.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < entries.length; i++) {
            RaceEntry entry = entries[i];
            if (entry == null) continue;

            sb.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry rt = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(rt.getTeamSize()).append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 90.0, "Open 10K");
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300.0, 4);

        RaceEntry[] fleet = {runnerEntry, relayEntry};
        System.out.println(announceAll(fleet));

        RaceEntry plain = new RaceEntry("BIB5001", 50);
        try {
            RelayTeamEntry bad = (RelayTeamEntry) plain;
            System.out.println(bad);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
