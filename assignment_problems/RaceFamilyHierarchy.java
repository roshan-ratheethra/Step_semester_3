public class RaceFamilyHierarchy {

    public static class RaceEntry {
        private String bibNumber;
        private double entryFee;
        private double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;
        }

        public void pay(double amount) {
            if (amount > 0) {
                this.amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return Math.max(0.0, entryFee - amountPaid);
        }

        public String getBibNumber() {
            return bibNumber;
        }

        public String announce() {
            return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
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
            return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
        }
    }

    public static class EliteRunnerEntry extends RunnerEntry {
        private double sponsorBonus;

        public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }

        public double getSponsorBonus() {
            return sponsorBonus;
        }

        @Override
        public String announce() {
            return "Elite Runner | Bib: " + getBibNumber() + " | Category: " + getCategory() + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
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
            return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
        }
    }

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Single-inheritance child";
        } else if (entry != null) {
            return "Root base entry";
        }
        return "Unknown";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        if (entries == null) {
            return 0.0;
        }
        double total = 0.0;
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                total += entries[i].getBalanceDue();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());

        System.out.println(classifyGeneration(eliteEntry));
        System.out.println(classifyGeneration(relayEntry));

        RaceEntry[] entries = {runnerEntry, eliteEntry, relayEntry};
        System.out.println(getTotalBalanceDue(entries));
    }
}
