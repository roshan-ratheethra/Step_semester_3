public class MembershipAccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }
        switch (fieldModifier) {
            case "public":
                return "ALLOWED";
            case "protected":
            case "default":
                if ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            case "private":
                if ("SAME_CLASS".equals(accessorContext)) {
                    return "ALLOWED";
                }
                return "DENIED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        if (attempts == null) {
            return "";
        }
        int privAllow = 0, privDeny = 0;
        int defAllow = 0, defDeny = 0;
        int protAllow = 0, protDeny = 0;
        int pubAllow = 0, pubDeny = 0;

        for (int i = 0; i < attempts.length; i++) {
            if (attempts[i] == null || attempts[i].length < 2) {
                continue;
            }
            String mod = attempts[i][0];
            String ctx = attempts[i][1];
            boolean allowed = "ALLOWED".equals(classifyAccess(mod, ctx));

            if ("private".equals(mod)) {
                if (allowed) privAllow++; else privDeny++;
            } else if ("default".equals(mod)) {
                if (allowed) defAllow++; else defDeny++;
            } else if ("protected".equals(mod)) {
                if (allowed) protAllow++; else protDeny++;
            } else if ("public".equals(mod)) {
                if (allowed) pubAllow++; else pubDeny++;
            }
        }

        return "private: " + privAllow + " allowed / " + privDeny + " denied "
             + "default: " + defAllow + " allowed / " + defDeny + " denied "
             + "protected: " + protAllow + " allowed / " + protDeny + " denied "
             + "public: " + pubAllow + " allowed / " + pubDeny + " denied";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeByModifier(attempts));

        try {
            new LibraryMember("LB9", "BR1", 0, "Priya Nair");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LibraryMember validMember = new LibraryMember("LB94", "BR1", 0, "Priya Nair");
        System.out.println("Created member: " + validMember.getMembershipId());
    }
}

class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("membershipId must be at least 4 non-whitespace characters");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public double getFinesOwed() {
        return finesOwed;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        return MembershipAccessChecker.classifyAccess(fieldModifier, accessorContext);
    }

    public static String summarizeByModifier(String[][] attempts) {
        return MembershipAccessChecker.summarizeByModifier(attempts);
    }
}
