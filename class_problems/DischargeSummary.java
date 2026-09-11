public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (patientId == null || medicationCodes == null) {
            throw new IllegalArgumentException("patientId and medicationCodes cannot be null");
        }
        for (int i = 0; i < medicationCodes.length; i++) {
            if (medicationCodes[i] == null || !medicationCodes[i].matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("Invalid medication code: " + medicationCodes[i]);
            }
        }
        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (newCode == null || !newCode.matches("^MED-[A-Z]$")) {
            throw new IllegalArgumentException("Invalid replacement code: " + newCode);
        }
        String[] updated = medicationCodes.clone();
        updated[index] = newCode;
        return new DischargeSummary(this.patientId, updated);
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }

        public int getIcuDays() {
            return icuDays;
        }
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 critical-care | 0 routine";
        }

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {
            DischargeSummary d = summaries[i];
            if (d == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (d instanceof CriticalCareDischargeSummary) {
                criticalCare++;
            } else {
                routine++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + criticalCare + " critical-care | " + routine + " routine";
    }

    public static void main(String[] args) {
        try {
            new DischargeSummary("MT2026-0142", new String[]{"MED-A", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        DischargeSummary d = new DischargeSummary("MT2026-0142", new String[]{"MED-A", "MED-B"});
        String[] codes = d.getMedicationCodes();
        codes[0] = "TAMPERED";
        System.out.println(d.getMedicationCodes()[0]);

        DischargeSummary[] batch = new DischargeSummary[]{
            new CriticalCareDischargeSummary("MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary("MT002", new String[]{"MED-Y"})
        };

        System.out.println(processNightlyBatch(batch));
    }
}
