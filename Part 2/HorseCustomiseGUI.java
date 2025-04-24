import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HorseCustomiseGUI implements ActionListener {
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

    //combo boxes
    private JComboBox breedsComboBox;
    private JComboBox horseSymbolsComboBox;



    public void setUpGUI() {
        while (horseNum < 2 || horseNum > 5) {
            horseNum = Integer.parseInt(JOptionPane.showInputDialog(frame, "How many horses would you like in your race? (2-5)", "Horse Num",
                    JOptionPane.QUESTION_MESSAGE));
        }
        createHorseOptions();

        setUpButton();
        setUpPanel();
        setUpFrame();
    }

    public double confidenceDecider(String breed) {
        double confidence = 0.0;
        if (breed.equals("Arabian")) {
            confidence = 0.5;
        }
        else if (breed.equals("Quarter")) {
            confidence = 0.4;
        }
        else if (breed.equals("Thorough Bred")) {
            confidence = 0.7;
        }
        return confidence;
    }

    public void createHorseOptions() {
        ArrayList<Horse> horses = new ArrayList<>();
        String[] breedOptions = {"Arabian", "Quarter", "Thorough Bred"};
        Character[] breedChar = {'A', 'Q', 'T'};

        for (int i = 1; i <= horseNum; i++) {
            String name = JOptionPane.showInputDialog(frame, "Name of horse " + i + ":", "Name your horse", JOptionPane.QUESTION_MESSAGE);
            if (name == null || name.trim().isEmpty()) continue;

            JComboBox<Character> horseSymbolsComboBox = new JComboBox<>(breedChar);
            JComboBox<String> breedsComboBox = new JComboBox<>(breedOptions);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Select a horse symbol:"));
            panel.add(horseSymbolsComboBox);
            panel.add(new JLabel("Select a breed:"));
            panel.add(breedsComboBox);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Choose Horse Options", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) continue;

            char selectedHorseSymbol = (char) horseSymbolsComboBox.getSelectedItem();
            String selectedBreed = breedsComboBox.getSelectedItem().toString();
            double confidence = confidenceDecider(selectedBreed);

            horses.add(new Horse(selectedHorseSymbol, name, confidence));
        }
    }


    public void setUpFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Customise your Horse");
        frame.setSize(1080, 1080);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(optionsPanel, BorderLayout.WEST);
        frame.add(statsPanel, BorderLayout.SOUTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void setUpPanel() {
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(nextButton);


        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(backButton);
        optionsPanel.add(Box.createVerticalStrut(50));

        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.setBackground(Color.WHITE);
    }

    public void setUpButton() {
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(this);

        nextButton.setBackground(Color.WHITE);
        nextButton.addActionListener(this);
    }

    public void breedButtonActionPerformed() {
        System.out.println("breedButtonActionPerformed");

    }

    public void backButtonActionPerformed() {
        System.out.println("backButtonActionPerformed");
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        frame.setVisible(false);
        mainMenuGUI.setUpGUI();
    }

    public void nextButtonActionPerformed() {
        frame.setVisible(false);
        System.out.println("nextButtonActionPerformed");
        TrackGUI trackGUI = new TrackGUI();
        trackGUI.setUpGUI();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            backButtonActionPerformed();
        }
        else if (e.getSource() == nextButton) {
            nextButtonActionPerformed();
        }


    }
}
