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
    private JButton ovalTrackButton;
    private JLabel TrackLabel;
    private ImageIcon straightTrackIcon;
    private ImageIcon ovalTrackIcon;
    private JComboBox lanesComboBox;

    public void backButtonActionPerformed() {
        System.out.println("backButtonActionPerformed");
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        frame.setVisible(false);
        mainMenuGUI.setUpGUI();
    }

    public void straightTrackButtonActionPerformed() {
        System.out.println("straightTrackButtonActionPerformed");
        TrackLabel.setVisible(!TrackLabel.isVisible());
        TrackLabel.setIcon(straightTrackIcon);
        lanesComboBox.setVisible(TrackLabel.isVisible());
        mainPanel.add(lanesComboBox, BorderLayout.WEST);

    }

    public void ovalTrackButtonActionPerformed() {
        System.out.println("ovalTrackButtonActionPerformed");
        TrackLabel.setVisible(!TrackLabel.isVisible());
        TrackLabel.setIcon(ovalTrackIcon);
        lanesComboBox.setVisible(TrackLabel.isVisible());
        mainPanel.add(lanesComboBox, BorderLayout.WEST);
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

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(TrackLabel);
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

    }

    public void setUpComboBox() {
        Integer[] lanes = {1, 2, 3, 4, 5};
        lanesComboBox = new JComboBox(lanes);
        lanesComboBox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            backButtonActionPerformed();

        }
        else if (e.getSource() == straightTrackButton) {
            straightTrackButtonActionPerformed();
        }
        else if (e.getSource() == ovalTrackButton) {
            ovalTrackButtonActionPerformed();
        }
        else if (e.getSource() == lanesComboBox) {
            System.out.println(lanesComboBox.getSelectedItem());
        }

    }
}
