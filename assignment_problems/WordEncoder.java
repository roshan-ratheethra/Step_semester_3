import java.util.Scanner;

public class WordEncoder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence to reverse: ");
        String sentence = scanner.nextLine();
        sentence = sentence.replace("\"", "");
        
        System.out.println(reverseEachWord(sentence));
        scanner.close();
    }

    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";
        
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            result += sb.reverse().toString();
            
            if (i < words.length - 1) {
                result += " ";
            }
        }
        
        return result;
    }
}