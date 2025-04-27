import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGUI implements ActionListener {
    private JFrame frame;
    private JButton startButton;

    private JPanel sideBar = new JPanel();
    private JButton horseButton = new JButton("Horses");

    private ImageIcon horseIcon = new ImageIcon("Part 2/horse.png");
    ImageIcon mainMenuBG;
    private BackgroundPanel BGImage;




    public void setUpGUI() {
        ImageIcon originalIcon = new ImageIcon("Part 2/mainMenuBG.jpg");
        mainMenuBG = originalIcon; // no need to scale here anymore

        BGImage = new BackgroundPanel(mainMenuBG);
        BGImage.setLayout(new BorderLayout());

        frame = new JFrame("Horse Race Simulator");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Optional: Start maximized
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(horseIcon.getImage());

        startButton = new JButton("Start Race");

        sideBar.setLayout(new FlowLayout());
        sideBar.setOpaque(false);
        sideBar.add(horseButton);
        styleButton(horseButton);
        addHoverEffect(horseButton);
        styleButton(startButton);
        addHoverEffect(startButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.add(startButton);

        BGImage.add(sideBar, BorderLayout.WEST);
        BGImage.add(mainPanel, BorderLayout.CENTER);

        frame.setContentPane(BGImage);
        frame.setVisible(true);
    }


    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Serif", Font.BOLD, 20));
    }
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.YELLOW); // Highlight color when hovering
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE); // Back to normal
            }
        });
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
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(ImageIcon icon) {
        this.backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


