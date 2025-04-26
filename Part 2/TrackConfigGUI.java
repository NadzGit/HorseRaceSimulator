//sets up track (configurable lane number)

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TrackConfigGUI extends JComponent {
    private int laneNum;

    public TrackConfigGUI(int laneNum) {
        this.laneNum = laneNum;
        setPreferredSize(new Dimension(1000, 600));  // Set the preferred size of the panel
    }

    // Override paintComponent to draw the track
    @Override
    // Inside TrackConfigGUI class
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ensure the background is cleared before painting
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);

        for (int i = 0; i < laneNum; i++) {
            Line2D.Double line = new Line2D.Double(100, 500 + (i * 20), 900, 500 + (i * 20));
            g2d.draw(line);
        }
    }

    // Method to update the number of lanes dynamically
    public void updateLaneNum(int newLaneNum) {
        this.laneNum = newLaneNum;
        repaint();  // Repaint the component to reflect the new lane number
    }
}

