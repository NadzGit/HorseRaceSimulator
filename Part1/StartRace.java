public class StartRace {
    public static void main(String[] args) throws InterruptedException {
        // Call startRace method to start the race
        startRace();
    }

    public static void startRace() throws InterruptedException {
        // Create a race instance
        Race newRace = new Race(20, 5);

        // Create horses
        Horse horse1 = new Horse('Ә', "Pookie", 0.2);
        Horse horse2 = new Horse('Ѱ', "Wookie", 0.6);
        Horse horse3 = new Horse('Ѿ', "Woo", 0.99999);

        // Add horses to the race
        newRace.addHorse(horse1, 1);
        newRace.addHorse(horse2, 2);
        newRace.addHorse(horse3, 3);

        // Start the race
        newRace.startRace();
    }
}