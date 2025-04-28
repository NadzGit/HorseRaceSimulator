import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;


public class TrackConfigGUI extends JComponent {
    private int laneNum;
    private HorsePart2[] horses;

    public TrackConfigGUI(int laneNum, HorsePart2[] horses) {
        this.laneNum = laneNum;
        this.horses = horses;
        setPreferredSize(new Dimension(1000, 600));
    }

    private boolean allFallen() {
        for (HorsePart2 h : horses) {
            if (h != null && !h.hasFallen()) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);

        for (int i = 0; i < laneNum; i++) {
            Line2D.Double line = new Line2D.Double(100, 500 + (i * 20), 900, 500 + (i * 20));
            g2d.draw(line);
        }

    }

    public void updateLaneNum(int newLaneNum) {
        this.laneNum = newLaneNum;
        repaint();
    }

    public void adjustSpeedAndConfidence(String weatherCondition, String equipmentType) {
        for (HorsePart2 horse : horses) {
            double confidence = horse.getConfidence();

            if (weatherCondition != null) {
                switch (weatherCondition) {
                    case "Wet":
                        confidence *= 0.9;
                        break;
                    case "Dry":
                        confidence *= 1.1;
                        break;
                    case "Hot":
                        confidence *= 1.05;
                        break;
                }
            }

            if (equipmentType != null) {
                switch (equipmentType) {
                    case "Saddle":
                        confidence *= 1.0;
                        break;
                    case "Gold Crown":
                        confidence *= 1.2;
                        break;
                    case "Cool Hat":
                        confidence *= 1.1;
                        break;
                    case "Bridle":
                        confidence *= 1.05;
                        break;
                }
            }

            horse.setConfidence(confidence);
        }
    }
}
