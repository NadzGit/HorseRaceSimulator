

    /**
     * Write a description of class Horse here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */

    public class HorsePart2 {
        // Fields of the class Horse
        private String NAME;
        private char initialSymbol; // Initial symbol representing horse (Unicode character)
        private char currentSymbol; // Current symbol, which can change during the race
        private int initialDistance; // Initial distance (always 0 for reset state)
        private int currentDistance; // Current distance travelled by the horse
        private boolean fall;
        private double confidence;
        private String colour;
        private String breed;

        // Constructor of class Horse
        public HorsePart2(char horseSymbol, String horseName, double horseConfidence) {
            this.fall = false;
            this.currentDistance = 0;
            this.initialDistance = 0;
            this.NAME = horseName;
            this.confidence = horseConfidence;
            this.initialSymbol = horseSymbol;
            this.currentSymbol = horseSymbol;

        }

        // Getter and Setter methods
        public String getNAME() {
            return this.NAME;
        }

        public void setName(String NAME) {
            this.NAME = NAME;
        }

        public String getColour() {
            return this.colour;
        }

        public void setColour(String colour) {
            this.colour = colour;
        }

        public double getConfidence() {
            return this.confidence;
        }

        public int getDistanceTravelled() {
            return this.currentDistance;
        }

        public String getName() {
            return this.NAME;
        }

        public char getSymbol() {
            return this.currentSymbol;
        }

        // Reset the horse to its initial state
        public void reset() {
            this.currentDistance = this.initialDistance;
            this.currentSymbol = this.initialSymbol;
            this.fall = false;
            this.confidence = 1.0; // Optional: Reset confidence to full
        }

        // Other methods
        public void fall() throws InterruptedException {
            this.fall = true;
        }

        public void setFall() {
            this.fall = false;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public void setConfidence(double newConfidence) {
            this.confidence = newConfidence;
        }

        public void setSymbol(char newSymbol) {
            this.currentSymbol = newSymbol;
        }

        public void goBackToStart() {
            this.currentDistance = 0;
        }

        public boolean hasFallen() {
            return this.fall;
        }

        public void moveForward() {
            this.currentDistance += 1;
        }

        // Method to simulate a horse falling during the race
        public void fallDuringRace() throws InterruptedException {
            this.fall = true;
        }
    }


