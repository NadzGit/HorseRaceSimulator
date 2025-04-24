import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceGUI extends JLabel implements ActionListener, ListCellRenderer{
    private int horseNum;
    private String title;
    private JFrame mainFrame;
    private JPanel racePanel = new JPanel();
    private JPanel optionsPanel = new JPanel();
    private JPanel extraInfoPanel = new JPanel();
    private JPanel raceInfoPanel = new JPanel();

    private JComboBox trackTypeComboBox;
    private JComboBox weatherConditionComboBox;
    private JComboBox lanesComboBox;

    public RaceGUI(int horseNum) {
        this.horseNum = horseNum;
    }

    public void setUpGUI() {
        setUpComboBoxes();
        setUpPanels();
        setUpFrame();
    }

    public RaceGUI (String newTitle) {
        title = newTitle;
    }

    public void setUpComboBoxes() {
        Integer[] lanes = {2, 3, 4, 5};
        lanesComboBox = new JComboBox(lanes);
        lanesComboBox.addActionListener(this);
        lanesComboBox.setSelectedIndex(-1);
        lanesComboBox.setRenderer(new RaceGUI("Set number of lanes"));

        String[] trackTypes = {"Straight", "Figure 8", "Oval"};
        trackTypeComboBox = new JComboBox(trackTypes);
        trackTypeComboBox.setToolTipText("Select track type");
        trackTypeComboBox.setRenderer(new RaceGUI("Choose track type"));
        trackTypeComboBox.setSelectedIndex(-1);
        trackTypeComboBox.addActionListener(this);

        String[] weatherConditions = {"Wet","Dry", "Hot"};
        weatherConditionComboBox = new JComboBox(weatherConditions);
        weatherConditionComboBox.setToolTipText("Select weather condition");
        weatherConditionComboBox.setRenderer(new RaceGUI("Choose weather condition"));
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

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index == -1 && value == null) {
            setText(title);
        }
        else{
            setText(value.toString());
        }
        return this;
    }
}
