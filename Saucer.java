/**
 * Saucer class for PA4. 
 * @author Stephen Ruhlen
 * @version 4/13/2023
 */
public class Saucer extends Enemy {
    
    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final int SAUCER_SPEED = 2;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = 0.002;
    public static final double TURN_PROB = 0.05;
    
    /**
     * Constructor method for saucer class.
     */
    Saucer() {
        super(SAUCER_SPEED, HALF_WIDTH, SAUCER_POINTS);
    }
    
    /**
     * Update method for saucer class.
     */
    public void update() {
        this.pose = this.pose.move(this.velocity);
        if (GameDriver.GENERATOR.nextInt(20) == 1) {
            double heading = (double) GameDriver.GENERATOR.nextDouble();
            Vector2D velocity1 = new Vector2D(heading, SAUCER_SPEED);
            this.velocity = this.velocity.add(velocity1);
        }
        double xDistance = GameDriver.SCREEN_WIDTH - this.pose.getX(); 
        double yDistance = GameDriver.SCREEN_HEIGHT - this.pose.getY();
        if (xDistance < 0 || xDistance > GameDriver.SCREEN_WIDTH
                || yDistance < 0 || yDistance > GameDriver.SCREEN_HEIGHT) {
            this.setDestroyed(true);
        }
    }
    
    /**
     * Draw method for saucer class.
     */
    public void draw() {
        StdDraw.rectangle(super.getPose().xPosition, super.getPose().yPosition, HALF_WIDTH, HALF_HEIGHT);
    }

}
