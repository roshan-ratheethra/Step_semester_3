import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    public static char findFirstNonRepeatingChar(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (counts.get(c) == 1) {
                return c;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] samples = {"swiss", "aabbcc"};
        for (int i = 0; i < samples.length; i++) {
            char ch = findFirstNonRepeatingChar(samples[i]);
            if (ch != 0) {
                System.out.println("First Non-Repeating Character: '" + ch + "'");
            } else {
                System.out.println("No Non-Repeating Character Found");
            }
        }
    }
}
