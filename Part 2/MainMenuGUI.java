import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI implements ActionListener {
    private JLabel title;
    private ImageIcon gameIcon;
    private ImageIcon horseIcon;
    private ImageIcon homeIcon;
    private Border horseTitleborder;
    private JPanel optionsPanel;
    private JButton homeButton;
    private JButton HorsesButton;
    private JButton statsButton;
    private JButton bettingButton;
    private JButton startButton;
    private JPanel mainMenuPanel;
    public JFrame frame = new JFrame();


    public void styleButton(JButton button, String text) {
        button.setText(text);
        button.setOpaque(true);
        button.setBackground(Color.GRAY);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Comic Sans", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    public void goToStatsGUI() {
        System.out.print("Going to Stats GUI");
    }

    public void goToMainMenuGUI() {
        System.out.print("Going to Main Menu GUI");
    }

    public void goToHorsesGUI() {
        System.out.println("Going to Horse Customisation GUI");
        HorseCustomiseGUI horseCustomiseGUI = new HorseCustomiseGUI();
        horseCustomiseGUI.setUpGUI();

    }

    public void setUpGUI () {
        //Images
        gameIcon = new ImageIcon("Part 2/horse.png");
        horseIcon = new ImageIcon("Part 2/horseimage.png");
        homeIcon = new ImageIcon("home.png");

        horseTitleborder = BorderFactory.createLineBorder(Color.cyan, 3);
        setUpButtons();
        setUpLabels();
        setUpPanels();
        setUpFrame();

    }

    public void setUpFrame() {
        frame.setSize(1080, 1080);
        frame.setTitle("Horse Race Simulator");
        frame.setIconImage(gameIcon.getImage());
        frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());

        frame.add(optionsPanel, BorderLayout.WEST);
        frame.add(title, BorderLayout.NORTH);
        frame.add(mainMenuPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void setUpLabels() {
        title = new JLabel();
        title.setIcon(horseIcon);
        title.setText("Horse Race Simulator");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        title.setForeground(Color.DARK_GRAY);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        title.setBackground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(horseTitleborder);


    }

    public void setUpPanels() {
        //panel stuff

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(Color.GRAY);
        optionsPanel.add(homeButton);
        optionsPanel.add(Box.createVerticalStrut(50));
        optionsPanel.add(HorsesButton);
        optionsPanel.add(Box.createVerticalStrut(50));
        optionsPanel.add(statsButton);
        optionsPanel.add(Box.createVerticalStrut(50));
        optionsPanel.add(bettingButton);

        mainMenuPanel = new JPanel();
        mainMenuPanel.setBackground(Color.white);
        mainMenuPanel.add(startButton);

    }

    public void setUpButtons(){
        //Home Button
        homeButton = new JButton();
        styleButton(homeButton, "Home");


        //Horses Button
        HorsesButton = new JButton();
        styleButton(HorsesButton, "Horse");


        // Stats Button
        statsButton = new JButton();
        styleButton(statsButton, "Stats");

        //Betting Button
        bettingButton = new JButton();
       styleButton(bettingButton, "Betting");

        //Start Button
        startButton = new JButton();
        styleButton(startButton, "Start");
        startButton.setBackground(Color.green);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            goToMainMenuGUI();
        }
        else if (e.getSource() == HorsesButton) {
            goToHorsesGUI();
        }
        else if (e.getSource() == statsButton) {
            goToStatsGUI();
        }
        else if (e.getSource() == startButton) {
            goToHorsesGUI();

           optionsPanel.setVisible(false);
           mainMenuPanel.setVisible(false);
           frame.setVisible(false);

        }
    }

}
