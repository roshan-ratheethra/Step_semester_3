public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("memberId and bookIds cannot be null");
        }
        for (int i = 0; i < bookIds.length; i++) {
            if (bookIds[i] == null || !bookIds[i].matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("Invalid bookId: " + bookIds[i]);
            }
        }
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (newId == null || !newId.matches("^BK-\\d{3}$")) {
            throw new IllegalArgumentException("Invalid replacement bookId: " + newId);
        }
        String[] updated = bookIds.clone();
        updated[index] = newId;
        return new LoanReceipt(this.memberId, updated);
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int refOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {
            LoanReceipt r = receipts[i];
            if (r == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (r instanceof ReferenceOnlyLoanReceipt) {
                refOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + refOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        try {
            new LoanReceipt("LIB-8841", new String[]{"BK-100", "bad"});
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]);

        LoanReceipt[] batch = new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(batch));
    }
}
