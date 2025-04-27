import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackGUI implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JLabel title;
    private JPanel optionsPanel;
    private JButton backButton;
    private JButton straightTrackButton;
    private JButton figure8Button = new JButton("Figure 8");
    private ImageIcon figure8Icon = new ImageIcon("Part 2/figure8.png");
    private JButton ovalTrackButton;
    private JLabel TrackLabel;
    private ImageIcon straightTrackIcon;
    private ImageIcon ovalTrackIcon;
    private JComboBox lanesComboBox;
    private JButton confirmButton;
    private TrackLogic trackLogic = new TrackLogic();
    public String trackType = "";
    public int laneNum = 0;

    public void backButtonActionPerformed() {
        System.out.println("backButtonActionPerformed");
        HorseCustomiseGUI horseCustomiseGUI = new HorseCustomiseGUI();
        frame.setVisible(false);
        horseCustomiseGUI.setUpGUI();
    }

    public String straightTrackButtonActionPerformed() {
        TrackLabel.removeAll();
        System.out.println("straightTrackButtonActionPerformed");
        confirmButton.setVisible(true);
        TrackLabel.setVisible(true);
        TrackLabel.setIcon(straightTrackIcon);
        lanesComboBox.setVisible(TrackLabel.isVisible());
        mainPanel.add(lanesComboBox, BorderLayout.WEST);
        return "Straight Track";
    }

    public String figure8ButtonActionPerformed() {
        TrackLabel.removeAll();
        TrackLabel.setIcon(figure8Icon);
        System.out.println("figure8ButtonActionPerformed");
        confirmButton.setVisible(true);
        TrackLabel.setVisible(true);
        TrackLabel.setIcon(figure8Icon);
        lanesComboBox.setVisible(TrackLabel.isVisible());
        mainPanel.add(lanesComboBox, BorderLayout.WEST);
        return "Figure 8 Track";
    }

    public String ovalTrackButtonActionPerformed() {
        TrackLabel.removeAll();
        System.out.println("ovalTrackButtonActionPerformed");
        confirmButton.setVisible(true);
        TrackLabel.setVisible(true);
        TrackLabel.setIcon(ovalTrackIcon);
        lanesComboBox.setVisible(TrackLabel.isVisible());
        mainPanel.add(lanesComboBox, BorderLayout.WEST);

        return "Oval Track";
    }

    public void confirmButtonActionPerformed(int laneNum, String trackType) {
        confirmButton.setVisible(true);
        System.out.println("confirmButtonActionPerformed");
        trackLogic.storeTrackInfo(laneNum, trackType);

    }

    public void setUpGUI() {
        straightTrackIcon = new ImageIcon("Part 2/straightTrack.png");
        ovalTrackIcon = new ImageIcon("Part 2/ovalTrack.png");
        setUpLabel();
        setUpButtons();
        setUpComboBox();
        setUpPanel();
        setUpFrame();
    }

    public void setUpFrame() {
        frame.setVisible(true);
        frame.setSize(1080, 1080);
        frame.setTitle("Customise your track!");
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        frame.add(optionsPanel, BorderLayout.WEST);
        frame.add(title, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setUpLabel() {
        title = new JLabel("Customise your track!");
        title.setFont(new Font("Comic Sans", Font.BOLD, 20));
        title.setForeground(Color.black);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        TrackLabel = new JLabel();
        TrackLabel.setVisible(false);
        TrackLabel.setPreferredSize(new Dimension(800, 800));
        TrackLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setUpPanel() {
        optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setLayout(new BorderLayout());
        optionsPanel.setPreferredSize(new Dimension(500, 1080));
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(backButton);
        optionsPanel.add(Box.createVerticalStrut(100));
        optionsPanel.add(straightTrackButton);
        optionsPanel.add(Box.createVerticalStrut(100));
        optionsPanel.add(ovalTrackButton);
        optionsPanel.add(Box.createVerticalStrut(100));
        optionsPanel.add(figure8Button);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(TrackLabel);
        mainPanel.add(confirmButton);
        mainPanel.setPreferredSize(new Dimension(800, 800));

    }

    public void setUpButtons() {
        backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.black);
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        straightTrackButton = new JButton("Straight Track");
        straightTrackButton.setBackground(Color.WHITE);
        straightTrackButton.setForeground(Color.black);
        straightTrackButton.addActionListener(this);
        straightTrackButton.setFocusable(false);

        ovalTrackButton = new JButton("Oval Track");
        ovalTrackButton.setBackground(Color.WHITE);
        ovalTrackButton.setForeground(Color.black);
        ovalTrackButton.addActionListener(this);
        ovalTrackButton.setFocusable(false);

        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setForeground(Color.black);
        confirmButton.addActionListener(this);
        confirmButton.setFocusable(false);
        confirmButton.setVisible(false);
        confirmButton.setBounds(200, 200, 100, 50);


        figure8Button.setBackground(Color.WHITE);
        figure8Button.setForeground(Color.black);
        figure8Button.addActionListener(this);
        figure8Button.setFocusable(false);


    }

    public void setUpComboBox() {
        Integer[] lanes = {1, 2, 3, 4, 5};
        lanesComboBox = new JComboBox(lanes);
        lanesComboBox.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        laneNum = Integer.parseInt(lanesComboBox.getSelectedItem().toString());
        if (e.getSource() == backButton) {
            backButtonActionPerformed();
        }
        else if (e.getSource() == straightTrackButton) {
            trackType = straightTrackButtonActionPerformed();

        }
        else if (e.getSource() == ovalTrackButton) {
            trackType = ovalTrackButtonActionPerformed();

        }
        else if (e.getSource() == lanesComboBox) {
            System.out.println(lanesComboBox.getSelectedItem());
        }
        else if (e.getSource() == figure8Button) {
            trackType = figure8ButtonActionPerformed();

        }
        else if (e.getSource() == confirmButton) {
            System.out.println("confirmButtonActionPerformed");

            System.out.println(trackType);
            System.out.println(laneNum);
            confirmButtonActionPerformed(laneNum, trackType);
        }
    }
}
