import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceGUI implements ActionListener {
    private int laneNum = 0;
    private int horseNum = 0;
    private JFrame mainFrame;
    private JPanel optionsPanel = new JPanel();
    private JPanel extraInfoPanel = new JPanel();
    private JPanel raceInfoPanel = new JPanel();
    private JPanel racePanel = new JPanel();

    private JComboBox<Integer> lanesComboBox;
    private JComboBox<String> trackTypeComboBox;
    private JComboBox<String> weatherConditionComboBox;




    public RaceGUI(int horseNum) {
        this.horseNum = horseNum;
    }

    public void setUpGUI() {
        setUpComboBoxes();
        setUpPanels();
        setUpFrame();
    }

    public void setUpComboBoxes() {
        Integer[] lanes = {2, 3, 4, 5};
        lanesComboBox = new JComboBox<>(lanes);
        lanesComboBox.addActionListener(this);
        lanesComboBox.setSelectedIndex(-1);
        lanesComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Set number of lanes"));

        String[] trackTypes = {"Straight", "Figure 8", "Oval"};
        trackTypeComboBox = new JComboBox<>(trackTypes);
        trackTypeComboBox.setToolTipText("Select track type");
        trackTypeComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose track type"));
        trackTypeComboBox.setSelectedIndex(-1);
        trackTypeComboBox.addActionListener(this);

        String[] weatherConditions = {"Wet", "Dry", "Hot"};
        weatherConditionComboBox = new JComboBox<>(weatherConditions);
        weatherConditionComboBox.setToolTipText("Select weather condition");
        weatherConditionComboBox.setRenderer(new ComboBoxPlaceholderRenderer("Choose weather condition"));
        weatherConditionComboBox.setSelectedIndex(-1);
        weatherConditionComboBox.addActionListener(this);




    }

    public void setUpFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Simulate your race!");
        mainFrame.add(racePanel, BorderLayout.CENTER);
        mainFrame.add(optionsPanel, BorderLayout.NORTH);
        mainFrame.add(raceInfoPanel, BorderLayout.EAST);
        mainFrame.add(extraInfoPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
        mainFrame.setSize(1080, 1080);
        TrackConfigGUI trackConfigGUI = new TrackConfigGUI(laneNum);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void setUpPanels() {
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options"));
        optionsPanel.add(lanesComboBox);
        optionsPanel.add(trackTypeComboBox);
        optionsPanel.add(weatherConditionComboBox);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lanesComboBox && lanesComboBox.getSelectedItem() != null) {
            laneNum = Integer.parseInt(lanesComboBox.getSelectedItem().toString());

        } else if (e.getSource() == trackTypeComboBox && trackTypeComboBox.getSelectedItem() != null) {

        }
    }
}

class ComboBoxPlaceholderRenderer extends JLabel implements ListCellRenderer<Object> {
    private String title;

    public ComboBoxPlaceholderRenderer(String title) {
        this.title = title;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index == -1 && value == null) {
            setText(title);
        } else {
            setText(value.toString());
        }
        return this;
    }
}

