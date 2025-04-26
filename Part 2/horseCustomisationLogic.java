public class horseCustomisationLogic {

    public double confidenceDecider(String breed) {
        double confidence = 0.0;
        if (breed.equals("Arabian")) {
            confidence = 0.5;
        } else if (breed.equals("Quarter")) {
            confidence = 0.4;
        } else if (breed.equals("Thorough Bred")) {
            confidence = 0.7;
        }
        return confidence;
    }
}
