import java.util.ArrayList;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class AsteroidsGame implements Playable {
    
    public static final int LIVES = 3;

    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private NumericDisplay score;
    private NumericDisplay lives;
    private Ship ship = new Ship();

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");
        
        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        shipAndBullets = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(10, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(10, TOP - 40, "Lives: ", LIVES);
        drawElements.add(score);
        drawElements.add(lives);
        shipAndBullets.add(this.ship);
        drawElements.add(this.ship);
        updateElements.add(this.ship);

        // TODO
    }
    
    /** 
     * Add enemies to their appropriate lists.
     * @param enemy the enemy to add
     */
    public void addEnemy(Enemy enemy) {
        drawElements.add(enemy);
        updateElements.add(enemy);
        enemies.add(enemy);
    }
    
    /**
     * Spawn new enemies.
     */
    public void newEnemies() {
        for (int i = 0; i < 10; i++) {
            Asteroid a = new Asteroid(AsteroidSize.randomSize());
            addEnemy(a); 
        }
    }
    
    /**
     * Spawn a new ship.
     */
    public void newShip() {
        if (this.ship.destroyed) {
            if (this.lives.getValue() > 0) {
                Ship ship = new Ship();
                this.ship = ship;
                shipAndBullets.add(this.ship);
                drawElements.add(this.ship);
                updateElements.add(this.ship);
            }
        }
    }
    
    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        for (int x = 0; x < 100; x++) {
            Star star = new Star(randomXPosition(), randomYPosition());
            drawElements.add(star);
        }
    }
    
    /**
     * Starts a new game with 10 asteroids.
     */
    public void startGame() {
        newEnemies();
        newStars();
        draw();
    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets
     * if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {
        if (GameDriver.spacePressed()) {
            Bullet b = new Bullet(this.ship.getPose());
            shipAndBullets.add(b);
            updateElements.add(b);
            drawElements.add(b);
        }
        
        if (GameDriver.leftPressed()) {
            this.ship.turnLeft();
        }
        
        if (GameDriver.rightPressed()) {
            this.ship.turnRight();
        }
        
        if (GameDriver.upPressed()) {
            this.ship.thrust();
        }
    }
    
    /**
     * Update the objects on screen.
     */
    @Override
    public void update() {
        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;
        }
        
        // update everything (including newest bullet)
        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();
        }

        // TODO You will need these in Part 3
        handleCollisions();
        removeDestroyedBullets();
        removeDestroyedEnemies();

        // TODO Put your code here
        this.ship.update();
        newShip();
        
        if (GameDriver.GENERATOR.nextInt(500) == 1) {
            Saucer saucer = new Saucer();
            addEnemy(saucer);
        }
        
        if (enemies.size() == 0) {
            newEnemies();
        }
    }
    
    /**
     * Draw the objects on screen.
     */
    @Override
    public void draw() {
        // TODO
        StdDraw.setPenColor(StdDraw.WHITE);
        for (int x = 0; x < drawElements.size(); x++) {
            drawElements.get(x).draw();
        }
    }
    
    /**
     * Handle the collisions of all objects on screen.
     */
    public void handleCollisions() {
        for (Updatable element1 : updateElements) {
            for (Updatable element2 : updateElements) {
                if (element1 != element2) {
                    if (element1 instanceof GameElement && element2 instanceof GameElement) {
                        GameElement element1GE = (GameElement) element1;
                        GameElement element2GE = (GameElement) element2;
                        if (element1GE.checkCollision(element2GE)) {
                            if (element2GE instanceof Enemy) {
                                Enemy e2 = (Enemy) element2GE;
                                if (element1GE instanceof Bullet) {
                                    this.score.setValue(this.score.getValue() + e2.points);
                                    element1GE.setDestroyed(true);
                                    element2GE.setDestroyed(true);
                                } else if (element1GE instanceof Ship) {
                                    this.lives.setValue(this.lives.getValue() - 1);
                                    this.score.setValue(this.score.getValue() + e2.points);
                                    element1GE.setDestroyed(true);
                                    element2GE.setDestroyed(true);
                                }
                            }
                            
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Remove destroyed bullets.
     */
    public void removeDestroyedBullets() {
        int length = shipAndBullets.size();
        for (int i = 0; i < length; i++) {
            GameElement element = shipAndBullets.get(i);
            if (element.destroyed) {
                shipAndBullets.remove(element);
                drawElements.remove(element);
                updateElements.remove(element);
                length = shipAndBullets.size();
            }
        }
    }
    
    /**
     * Remove destroyed enemies.
     */
    public void removeDestroyedEnemies() {
        int length = enemies.size();
        for (int i = 0; i < length; i++) {
            GameElement element = enemies.get(i);
            if (element.destroyed) {
                enemies.remove(element);
                drawElements.remove(element);
                updateElements.remove(element);
                length = enemies.size();
            }
        }
    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}
