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
            setUpComboBoxes();
            setUpLabels();
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
        trackConditionInfo = new JLabel("Track Condition: Dry makes no difference to speed or stamina." +
                "Wet decreases significantly decreases confidence and speed. Hot slightly decreases confidence and speed.");

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
        extraInfoPanel.add(startRaceButton);

        extraInfoPanel.add(trackConditionInfo, FlowLayout.LEFT);
        raceInfoPanel.setLayout(new BoxLayout(raceInfoPanel, BoxLayout.Y_AXIS));
        raceInfoPanel.add(raceAnalyticsLabel);
        BettingLogic bettingLogic = new BettingLogic(raceInfoPanel, horses);

        horseEditPanel.setLayout(new BoxLayout(horseEditPanel, BoxLayout.Y_AXIS));
        horseEditPanel.add(equipmentLabel);
        horseEditPanel.add(horseShoesComboBox);
        horseEditPanel.add(equipmentComboBox);

        extraInfoPanel.add(ResetRaceButton);

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
        } else if (e.getSource() == startRaceButton) {
            handleStartRace();
        }
        else if (e.getSource() == ResetRaceButton){
            resetRace();
        }
        checkIfReadyToRace();
    }

    private void handleLaneSelection() {
        if (lanesComboBox.getSelectedItem() != null) {
            laneNum = (Integer) lanesComboBox.getSelectedItem();
            trackConfigGUI.updateLaneNum(laneNum);
            logic = new HorseMoveLogic(laneNum, horses, (String) weatherConditionComboBox.getSelectedItem());
        }
    }

    private void handleTrackTypeSelection() {
        if (trackTypeComboBox.getSelectedItem() != null) {
            trackType = (String) trackTypeComboBox.getSelectedItem();
            if (trackType.equals("Straight")) {
                logic = new HorseMoveLogic(laneNum, horses, weatherConditionComboBox.getSelectedItem() != null ? weatherConditionComboBox.getSelectedItem().toString() : "");
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
