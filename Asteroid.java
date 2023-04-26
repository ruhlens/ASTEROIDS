/**
 * Asteroid class for PA4.
 * @author Stephen Ruhlen
 * @version 4/13/2023
 */
public class Asteroid extends Enemy {
    
    public static final int ASTEROID_SPEED = 1;
    
    /**
     * Constructor for asteroid class.
     * @param size the size of the asteroid based on the AsteroidSize enum
     */
    Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }
    
    /**
     * Draw method for asteroid class.
     */
    public void draw() {
        StdDraw.circle(this.getPose().getX(), this.getPose().getY(), this.getRadius());
    }

}
