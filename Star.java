/**
 * Star Class for PA4.
 * @author Stephen Ruhlen
 * @version 3/30/2023
 */
public class Star implements Drawable {
    
    public static final int STAR_RADIUS = 1;
    private Point location;
    
    /**
     * Constructor Method for Star.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    Star(double x, double y) {
        this.location = new Point(x, y);
    }
    
    /**
     * Getter method for the location.
     * @return this.location the location of the object
     */
    public Point getLocation() {
        return this.location;
    }
    
    /**
     * Draw method.
     */
    public void draw() {
        StdDraw.filledCircle(this.location.getX(), this.location.getY(), STAR_RADIUS);
    }
}
