public class PalindromeChecker {

    public static boolean isPalindromeIterative(String text) {
        if (text == null) return false;
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(text.charAt(left)) != Character.toLowerCase(text.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        if (text == null) return false;
        return checkRecursive(text.toLowerCase(), 0, text.length() - 1);
    }

    private static boolean checkRecursive(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return checkRecursive(s, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        if (text == null) return false;
        char[] chars = text.toLowerCase().toCharArray();
        int n = chars.length;
        char[] reversed = new char[n];
        for (int i = 0; i < n; i++) {
            reversed[i] = chars[n - 1 - i];
        }
        for (int i = 0; i < n; i++) {
            if (chars[i] != reversed[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] testWords = {"madam", "hello"};
        for (int i = 0; i < testWords.length; i++) {
            String word = testWords[i];
            boolean iter = isPalindromeIterative(word);
            boolean rec = isPalindromeRecursive(word);
            boolean arr = isPalindromeArrayReversal(word);

            String iterStr = iter ? "Palindrome" : "Not Palindrome";
            String recStr = rec ? "Palindrome" : "Not Palindrome";
            String arrStr = arr ? "Palindrome" : "Not Palindrome";

            System.out.println("Iterative: " + iterStr + " | Recursive: " + recStr + " | Array Reversal: " + arrStr);
        }
    }
}
