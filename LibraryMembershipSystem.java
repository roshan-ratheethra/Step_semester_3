class BrokenLibraryMember {
    // Marking these fields static is wrong because static makes the field shared globally across all objects of the class.
    // If name is static, every member will incorrectly share the exact same name.
    // If memberId is static, every member will share the exact same ID, preventing unique identification.
    // If booksIssued is static, borrowing a book adds to one global count instead of the individual member's own count.
    static String name;
    static String memberId;
    static int booksIssued;
    
    public BrokenLibraryMember(String n, String id, int books) {
        name = n;
        memberId = id;
        booksIssued = books;
    }
}

class FixedLibraryMember {
    String name;
    String memberId;
    int booksIssued;
    
    static String libraryName = "City Library";
    static int memberCount = 0;
    
    public FixedLibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }
    
    public void printMemberCard() {
        System.out.println(name + " " + memberId);
    }
    
    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class LibraryMembershipSystem {
    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember m1 = new BrokenLibraryMember("Aditi", "LM1001", 0);
        BrokenLibraryMember m2 = new BrokenLibraryMember("Rohan", "LM1002", 0);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        
        System.out.println("\nFixed version:");
        FixedLibraryMember f1 = new FixedLibraryMember("Aditi", 0);
        FixedLibraryMember f2 = new FixedLibraryMember("Rohan", 0);
        f1.printMemberCard();
        f2.printMemberCard();
        FixedLibraryMember.printTotalMembers();
    }
}