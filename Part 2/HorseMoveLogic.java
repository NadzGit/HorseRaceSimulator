//moves horses

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseMoveLogic extends JPanel implements ActionListener {
    private HorsePart2[] horses;
    private Timer timer;
    private int laneNum;
    private JPanel parentFrame;
    private Runnable raceEndCallback;
    private Race race = new Race(2, laneNum);
    private boolean raceFinished = false;
    private String weatherCondition;
    private int x;
    private int y;

    public HorseMoveLogic(int laneNum, HorsePart2[] horses, String weatherCondition) {
        this.laneNum = laneNum;
        this.weatherCondition = weatherCondition;
        this.horses = horses;
        timer = new Timer(100, this); // updates every 100ms
    }

    public void setUpHorseMove(JPanel frame) {
        this.parentFrame = frame;
    }

    public void startRace() {
        timer.start();
    }

    private boolean allFallen() {
        for (HorsePart2 h : horses) {
            if (!h.hasFallen()) return false;  // Not all horses have fallen
        }
        return true;  // All horses have fallen
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the lanes
        g.setColor(Color.BLACK);
        for (int i = 0; i < laneNum; i++) {
            int y = 500 + i * 40;
            g.drawLine(100, y, 900, y);
        }

        // Draw the horses
        for (int i = 0; i < horses.length; i++) {
            HorsePart2 horse = horses[i];
            setHorsePositionBasedOnWeather(horse, i);  // Handle horse position and confidence based on weather

            // Set horse color based on its breed or color
            Color horseColor = getHorseColor(horse);

            // Draw the horse's oval shape
            g.setColor(horseColor);
            g.fillOval(x, y - 15, 30, 30);
            g.drawOval(x, y - 15, 30, 30);

            // Set up the font and calculate the position of the text
            g.setColor(Color.BLACK);
            String symbol = String.valueOf(horse.getSymbol());
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(symbol);
            int textHeight = fm.getHeight();

            // Center the symbol horizontally and vertically in the oval
            int textX = x + (30 - textWidth) / 2;  // Center horizontally
            int textY = y - 15 + (30 + textHeight) / 2 - fm.getDescent();  // Center vertically

            // Draw the symbol centered inside the oval
            g.drawString(symbol, textX, textY);

            // Draw the horse name above the oval
            g.drawString(horse.getName(), x, y - 20); // show horse name above
        }

        // Check if all horses have fallen
        if (raceFinished && allFallen()) {
            g.drawString("All horses fell! Race is over.", 500, 100);
            raceFinished = true;
        }
    }

    private void setHorsePositionBasedOnWeather(HorsePart2 horse, int i) {
        // Set x and y based on weather condition
        switch (weatherCondition) {
            case "Dry":
                x = 100 + horse.getDistanceTravelled() * 10;
                y = 500 + i * 40;
                break;
            case "Wet":
                x = 100 + horse.getDistanceTravelled() * 7;
                y = 500 + i * 40;
                horse.setConfidence(horse.getConfidence() - 0.2);
                break;
            case "Hot":
                x = 100 + horse.getDistanceTravelled() * 6;
                y = 500 + i * 40;
                horse.setConfidence(horse.getConfidence() - 0.1);
                break;
        }
    }

    private Color getHorseColor(HorsePart2 horse) {
        Color horseColor = Color.BLACK;
        if (horse.getColour().equals("Brown")) {
            horseColor = new Color(150, 75, 0); // Brown color
        } else if (horse.getColour().equals("Black")) {
            horseColor = Color.BLACK;
        } else if (horse.getColour().equals("White")) {
            horseColor = Color.WHITE;
        }
        return horseColor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Move each horse based on their confidence and fall probability
        for (HorsePart2 horse : horses) {
            if (!horse.hasFallen()) {
                moveHorseBasedOnConfidence(horse);
            }
            if (allFallen()) {
                raceFinished = true;
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

    private void moveHorseBasedOnConfidence(HorsePart2 horse) {
        // Move horse forward based on confidence
        if (Math.random() < horse.getConfidence()) {
            horse.moveForward();
        }

        // Check if the horse falls (with a certain probability)
        if (Math.random() < (0.1 * horse.getConfidence() * horse.getConfidence())) {
            try {
                horse.fall();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            horse.setSymbol('Ⅹ');  // Symbol for fallen horse
        }
    }

    private boolean checkFinishLine() {
        for (HorsePart2 horse : horses) {
            if (100 + horse.getDistanceTravelled() * 10 >= 900) {
                return true;
            }
        }
        return false;
    }

    private void highlightWinner() {
        // Find the winner by the maximum distance
        HorsePart2 winner = null;
        int maxDistance = 0;
        for (HorsePart2 horse : horses) {
            if (horse.getDistanceTravelled() > maxDistance) {
                maxDistance = horse.getDistanceTravelled();
                winner = horse;
            }
        }

        // Show the winner
        JOptionPane.showMessageDialog(parentFrame, winner.getName() + " wins the race!");
    }

    public void startRaceWithCallback(Runnable callback) {
        timer.start();
        this.raceEndCallback = callback;
    }
}
