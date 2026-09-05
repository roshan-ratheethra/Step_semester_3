public class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;
    
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }
    
    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }
        return 0;
    }
    
    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }
    
    // totalFineCollected is static because it calculates a sum across an entire collection of objects, 
    // rather than depending on the specific state of a single, individual BookIssue object. 
    // fineAmount is not static because it relies on the specific daysOverdue of one individual book.
    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (int i = 0; i < issues.length; i++) {
            total += issues[i].fineAmount();
        }
        return total;
    }
    
    public static void main(String[] args) {
        BookIssue[] issues = new BookIssue[5];
        issues[0] = new BookIssue("Clean Code", "Alice", 18);
        issues[1] = new BookIssue("Effective Java", "Bob", 5);
        issues[2] = new BookIssue("Refactoring", "Charlie", 0);
        issues[3] = new BookIssue("DSA Handbook", "David", 21);
        issues[4] = new BookIssue("Design Patterns", "Eve", 9);
        
        for (int i = 0; i < issues.length; i++) {
            String status = issues[i].isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issues[i].title + " " + issues[i].daysOverdue + " days - " + status);
        }
        
        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}