import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceGUI implements ActionListener {
    private int laneNum = 0;
    private int horseNum = 0;
    private String trackType = "";

    private JFrame mainFrame;
    private JPanel optionsPanel = new JPanel();
    private JPanel extraInfoPanel = new JPanel();
    private JPanel raceInfoPanel = new JPanel();
    private JPanel racePanel = new JPanel();
    private JPanel horseEditPanel = new JPanel();
    private JLabel trackConditionInfo;
    private JLabel hotWeatherInfo;
    private JLabel dryWeatherInfo;

    private JLabel saddleInfo;
    private JLabel goldCrownInfo;
    private JLabel coolHatInfo;
    private JLabel bridleInfo;


    private HorsePart2[] horses;
    private HorseMoveLogic logic;

    private final HorseCustomiseGUI horseCustomiseGUI = new HorseCustomiseGUI();
    private final JButton startRaceButton = new JButton("Start Race");
    private final JButton resetRaceButton = new JButton("Reset Race");

    private JComboBox<Integer> lanesComboBox;
    private JComboBox<String> trackTypeComboBox;
    private JComboBox<String> weatherConditionComboBox;
    JComboBox<String> equipmentComboBox;
    JComboBox<String> horseShoesComboBox;

    private JLabel raceAnalyticsLabel;
    private JLabel equipmentLabel = new JLabel("Equipment Type");
    private BettingLogic bettingLogic;

    private TrackConfigGUI trackConfigGUI;
    private int balance = 100; // Starting balance
    private int betAmount = 0;
    private String chosenHorseName = "";

    public void setUpGUI() {
        SwingUtilities.invokeLater(() -> {
            horseNum = horseCustomiseGUI.horseNumSetUp();
            horses = horseCustomiseGUI.createHorseOptions(horseNum);

            trackConfigGUI = new TrackConfigGUI(laneNum, horses);

            setUpButton();
            setUpInfoLabels();
            setUpComboBoxes();
            setUpPanels();
            setUpFrame();
        });
    }

    public void setUpInfoLabels() {
        // Weather condition labels
        trackConditionInfo = new JLabel("<html><b>Wet Weather Condition:</b><br>Track is slippery, horses need to adjust their pace.</html>");
        hotWeatherInfo = new JLabel("<html><b>Hot Weather Condition:</b><br>Track may become dry, affecting the horses' performance.</html>");
        dryWeatherInfo = new JLabel("<html><b>Dry Weather Condition:</b><br>Ideal track conditions, all horses perform optimally.</html>");

        // Equipment info labels
        saddleInfo = new JLabel("<html><b>Saddle Info:</b><br>Standard equipment for all horses to maintain balance.</html>");
        goldCrownInfo = new JLabel("<html><b>Gold Crown Info:</b><br>Special crown that boosts a horse's morale during the race.</html>");
        coolHatInfo = new JLabel("<html><b>Cool Hat Info:</b><br>Helps maintain the horse's focus and confidence in the race.</html>");
        bridleInfo = new JLabel("<html><b>Bridle Info:</b><br>Essential for controlling the horse during the race.</html>");
    }

    private void setUpButton() {
        startRaceButton.addActionListener(this);
        startRaceButton.setEnabled(false);

        // Set up Reset Race Button
        resetRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRace();  // Call the reset functionality
            }
        });
        resetRaceButton.setEnabled(true); // Enable the reset button after race setup
    }

    private void setUpComboBoxes() {
        Integer[] lanes = {2, 3, 4, 5};
        lanesComboBox = new JComboBox<>(lanes);
        lanesComboBox.addActionListener(this);
        lanesComboBox.setSelectedIndex(-1);
        lanesComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Set number of lanes"));

        String[] trackTypes = {"Straight", "Figure 8", "Oval"};
        trackTypeComboBox = new JComboBox<>(trackTypes);
        trackTypeComboBox.addActionListener(this);
        trackTypeComboBox.setSelectedIndex(-1);
        trackTypeComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose track type"));

        String[] weatherConditions = {"Wet", "Dry", "Hot"};
        weatherConditionComboBox = new JComboBox<>(weatherConditions);
        weatherConditionComboBox.addActionListener(this);
        weatherConditionComboBox.setSelectedIndex(-1);
        weatherConditionComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose weather condition"));

        String[] equipment = {"Saddle", "Cool Hat", "Gold Crown", "Bridle"};
        equipmentComboBox = new JComboBox<>(equipment);
        equipmentComboBox.addActionListener(this);
        equipmentComboBox.setSelectedIndex(-1);
        equipmentComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose equipment"));

        String[] horseShoes = {"Regular", "Lightweight", "Extra Heavy"};
        horseShoesComboBox = new JComboBox<>(horseShoes);
        horseShoesComboBox.addActionListener(this);
        horseShoesComboBox.setSelectedIndex(-1);
        horseShoesComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose horse shoe."));
    }

    private void setUpPanels() {
        racePanel.setLayout(new BorderLayout());
        racePanel.add(trackConfigGUI, BorderLayout.CENTER);

        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsPanel.add(lanesComboBox);
        optionsPanel.add(trackTypeComboBox);
        optionsPanel.add(weatherConditionComboBox);


        extraInfoPanel.setLayout(new FlowLayout());
        optionsPanel.add(startRaceButton);
        optionsPanel.add(resetRaceButton);


        raceInfoPanel.setLayout(new BoxLayout(raceInfoPanel, BoxLayout.Y_AXIS));
        bettingLogic = new BettingLogic(raceInfoPanel, horses);


        horseEditPanel.setLayout(new BoxLayout(horseEditPanel, BoxLayout.Y_AXIS));
        horseEditPanel.add(equipmentLabel);
        horseEditPanel.add(horseShoesComboBox);
        horseEditPanel.add(equipmentComboBox);
    }

    private void setUpFrame() {
        mainFrame = new JFrame("Simulate your race!");
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(racePanel, BorderLayout.CENTER);
        mainFrame.add(optionsPanel, BorderLayout.NORTH);
        mainFrame.add(raceInfoPanel, BorderLayout.EAST);
        mainFrame.add(extraInfoPanel, BorderLayout.SOUTH);
        mainFrame.add(horseEditPanel, BorderLayout.WEST);

        mainFrame.setSize(1080, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void resetRace() {
        // Reset combo box selections
        lanesComboBox.setSelectedIndex(-1);
        trackTypeComboBox.setSelectedIndex(-1);
        weatherConditionComboBox.setSelectedIndex(-1);
        equipmentComboBox.setSelectedIndex(-1);
        horseShoesComboBox.setSelectedIndex(-1);


        horseNum = horseCustomiseGUI.horseNumSetUp();
        horses = horseCustomiseGUI.createHorseOptions(horseNum);

        racePanel.removeAll();
        racePanel.revalidate();
        racePanel.repaint();


        laneNum = 0;
        trackType = "";
        logic = null;

        // Refresh other UI components
        checkIfReadyToRace();  // Ensure that the buttons are properly enabled or disabled
    }


    private void checkIfReadyToRace() {
        boolean ready = lanesComboBox.getSelectedItem() != null
                && trackTypeComboBox.getSelectedItem() != null
                && weatherConditionComboBox.getSelectedItem() != null;
        startRaceButton.setEnabled(ready);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lanesComboBox) {
            handleLaneSelection();
        } else if (e.getSource() == trackTypeComboBox) {
            handleTrackTypeSelection();
        } else if (e.getSource() == startRaceButton) {
            handleStartRace();
        }
        else if (e.getSource() == weatherConditionComboBox) {
            extraInfoPanel.removeAll();  // Clear previous content


            String selectedWeather = (String) weatherConditionComboBox.getSelectedItem();
            if (selectedWeather != null) {
                if (selectedWeather.equals("Wet")) {
                    extraInfoPanel.add(trackConditionInfo, FlowLayout.LEFT);  // Example: Add track condition info for wet weather
                }
                else if (selectedWeather.equals("Hot")) {
                    extraInfoPanel.add(hotWeatherInfo, FlowLayout.LEFT);  // Example: Add info for hot weather
                }
                else if (selectedWeather.equals("Dry")) {
                    extraInfoPanel.add(dryWeatherInfo, FlowLayout.LEFT);  // Example: Add info for dry weather
                }

            }

            extraInfoPanel.revalidate();
            extraInfoPanel.repaint();
        }

        else if (e.getSource() == equipmentComboBox) {
            extraInfoPanel.removeAll();


            String selectedEquipment = (String) equipmentComboBox.getSelectedItem();
            if (selectedEquipment != null) {

                if (selectedEquipment.equals("Saddle")) {
                    extraInfoPanel.add(saddleInfo, FlowLayout.LEFT);
                }
                else if (selectedEquipment.equals("Gold Crown")) {
                    extraInfoPanel.add(goldCrownInfo, FlowLayout.LEFT);
                }
                else if (selectedEquipment.equals("Cool Hat")) {
                    extraInfoPanel.add(coolHatInfo, FlowLayout.LEFT);
                }
                else if (selectedEquipment.equals("Bridle")) {
                    extraInfoPanel.add(bridleInfo, FlowLayout.LEFT);
                }

            }

            extraInfoPanel.revalidate();
            extraInfoPanel.repaint();
        }

        checkIfReadyToRace();
    }

    private void handleLaneSelection() {
        if (lanesComboBox.getSelectedItem() != null) {
            laneNum = (Integer) lanesComboBox.getSelectedItem();
            trackConfigGUI.updateLaneNum(laneNum);
            logic = new HorseMoveLogic(laneNum, horses, (String) weatherConditionComboBox.getSelectedItem(), (String) equipmentComboBox.getSelectedItem(), bettingLogic);
        }
    }

    private void handleTrackTypeSelection() {
        if (trackTypeComboBox.getSelectedItem() != null) {
            trackType = (String) trackTypeComboBox.getSelectedItem();
            if (trackType.equals("Straight")) {
                logic = new HorseMoveLogic(laneNum, horses, weatherConditionComboBox.getSelectedItem() != null ? weatherConditionComboBox.getSelectedItem().toString() : "", equipmentComboBox.getSelectedItem().toString(), bettingLogic);
            }
        }
    }

    private void handleStartRace() {
        racePanel.removeAll();
        logic.setUpHorseMove(racePanel);
        racePanel.add(logic, BorderLayout.CENTER);

        racePanel.revalidate();
        racePanel.repaint();

        startRaceButton.setEnabled(false);

        logic.startRaceWithCallback(() -> startRaceButton.setEnabled(true));
    }
}
class ComboBoxPlaceholderRenderer extends JLabel implements ListCellRenderer<Object> {
    private final String placeholder;

    public ComboBoxPlaceholderRenderer(String placeholder) {
        this.placeholder = placeholder;
        setOpaque(true); // Important for background color
    }

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        if (index == -1 && value == null) {
            setText(placeholder);
        } else {
            setText(value != null ? value.toString() : "");
        }
        return this;
    }
}