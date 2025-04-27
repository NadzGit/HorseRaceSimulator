import javax.swing.*;
import java.awt.*;

public class HorseCustomiseGUI {
    private int horseNum = 0;
    private final horseCustomisationLogic customisationLogic = new horseCustomisationLogic();
    private final JFrame frame = new JFrame();

    public int horseNumSetUp() {
        while (horseNum < 2 || horseNum > 5) {
            try {
                horseNum = Integer.parseInt(JOptionPane.showInputDialog(frame,
                        "How many horses would you like in your race? (2-5)",
                        "Horse Number",
                        JOptionPane.QUESTION_MESSAGE));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number between 2 and 5.");
            }
        }
        return horseNum;
    }

    public HorsePart2[] createHorseOptions(int horseNum) {
        HorsePart2[] horses = new HorsePart2[horseNum];
        String[] breedOptions = {"Arabian", "Quarter", "Thorough Bred"};
        Character[] breedChar = {'♔', 'Ч', '߷'};
        String[] ColorOptions = {"Brown", "White", "Black"};

        for (int i = 0; i < horseNum; i++) { // Start from 0 not 1
            String name = JOptionPane.showInputDialog(frame, "Name of horse " + (i + 1) + ":",
                    "Name your horse", JOptionPane.QUESTION_MESSAGE);

            if (name == null || name.trim().isEmpty()) {
                i--;
                continue;
            }

            JComboBox<Character> horseSymbolsComboBox = new JComboBox<>(breedChar);
            JComboBox<String> breedsComboBox = new JComboBox<>(breedOptions);
            JComboBox<String> colorsComboBox = new JComboBox<>(ColorOptions);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Select a horse symbol:"));
            panel.add(horseSymbolsComboBox);
            panel.add(new JLabel("Select a breed (see how this impacts performance in the Horses tab of the Main Menu):"));
            panel.add(breedsComboBox);
            panel.add(new JLabel("Select a color:"));
            panel.add(colorsComboBox);

            int result = JOptionPane.showConfirmDialog(frame, panel,
                    "Choose Horse Options", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                i--;
                continue;
            }

            char selectedHorseSymbol = (char) horseSymbolsComboBox.getSelectedItem();
            String selectedBreed = breedsComboBox.getSelectedItem().toString();
            String selectedColor = colorsComboBox.getSelectedItem().toString();
            double confidence = customisationLogic.confidenceDecider(selectedBreed);

            horses[i] = new HorsePart2(selectedHorseSymbol, name, confidence);
            horses[i].setColour(selectedColor);
            horses[i].setBreed(selectedBreed);
            horses[i].setFall();
        }

        return horses;
    }
}
