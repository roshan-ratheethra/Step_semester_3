public class AccessRuleEngine {

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

    public static String summarizeBatch(String[][] attempts) {
        if (attempts == null) {
            return "Allowed: 0 | Denied: 0";
        }
        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {
            if (attempts[i] == null || attempts[i].length < 2) continue;
            String res = classifyAccess(attempts[i][0], attempts[i]);
            if ("ALLOWED".equals(res)) {
                allowed++;
            } else {
                denied++;
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess("private", "SAME_CLASS"));
        System.out.println(classifyAccess("default", "DIFFERENT_PACKAGE"));

        String[][] batch = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(summarizeBatch(batch));

        try {
            new PatientRecord("MT9", "W3", 98.2, "MediTrack Central");
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PatientRecord p = new PatientRecord("MT94", "W3", 98.2, "MediTrack Central");
        System.out.println("Created patient: " + p.getPatientId());
    }
}

class PatientRecord {
    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
        if (patientId == null || patientId.trim().length() < 4) {
            throw new IllegalArgumentException("Patient ID must be at least 4 characters");
        }
        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public double getVitalsScore() {
        return vitalsScore;
    }

    public String getFacilityName() {
        return facilityName;
    }
}
