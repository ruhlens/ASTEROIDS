/**
 * NumericDisplay class for PA4.
 * @author Stephen Ruhlen
 * @version 3/30/2023
 */
public class NumericDisplay implements Drawable {
    
    private String prefix; 
    private int value; 
    private Point location;
    
    /**
     * Constructor for NumericDisplay.
     * @param xPos the x coordinate 
     * @param yPos the y coordinate 
     * @param prefix the prefix for the display
     * @param value the value for the display
     */
    NumericDisplay(int xPos, int yPos, String prefix, int value) {
        this.location = new Point(xPos, yPos);
        this.prefix = prefix;
        this.value = value;
    }
    
    /**
     * Getter method for the location.
     * @return this.location the location of the object
     */
    public Point getLocation() {
        return this.location;
    }
    
    /**
     * Getter method for the value.
     * @return this.value the value for the object
     */
    public int getValue() {
        return this.value;
    }
    
    /**
     * Setter method for the value.
     * @param value the value to set this.value to
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    /**
     * Draw method.
     */
    public void draw() {
        StdDraw.textLeft(this.location.getX(), this.location.getY() , prefix + value);
    }

}
