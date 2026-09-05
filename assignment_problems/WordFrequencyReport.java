import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordFrequencyReport {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter feedback paragraph: ");
        String feedback = scanner.nextLine();
        feedback = feedback.replace("\"", "");
        
        printFilteredWordFrequency(feedback);
        scanner.close();
    }

    public static void printFilteredWordFrequency(String feedback) {
        String text = feedback.toLowerCase();
        text = text.replace(".", "");
        text = text.replace(",", "");
        
        String[] words = text.split("\\s+");
        
        HashMap<String, Integer> counts = new HashMap<>();
        
        for (String w : words) {
            if (w.equals("the") || w.equals("was") || w.equals("and") || 
                w.equals("a") || w.equals("is") || w.equals("of") || w.equals("in")) {
                continue;
            }
            
            if (w.length() > 0) {
                if (counts.containsKey(w)) {
                    counts.put(w, counts.get(w) + 1);
                } else {
                    counts.put(w, 1);
                }
            }
        }
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}