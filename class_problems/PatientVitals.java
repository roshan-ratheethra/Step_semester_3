import java.util.ArrayList;
import java.util.List;

public class PatientVitals {
    private List<Double> readings;

    public PatientVitals(double[] initialReadings) {
        this.readings = new ArrayList<>();
        if (initialReadings != null) {
            for (int i = 0; i < initialReadings.length; i++) {
                recordReading(initialReadings[i]);
            }
        }
    }

    public void recordReading(double reading) {
        if (reading > 0 && reading <= 45.0) {
            readings.add(reading);
        }
    }

    public double getAverage() {
        if (readings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < readings.size(); i++) {
            sum += readings.get(i);
        }
        return sum / readings.size();
    }

    public double[] getAllReadings() {
        double[] copy = new double[readings.size()];
        for (int i = 0; i < readings.size(); i++) {
            copy[i] = readings.get(i);
        }
        return copy;
    }

    public static void main(String[] args) {
        PatientVitals v = new PatientVitals(new double[]{36.5, -2, 37.1});
        double[] r = v.getAllReadings();
        System.out.print("[");
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i] + (i < r.length - 1 ? ", " : ""));
        }
        System.out.println("]");

        double[] copy = v.getAllReadings();
        copy[0] = 999;
        System.out.println(v.getAllReadings()[0]);
    }
}
