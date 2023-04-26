/**
 * Ship class for PA4.
 * @author Stephen Ruhlen
 * @version 4/9/2023
 */
public class Ship extends GameElement {
    
    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = 0.1;
    public static final double SHIP_MOVE = 0.1;
    public static final double FRICTION = 0.02;
    
    /**
     * Constructor method.
     */
    Ship() {
        super(new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, 
                Math.PI / 2), new Vector2D(SHIP_TURN, 0), SHIP_WIDTH);
    }
    
    /**
     * Turns the ship to the left.
     */
    public void turnLeft() {
        this.pose = this.getPose().newHeading(this.getPose().getHeading() + SHIP_TURN);
    }
    
    /**
     * Turns the ship to the right.
     */
    public void turnRight() {
        this.pose =  this.getPose().newHeading(this.getPose().getHeading() - SHIP_TURN);  
    }
    
    /**
     * Thrusts the ship forward.
     */
    public void thrust() {
        Vector2D velocity1 = new Vector2D(this.getPose().getHeading(), SHIP_MOVE);
        this.velocity = this.velocity.add(velocity1);
    }
    
    /**
     * Update the ship.
     */
    public void update() {
        super.update();
        double speed = this.velocity.getMagnitude() - FRICTION;
        
        if (speed < 0) {
            speed = 0;
        }
        this.velocity = this.velocity.newMagnitude(speed);
        this.velocity = this.velocity.newHeading(this.getPose().getHeading());
    }
    
    /**
     * Draw the ship.
     */
    public void draw() {
        GameUtils.drawPoseAsTriangle(super.getPose(), SHIP_WIDTH, SHIP_HEIGHT);
    }

}
