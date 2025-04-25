import java.util.Scanner;
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
    private String colour;


    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        Scanner scanner = new Scanner(System.in);
       this.fall = false;
       this.distance = 0;
       this.NAME = horseName;
       this.confidence = horseConfidence;
       this.Symbol = horseSymbol;

       if (horseConfidence > 1.0 || horseConfidence < 0.0)  {
           System.out.println("Invalid Horse Confidence");
       }
       while ((this.confidence < 0.0 || this.confidence > 1.0)){
       System.out.println("Confidence must be in between 0.0 and 1.0 inclusive.");
            System.out.println("Confidence of " + this.NAME + " is currently: " + this.confidence + " (invalid)");
            this.confidence = Double.parseDouble(scanner.nextLine());
       }
    }
    
    
    
    //Other methods of class Horse
    public void fall() throws InterruptedException {
        this.fall = true;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour(){
        return colour;
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
