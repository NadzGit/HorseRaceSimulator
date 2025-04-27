// Moves horses

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
    private boolean raceFinished = false;
    private String weatherCondition;
    private String equipment;
    private BettingLogic bettingLogic;


    public HorseMoveLogic(int laneNum, HorsePart2[] horses, String weatherCondition, String equipment, BettingLogic bettingLogic) {
        this.laneNum = laneNum;
        this.weatherCondition = weatherCondition;
        this.horses = horses;
        this.equipment = equipment;
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
            if (!h.hasFallen()) return false;
        }
        return true;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the lanes
        g.setColor(Color.BLACK);
        for (int i = 0; i < laneNum; i++) {
            int yLane = 500 + i * 40;
            g.drawLine(100, yLane, 900, yLane);
        }

        // Draw the horses
        for (int i = 0; i < horses.length; i++) {
            HorsePart2 horse = horses[i];
            int[] position = getHorsePositionBasedOnWeather(horse, i);
            int horseX = position[0];
            int horseY = position[1];

            // Set horse color
            Color horseColor = getHorseColor(horse);

            // Draw horse body
            g.setColor(horseColor);
            g.fillOval(horseX, horseY - 15, 30, 30);
            g.drawOval(horseX, horseY - 15, 30, 30);

            // Draw horse symbol
            g.setColor(Color.BLACK);
            String symbol = String.valueOf(horse.getSymbol());
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(symbol);
            int textHeight = fm.getHeight();
            int textX = horseX + (30 - textWidth) / 2;
            int textY = horseY - 15 + (30 + textHeight) / 2 - fm.getDescent();
            g.drawString(symbol, textX, textY);

            // Draw horse name
            g.drawString(horse.getName(), horseX, horseY - 20);
        }

        if (raceFinished && allFallen()) {
            g.drawString("All horses fell! Race is over.", 500, 100);
        }
    }

    private int[] getHorsePositionBasedOnWeather(HorsePart2 horse, int i) {
        int scaling = 10; // Default Dry scaling
    if (horse != null) {
        if (weatherCondition != null) {
            switch (weatherCondition) {
                case "Wet":
                    scaling = 7;
                    horse.setConfidence(horse.getConfidence() - 0.2);
                    break;
                case "Hot":
                    scaling = 6;
                    horse.setConfidence(horse.getConfidence() - 0.1);
                    break;
            }
        }
        if (equipment != null) {
            switch (equipment) {
                case "Saddle":
                    scaling = 5;
                    horse.setConfidence(horse.getConfidence() + 0.1);
                    break;
                case "Cool Hat":
                    scaling = 7;
                    horse.setConfidence(horse.getConfidence() + 0.105);
                    break;
                case "Gold Crown":
                    scaling = 4;
                    horse.setConfidence(horse.getConfidence() + 0.25);
                    break;
                case "Bridle":
                    scaling = 3;
                    horse.setConfidence(horse.getConfidence() + 0.2);
            }
        }
    }

        int x = Math.max(0, 100 + horse.getDistanceTravelled() * scaling);
        int y = Math.max(0, 500 + i * 40);

        return new int[]{x, y};
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

        repaint(); // Redraw the screen with updated positions
        raceFinished = checkFinishLine();

        if (raceFinished) {
            timer.stop();
            highlightWinner();

            if (raceEndCallback != null) {
                raceEndCallback.run();
            }
        }
    }

    private void moveHorseBasedOnConfidence(HorsePart2 horse) {
        // Move horse forward based on confidence
        if (Math.random() < horse.getConfidence()) {
            horse.moveForward();
        }

        // Check if the horse falls
        if (Math.random() < (0.1 * horse.getConfidence() * horse.getConfidence())) {
            try {
                horse.fall();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            horse.setSymbol('Ⅹ'); // Symbol for fallen horse
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


    private String highlightWinner() {
        // Find the winner
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
        if (bettingLogic != null) {
            bettingLogic.processRaceResult(winner.getName());
        }
        return winner.getName();
    }



    public void startRaceWithCallback(Runnable callback) {
        timer.start();
        this.raceEndCallback = callback;
    }
}
