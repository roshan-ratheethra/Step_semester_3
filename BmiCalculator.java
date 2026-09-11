public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi <= 24.9) {
            return "Normal";
        } else if (bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        if (heights == null || weights == null) return;
        int n = Math.min(heights.length, weights.length);

        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < n; i++) {
            double h = heights[i];
            double w = weights[i];
            double bmi = w / (h * h);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %d | %.2f m | %.1f kg | %.2f | %s%n", (i + 1), h, w, bmi, status);
        }
    }

    public static void main(String[] args) {
        double[] heights = {1.75, 1.60, 1.80, 1.65, 1.70, 1.55, 1.85, 1.72, 1.68, 1.78};
        double[] weights = {70.0, 90.0, 68.0, 75.0, 82.0, 48.0, 95.0, 64.0, 58.0, 85.0};

        printWellnessReport(heights, weights);
    }
}
