import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HorseCustomiseGUI{
    private int horseNum = 0;
    //frames
    private JFrame frame = new JFrame();

    //labels
    private JLabel titleLabel = new JLabel("Customise your Horse");

    //panels
    private JPanel mainPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel optionsPanel = new JPanel();
    private JPanel statsPanel = new JPanel();


    //buttons
    private JButton nextButton = new JButton("Next");
    private JButton backButton = new JButton("Back");


    public void setUpGUI() {
        while (horseNum < 2 || horseNum > 5) {
            horseNum = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many horses would you like in your race? (2-5)", "Horse Num",
                    JOptionPane.QUESTION_MESSAGE));
        }
        createHorseOptions();
        RaceGUI raceGUI = new RaceGUI(horseNum);
        raceGUI.setUpGUI();
    }

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

    public void createHorseOptions() {
        ArrayList<Horse> horses = new ArrayList<>();
        String[] breedOptions = {"Arabian", "Quarter", "Thorough Bred"};
        Character[] breedChar = {'A', 'Q', 'T'};
        String[] colourOptions = {"Black", "Brown", "White"};

        for (int i = 1; i <= horseNum; i++) {
            String name = JOptionPane.showInputDialog(frame, "Name of horse " + i + ":", "Name your horse", JOptionPane.QUESTION_MESSAGE);
            if (name == null || name.trim().isEmpty()) continue;

            JComboBox<Character> horseSymbolsComboBox = new JComboBox<>(breedChar);
            JComboBox<String> breedsComboBox = new JComboBox<>(breedOptions);
            JComboBox<String> colorsComboBox = new JComboBox<>(colourOptions);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Select a horse symbol:"));
            panel.add(horseSymbolsComboBox);
            panel.add(new JLabel("Select a breed:"));
            panel.add(breedsComboBox);
            panel.add(new JLabel("Select a color:"));
            panel.add(colorsComboBox);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Choose Horse Options", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) continue;

            char selectedHorseSymbol = (char) horseSymbolsComboBox.getSelectedItem();
            String selectedBreed = breedsComboBox.getSelectedItem().toString();
            double confidence = confidenceDecider(selectedBreed);
            String colour = colourOptions[colorsComboBox.getSelectedIndex()];

            Horse horse;
            horses.add(horse = new Horse(selectedHorseSymbol, name, confidence));
            horse.setColour(colour);

        }
    }
}

