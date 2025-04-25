//this class is to deal with displaying track types and different number of lanes

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class TrackConfigGUI extends JComponent {
    int laneNum = 0;
    public TrackConfigGUI(int laneNum) {
        this.laneNum = laneNum;
    }


    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Line2D.Double line = new Line2D.Double(100,500,900,500);
        g2d.setColor(Color.black);
        for (int i = 0; i <laneNum; i++) {
            g2d.draw(line);
        }



    }
}
