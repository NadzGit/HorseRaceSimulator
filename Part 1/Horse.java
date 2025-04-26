/**
 * Write a description of class Horse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Horse
{
    //Fields of class Horse
    private final String NAME;
    private char Symbol; // symbol representing horse (Unicode character)
    private int distance; //must be whole number
    private boolean fall;
    private double confidence;


    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
       this.fall = false;
       this.distance = 0;
       this.NAME = horseName;
       this.confidence = horseConfidence;
       this.Symbol = horseSymbol;

       if (horseConfidence <= 0.0) {
           this.confidence = 0.1;
       }
       else if (horseConfidence >= 1.0) {
           this.confidence = 0.9;
       }
    }
    
    
    
    //Other methods of class Horse
    public void fall() throws InterruptedException {
        this.fall = true;
    }

    
    public double getConfidence()
    {
        return this.confidence;
    }
    
    public int getDistanceTravelled()
    {
        return this.distance;
    }
    
    public String getName()
    {
        return this.NAME;
    }
    
    public char getSymbol()
    {
        return this.Symbol;
    }
    
    public void goBackToStart()
    {
        this.distance = 0;
    }
    
    public boolean hasFallen()
    {
        return this.fall;
    }

    public void moveForward()
    {
        this.distance += 1;
    }

    public void setConfidence(double newConfidence)
    {
        this.confidence = newConfidence;
    }
    
    public void setSymbol(char newSymbol)
    {
        this.Symbol = newSymbol;
    }
    
}
