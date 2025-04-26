//moves horses

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class HorseMoveLogic extends JPanel implements ActionListener {
    private Horse [] horses;
    private Timer timer;
    private int laneNum;
    private JPanel parentFrame;
    private Random random = new Random();
    private Runnable raceEndCallback;
    public HorseMoveLogic(int laneNum, Horse[] horses) {
        this.laneNum = laneNum;
        this.horses = horses;
        timer = new Timer(100, this); // updates every 100ms
    }

    public void setUpHorseMove(JPanel frame) {
        this.parentFrame = frame;
    }

    public void startRace() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the lanes
        g.setColor(Color.BLACK);
        for (int i = 0; i < laneNum; i++) {
            int y = 500 + i * 40;
            g.drawLine(100, y, 900, y);
        }

        // Draw the horses
        for (int i = 0; i < horses.length; i++) {
            Horse horse = horses[i];
            int x = 100 + horse.getDistanceTravelled() * 10; // Distance moves in increments
            int y = 500 + i * 40;

            // Change color based on the horse's progress (confidence)
            Color horseColor = new Color((int)(horse.getConfidence() * 255), 0, 0);  // Redder for lower confidence
            g.setColor(horseColor);
            g.fillOval(x, y - 15, 30, 30); // simple horse as a circle
            g.setColor(Color.BLACK);
            g.drawString(horse.getName(), x, y - 20); // show horse name above
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean raceFinished = false;

        // Move each horse
        for (Horse horse : horses) {
            if (!horse.hasFallen()) {
                // If horse moves (based on confidence), move forward
                if (random.nextDouble() < horse.getConfidence()) {
                    horse.moveForward();
                }
            }
        }

        repaint();  // Redraw the screen with updated positions
        raceFinished = checkFinishLine();

        if (raceFinished) {
            timer.stop();
            highlightWinner();
            if (raceEndCallback != null) {
                raceEndCallback.run(); // Enable the Start Race button again
            }
        }
    }

    private boolean checkFinishLine() {
        // Check if any horse has crossed the finish line
        for (Horse horse : horses) {
            if (100 + horse.getDistanceTravelled() * 10 >= 900) {
                return true;
            }
        }
        return false;
    }

    private void highlightWinner() {
        // Find the winner
        Horse winner = null;
        int maxDistance = 0;
        for (Horse horse : horses) {
            if (horse.getDistanceTravelled() > maxDistance) {
                maxDistance = horse.getDistanceTravelled();
                winner = horse;
            }
        }


        JOptionPane.showMessageDialog(parentFrame, winner.getName() + " wins the race!");
    }

    public void startRaceWithCallback(Runnable callback) {
        timer.start();
        this.raceEndCallback = callback;
    }


}
