import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseCustomiseGUI implements ActionListener {
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
    private JButton breedButton = new JButton("Breed");
    private JButton nameButton = new JButton("Name");
    private JButton thoroughBredButton = new JButton("ThoroughBred");
    private JButton ArabianButton = new JButton("Arabian");
    private JButton QuarterHorse = new JButton("Quarter Horse");

    public void setUpGUI() {
        setUpButton();
        setUpPanel();
        setUpFrame();
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
        optionsPanel.add(breedButton);
        optionsPanel.add(Box.createVerticalStrut(50));
        optionsPanel.add(nameButton);

        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mainPanel.setBackground(Color.WHITE);
    }

    public void setUpButton() {
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(this);

        nameButton.setBackground(Color.WHITE);
        nameButton.addActionListener(this);

        breedButton.setBackground(Color.WHITE);
        breedButton.addActionListener(this);

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
        if (e.getSource() == nextButton) {
            nextButtonActionPerformed();
        }
    }
}
