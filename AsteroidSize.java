import java.util.HashMap;

/**
 * AsteroidSize enum for PA4.
 * @author Stephen Ruhlen
 * @version 4/13/2023
 */
public enum AsteroidSize {
    
    SMALL(10, 200), MEDIUM(20, 100), LARGE(30, 50);
    private int radius;
    private int points;
    
    /**
     * Constructor for asteroidsize enum.
     * @param radius the radius of the asteroid
     * @param points the points of the asteroid
     */
    AsteroidSize(int radius, int points) {
        this.radius = radius;
        this.points = points;
    }
    
    /**
     * Getter method for asteroid radius.
     * @return this.radius the radius of the asteroid
     */
    public int getRadius() {
        return this.radius;
    }
    
    /**
     * Getter method for asteroid points.
     * @return this.points the points of the asteroid
     */
    public int getPoints() {
        return this.points;
    }
    
    /**
     * Randomsize method for asteroid points returns a random size.
     * @return a random sized asteroid based on given probabilities
     */
    public static AsteroidSize randomSize() {
        HashMap<Integer, AsteroidSize> sizes = new HashMap<Integer, AsteroidSize>();
        sizes.put(0, SMALL);
        sizes.put(1, MEDIUM);
        sizes.put(2, LARGE);
        sizes.put(3, LARGE);
        return sizes.get(GameDriver.GENERATOR.nextInt(4));
    }
}
