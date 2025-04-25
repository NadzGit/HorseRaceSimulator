public class Main {
    public static void main(String[] args) throws InterruptedException {
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.setUpGUI();
      
        Race newRace = new Race(20, 5);
        Horse horse1 = new Horse('Ә', "Pookie", 1.1);
        Horse horse2 = new Horse('Ѱ', "Wookie", 1.01);
        Horse horse3 = new Horse('Ѿ', "Woo", 0.01);

        newRace.addHorse(horse1, 1);
        newRace.addHorse(horse2, 2);
        newRace.addHorse(horse3, 3);
        newRace.startRace();
    }
}
