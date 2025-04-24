import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.Collections;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author McRaceface
 * @version 1.0
 */
public class Race
{
    private int raceLength;
    private ArrayList<Horse> lanes;


    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int laneCount)
    {
        // initialise instance variables
        raceLength = distance;
        lanes = new ArrayList<Horse>(Collections.nCopies(laneCount, null));
    }


    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber >= 1 && laneNumber <= lanes.size()) {
            lanes.set(laneNumber - 1, theHorse);
        } else {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane.");
        }
    }






    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace() throws InterruptedException {
        boolean finished = false;

        for (Horse h : lanes) {
            if (h != null) h.goBackToStart();
        }

        while (!finished) {
            for (Horse h : lanes) {
                if (h != null) moveHorse(h);
            }

            printRace();

            // Check for win
            for (Horse h : lanes) {
                if (h != null && raceWonBy(h)) {
                    finished = true;
                    updateConfidence(h);
                    System.out.println("And the winner is..." + h.getName() + "!");
                    break;
                }
            }

            if (!finished && allFallen()) {
                System.out.println("All horses fell! Race is over.");
                finished = true;
            }

            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
    private void updateConfidence(Horse winner) {
        for (Horse h : lanes) {
            if (h == null) continue;
            if (h == winner) {
                h.setConfidence(h.getConfidence() * 1.1);
            } else {
                h.setConfidence(h.getConfidence() * 0.9);
            }
        }
    }

    private boolean allFallen() {
        for (Horse h : lanes) {
            if (h != null && !h.hasFallen()) return false;
        }
        return true;
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse) throws InterruptedException {
        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                theHorse.setSymbol('Ⅹ');
                System.out.println(theHorse.getSymbol());
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        return theHorse.getDistanceTravelled() == raceLength;
    }

    /***
     * Print the race on the terminal
     */
    private void printRace() {
        System.out.print('\u000C');  // clear screen
        multiplePrint('=', raceLength + 3);
        System.out.println();

        for (Horse h : lanes) {
            if (h != null) {
                printLane(h);
            } else {
                System.out.println("|" + " ".repeat(raceLength) + "|     (Empty Lane)");
            }
            System.out.println();
        }

        multiplePrint('=', raceLength + 3);
        System.out.println();
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse) {
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        //print a | for the beginning of the lane
        System.out.print('|');

        //print the spaces before the horse
        multiplePrint(' ', spacesBefore);


        System.out.print(theHorse.getSymbol());
//
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|' + "     " +  (theHorse.getName()).toUpperCase(Locale.ROOT) + " (Current Confidence "
                + theHorse.getConfidence() + ".)");
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
