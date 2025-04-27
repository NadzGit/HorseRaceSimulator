import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseInfoGUI implements ActionListener {
    private JFrame mainFrame = new JFrame("Horse Info");
    private JButton quarterButton = new JButton("Quarter");
    private JButton thoroughBredButton = new JButton("Thorough Bred");
    private JButton arabianButton = new JButton("Arabian");

    private JLabel quarterLabel = new JLabel("Quarter");
    private JLabel thoroughBredLabel = new JLabel("Thorough Bred");
    private JLabel arabianLabel = new JLabel("Arabian");

    private JLabel quarterLabelInfo = new JLabel("The Quarter Horse will have an average confidence of 0.4/1.0. This is subject to change based on equipment, weather conditions, and wins.");
    private JLabel thoroughBredLabelInfo = new JLabel("The Thoroughbred Horse will have an average confidence of 0.7/1.0. This is subject to change based on equipment, weather conditions, and wins.");
    private JLabel arabianLabelInfo = new JLabel("The Arabian Horse will have an average confidence of 0.5/1.0. This is subject to change based on equipment, weather conditions, and wins.");

    private JButton back = new JButton("Back");

    private JPanel mainPanel = new JPanel();
    private JPanel infoPanel = new JPanel();

    private ImageIcon horseIcon = new ImageIcon("Part 2/horse.png");

    public void setUpButtons() {
        quarterButton.addActionListener(this);
        thoroughBredButton.addActionListener(this);
        arabianButton.addActionListener(this);
        back.addActionListener(this);
    }

    public void setUpFrame() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setIconImage(horseIcon.getImage());

        mainPanel.setLayout(new FlowLayout());  // Buttons are placed in a horizontal row
        mainPanel.add(quarterButton);
        mainPanel.add(thoroughBredButton);
        mainPanel.add(arabianButton);

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));  // Stack text vertically
        infoPanel.add(back);  // Add the back button to the bottom of the text section

        mainFrame.add(mainPanel, BorderLayout.NORTH);  // Buttons at the top
        mainFrame.add(infoPanel, BorderLayout.CENTER);  // Text below the buttons

        mainFrame.setSize(400, 300); // Set the window size
    }

    public void setUpGUI() {
        setUpButtons();
        setUpFrame();
        mainFrame.setVisible(true); // Make the frame visible at the end of setup
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        infoPanel.removeAll(); // Clear previous text content

        if (e.getSource() == quarterButton) {
            infoPanel.add(quarterLabel);
            infoPanel.add(quarterLabelInfo);
        } else if (e.getSource() == thoroughBredButton) {
            infoPanel.add(thoroughBredLabel);
            infoPanel.add(thoroughBredLabelInfo);
        } else if (e.getSource() == arabianButton) {
            infoPanel.add(arabianLabel);
            infoPanel.add(arabianLabelInfo);
        } else if (e.getSource() == back) {
            // Handle "Back" button to navigate back to main menu
            MainMenuGUI mainMenuGUI = new MainMenuGUI();
            mainMenuGUI.setUpGUI();
            mainFrame.dispose(); // Close the current frame before opening the main menu
            return;
        }

        // Re-add the back button at the bottom of the info panel
        infoPanel.add(back);

        mainFrame.revalidate(); // Ensure the layout is updated with the new components
        mainFrame.repaint();    // Ensure the components are rendered correctly
    }
}
