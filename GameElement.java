/**
 * GameElement class for PA4.
 * @author Stephen Ruhlen
 * @version 3/30/2023
 */
public abstract class GameElement implements Drawable, Updatable {
    
    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed;
    
    /**
     * Constructor for GameElement class.
     * @param pose the pose object  
     * @param velocity the velocity of the pose object 
     * @param radius the radius of the object
     */
    GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose; 
        this.velocity = velocity;
        this.radius = radius;
    }
    
    /**
     * Getter method for the pose object.
     * @return this.pose the pose object
     */
    public Pose getPose() {
        return this.pose;
    }
    
    /**
     * Getter method for the velocity of the object.
     * @return this.velocity the velocity of the object 
     */
    public Vector2D getVelocity() {
        return this.velocity;
    }
    
    /**
     * Getter method for the radius of the object.
     * @return this.radius the radius of the object
     */
    public double getRadius() {
        return this.radius;
    }
    
    /**
     * Checks if the GameElement is destroyed.
     * @return boolean true or false
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }
    
    /**
     * Setter method for destroyed attribute.
     * @param destroyed the boolean value to set destroyed to
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    
    /**
     * Checks for collisions between GameElements.
     * @param other the GameElement to check for collisions with
     * @return result which is true or false
     */
    public boolean checkCollision(GameElement other) {
        double distance = Math.sqrt(Math.abs(Math.pow(this.getPose().getX() - other.getPose().getX(), 2)
                + Math.pow(this.getPose().getY() - other.getPose().getY(), 2)));
        double result = distance - (this.getRadius() + other.getRadius());
        return result < 0;
    }
    
    /**
     * Update the location of all GameElements on screen.
     */
    public void update() {
        this.pose = this.pose.move(this.velocity);
        double xDistance = GameDriver.SCREEN_WIDTH - this.pose.getX(); 
        double yDistance = GameDriver.SCREEN_HEIGHT - this.pose.getY();
        if (xDistance < 0) {
            this.pose =  this.pose.newX(0);
        }
        if (xDistance > GameDriver.SCREEN_WIDTH) {
            this.pose = this.pose.newX(GameDriver.SCREEN_WIDTH);
        }
        if (yDistance < 0) {
            this.pose = this.pose.newY(0);
        }
        if (yDistance > GameDriver.SCREEN_HEIGHT) {
            this.pose = this.pose.newY(GameDriver.SCREEN_HEIGHT);
        } 
    }
}
