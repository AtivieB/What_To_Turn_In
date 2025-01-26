
public class ConversionUtility {

    /** Convert from feet to meters */
    public static double footToMeter(double foot) {
        return 0.305 * foot; // 1 foot is approximately 0.305 meters
    }

    /** Convert from meters to feet */
    public static double meterToFoot(double meter) {
        return 3.279 * meter; // 1 meter is approximately 3.279 feet
    }

    public static void main(String[] args) {
        // Example usage
        double feet = 10.0;
        double meters = 5.0;

        System.out.println(feet + " feet is " + footToMeter(feet) + " meters.");
        System.out.println(meters + " meters is " + meterToFoot(meters) + " feet.");
    }
}
