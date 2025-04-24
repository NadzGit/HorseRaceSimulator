import javax.swing.*;
import java.awt.*;

class TrackPanel extends JPanel {
    private int laneCount = 0;
    private String trackType = "";

    public void setLaneCount(int laneCount) {
        this.laneCount = laneCount;
        repaint();
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);

        if (trackType.equals("Straight")) {
            for (int i = 0; i < laneCount; i++) {
                g2.drawLine(50, 100 + i * 40, 800, 100 + i * 40);
            }
        } else if (trackType.equals("Oval")) {
            for (int i = 0; i < laneCount; i++) {
                g2.drawOval(100 + i * 10, 100 + i * 10, 400 - i * 20, 200 - i * 20);
            }
        } else if (trackType.equals("Figure 8")) {
            for (int i = 0; i < laneCount; i++) {
                g2.drawOval(200 + i * 10, 100 + i * 10, 150 - i * 20, 150 - i * 20);
                g2.drawOval(100 + i * 10, 200 + i * 10, 150 - i * 20, 150 - i * 20);
            }
        }
    }
}
