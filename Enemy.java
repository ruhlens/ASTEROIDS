/**
 * Enemy class for PA4.
 * @author Stephen Ruhlen
 * @version 4/13/2023
 */
public abstract class Enemy extends GameElement {
    
    protected int points;
    
    /**
     * Constructor for enemy class.
     * @param speed the speed of the enemy
     * @param radius the radius of the enemy
     * @param points the points of the enemy
     */
    Enemy(double speed, double radius, int points) {
        super(new Pose(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition(),
                AsteroidsGame.randomHeading()), new Vector2D(0, speed), radius);
        this.points = points;
        this.velocity = this.velocity.newHeading(this.getPose().getHeading());
    }
    
    /**
     * Getter method for the enemy points.
     * @return this.points the enemy points
     */
    public int getPoints() {
        return this.points;
    }
}
