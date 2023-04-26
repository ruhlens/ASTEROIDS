/**
 * Bullet class for PA4.
 * @author Stephen Ruhlen
 * @version 4/9/2023
 */
public class Bullet extends GameElement {
    
    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20; 
    public static final int BULLET_DURATION = 20; 
    private int counter;
    
    /**
     * Constructor method.
     * @param pose the position
     */
    Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), BULLET_SPEED), BULLET_RADIUS);
    }
    
    /**
     * Update method for bullets. 
     */
    public void update() {
        if (counter == BULLET_DURATION) {
            this.setDestroyed(true);
        }
        counter += 1;
        double speed = this.velocity.getMagnitude();
        this.velocity = this.velocity.newMagnitude(speed);
        this.velocity = this.velocity.newHeading(this.getPose().getHeading());
        super.update();
    }
    
    /**
     * Draw method for bullets.
     */
    public void draw() {
        StdDraw.filledCircle(pose.getX(), pose.getY(), BULLET_RADIUS);  
    }
}
