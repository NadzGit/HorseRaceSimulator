import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenuGUI implements ActionListener {
    private JFrame frame;
    private JButton startButton;

    private JPanel sideBar = new JPanel();
    private JButton horseButton = new JButton("Horses");

    private ImageIcon horseIcon = new ImageIcon("Part 2/horse.png");


    public void setUpGUI() {
        // Create the start button
        startButton = new JButton("Start Race");
        startButton.addActionListener(this);

        // Set up the frame and panels
        frame = new JFrame("Horse Race Simulator");
        frame.setSize(1080, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(sideBar, BorderLayout.WEST);
        frame.setIconImage(horseIcon.getImage());

        sideBar.add(horseButton);
        horseButton.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.add(startButton);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            frame.setVisible(false);
            RaceGUI raceGUI = new RaceGUI();
            raceGUI.setUpGUI();

        }
        if (e.getSource() == horseButton) {
            HorseInfoGUI horseInfoGUI = new HorseInfoGUI();
            horseInfoGUI.setUpGUI();
        }
    }
}
