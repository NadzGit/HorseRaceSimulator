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
    private JComboBox<String> raceDistanceComboBox;
    private JLabel raceDistanceLabel;
    private JLabel hotWeatherInfo;
    private JLabel dryWeatherInfo;
    private JLabel wetWeatherInfo;

    private JLabel saddleInfo;
    private JLabel crownInfo;
    private JLabel bridleInfo;
    private JLabel hatInfo;



    private HorsePart2[] horses;
    private HorseMoveLogic logic;

    private final HorseCustomiseGUI horseCustomiseGUI = new HorseCustomiseGUI();
    private final JButton startRaceButton = new JButton("Start Race");

    private JComboBox<Integer> lanesComboBox;
    private JComboBox<String> trackTypeComboBox;
    private JComboBox<String> weatherConditionComboBox;
    JComboBox<String> equipmentComboBox;
    JComboBox<String> horseShoesComboBox;

    private JLabel trackConditionInfo;
    private JLabel raceAnalyticsLabel;
    private JLabel equipmentLabel = new JLabel("Equipment Type");

    private TrackConfigGUI trackConfigGUI;

    private JButton ResetRaceButton = new JButton("Reset Race");

    public void setUpGUI() {
        SwingUtilities.invokeLater(() -> {
            horseNum = horseCustomiseGUI.horseNumSetUp();
            horses = horseCustomiseGUI.createHorseOptions(horseNum);

            trackConfigGUI = new TrackConfigGUI(laneNum);

            setUpButton();
            setUpLabels();
            setUpComboBoxes();
            setUpPanels();
            setUpFrame();
        });
    }
    private void checkIfReadyToRace() {
        boolean ready = lanesComboBox.getSelectedItem() != null
                && trackTypeComboBox.getSelectedItem() != null
                && weatherConditionComboBox.getSelectedItem() != null;
        startRaceButton.setEnabled(ready);
        ResetRaceButton.setEnabled(ready);
    }

    private void setUpButton() {
        startRaceButton.addActionListener(this);
        startRaceButton.setEnabled(true);
        startRaceButton.setEnabled(false);

        ResetRaceButton.addActionListener(this);
        ResetRaceButton.setEnabled(true);
        ResetRaceButton.setEnabled(false);
    }

    public void setUpLabels() {
        hotWeatherInfo = new JLabel("• Hot: Slightly decreases confidence and speed.");
        wetWeatherInfo = new JLabel("• Wet: Significantly decreases confidence and speed.");
        dryWeatherInfo = new JLabel("• Dry: No effect on speed or stamina.");
        saddleInfo = new JLabel("• Saddle: +0.1 confidence, Scaling: 5");
        hatInfo = new JLabel("• Cool Hat: +0.105 confidence, Scaling: 7");
        bridleInfo = new JLabel("• Bridle: +0.2 confidence, Scaling: 3");
        crownInfo = new JLabel("• Gold Crown: +0.25 confidence, Scaling: 4");

        raceAnalyticsLabel = new JLabel("Race Analytics");
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

        String [] horseShoes = {"Regular", "Lightweight", "Extra Heavy"};
        horseShoesComboBox = new JComboBox<>(horseShoes);
        horseShoesComboBox.addActionListener(this);
        horseShoesComboBox.setSelectedIndex(-1);
        horseShoesComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose horse shoe."));

        String[] raceDistances = {"500", "1000", "1500", "2000", "2500", "3000"};  // Distances in meters
        raceDistanceComboBox = new JComboBox<>(raceDistances);
        raceDistanceComboBox.addActionListener(this);
        raceDistanceComboBox.setSelectedIndex(-1);
        raceDistanceComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose race distance"));

    }

    private void setUpPanels() {
        racePanel.setLayout(new BorderLayout());
        racePanel.add(trackConfigGUI, BorderLayout.CENTER);

        optionsPanel.setLayout(new FlowLayout());
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsPanel.add(lanesComboBox);
        optionsPanel.add(trackTypeComboBox);
        optionsPanel.add(weatherConditionComboBox);
        optionsPanel.add(raceDistanceComboBox);

        extraInfoPanel.setLayout(new FlowLayout());
        optionsPanel.add(startRaceButton);


        raceInfoPanel.setLayout(new BoxLayout(raceInfoPanel, BoxLayout.Y_AXIS));
        raceInfoPanel.add(raceAnalyticsLabel);
        BettingLogic bettingLogic = new BettingLogic(raceInfoPanel, horses);

        horseEditPanel.setLayout(new BoxLayout(horseEditPanel, BoxLayout.Y_AXIS));
        horseEditPanel.add(equipmentLabel);
        horseEditPanel.add(horseShoesComboBox);
        horseEditPanel.add(equipmentComboBox);

        optionsPanel.add(ResetRaceButton);

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

        //set up new horses
        horseNum = horseCustomiseGUI.horseNumSetUp();
        horses = horseCustomiseGUI.createHorseOptions(horseNum);

        // Refresh race panel
        racePanel.removeAll();
        racePanel.revalidate();
        racePanel.repaint();


        // Refresh other UI components
        checkIfReadyToRace();  // Ensure that the buttons are properly enabled or disabled
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lanesComboBox) {
            handleLaneSelection();
        } else if (e.getSource() == trackTypeComboBox) {
            handleTrackTypeSelection();
        } else if (e.getSource() == raceDistanceComboBox) {
            handleRaceDistanceSelection();
        } else if (e.getSource() == startRaceButton) {
            handleStartRace();
        } else if (e.getSource() == ResetRaceButton) {
            resetRace();
        } else if (e.getSource() == weatherConditionComboBox) {
            extraInfoPanel.removeAll();


            String selectedWeather = (String) weatherConditionComboBox.getSelectedItem();
            if (selectedWeather != null) {
                if (selectedWeather.equals("Wet")) {
                    extraInfoPanel.add(wetWeatherInfo, FlowLayout.LEFT);
                }
                else if (selectedWeather.equals("Dry")) {
                    extraInfoPanel.add(dryWeatherInfo, FlowLayout.LEFT);
                }
                else if (selectedWeather.equals("Hot")) {
                    extraInfoPanel.add(hotWeatherInfo, FlowLayout.LEFT);
                }
            }

            extraInfoPanel.revalidate();
            extraInfoPanel.repaint();
        }

        else if (e.getSource() == equipmentComboBox) {
            extraInfoPanel.removeAll();  // Clear previous content


            String selectedEquipment = (String) equipmentComboBox.getSelectedItem();
            if (selectedEquipment != null) {

                if (selectedEquipment.equals("Saddle")) {
                    extraInfoPanel.add(saddleInfo, FlowLayout.LEFT);  // Example condition
                }

                else if (selectedEquipment.equals("Cool Hat")) {
                    extraInfoPanel.add(hatInfo, FlowLayout.LEFT);
                }
                else if (selectedEquipment.equals("Saddle")) {
                    extraInfoPanel.add(saddleInfo, FlowLayout.LEFT);
                }
                else if (selectedEquipment.equals("Gold Crown")) {
                    extraInfoPanel.add(crownInfo, FlowLayout.LEFT);
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


    private void handleRaceDistanceSelection() {
        if (raceDistanceComboBox.getSelectedItem() != null) {
            String raceDistance = (String) raceDistanceComboBox.getSelectedItem();


        }
    }


    private void handleLaneSelection() {
        if (lanesComboBox.getSelectedItem() != null) {
            laneNum = (Integer) lanesComboBox.getSelectedItem();
            trackConfigGUI.updateLaneNum(laneNum);
            logic = new HorseMoveLogic(laneNum, horses, (String) weatherConditionComboBox.getSelectedItem(), (String) equipmentComboBox.getSelectedItem());
        }
    }

    private void handleTrackTypeSelection() {
        if (trackTypeComboBox.getSelectedItem() != null) {
            trackType = (String) trackTypeComboBox.getSelectedItem();
            if (trackType.equals("Straight")) {
                logic = new HorseMoveLogic(laneNum, horses, weatherConditionComboBox.getSelectedItem() != null ? weatherConditionComboBox.getSelectedItem().toString() : "", equipmentComboBox.getSelectedItem() != null ? equipmentComboBox.getSelectedItem().toString() : "");
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
