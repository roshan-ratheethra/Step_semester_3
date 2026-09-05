import java.util.Scanner;

public class movieReview {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the movie review: ");
        String review = scanner.nextLine();
        String[] word = new String[review.length()];

        if(review.isEmpty()){
            System.out.println("Please write a review before pressing enter");

        }else{
            review = review + " ";
            int i =0;

            int s = 0,m = 0,l = 0;

            while(review.indexOf(" ") != -1){

                word[i] = review.substring(0, review.indexOf(" "));

                review = review.substring(review.indexOf(" ") + 1);


                if(word[i].length() < 5 && word[i].length() > 0){
                s++;
                    }
                else if(word[i].length()>4 && word[i].length()<9){
                m++;
                    }
                else if(word[i].length() > 8){
                l++;
                    }
            }

        System.out.println("Short: " + s + " | Medium: " + m + " | Long: " + l);
    }

    }
}
