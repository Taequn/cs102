import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.Random;

//Represents an instance of a single Tetris Game
//is an extension of AbstractGame -- builds off the framework (ie. attributes,
//and methods) of AbstractGame to make a tetris-style game
public class TetrisGame extends AbstractGame {
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //filename of splash screen image
    protected static final String INTRO_SPLASH_IMAGE = "tetris_splash.png"; 
    
    //used anytime random numbers are needed
    //USE THIS! Don't create additional Random objects, just reuse this one!
    protected static final Random DICE = new Random();   
    
    //ints representing the change in x for moving left/right
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    
    //Determines how much the drop speed changes when inc/dec drop speed
    private static final int DROP_SPEED_FACTOR = 4;
    
    //amount of points awarded for clearing 0, 1, 2, 3, and 4 lines
    public static final int[] LINE_CLEAR_POINTS = {0, 40, 100, 300, 1200};
    
    // default number of vertical/horizontal cells: height/width of grid
    //Tetris board is 20 rows by 10 columns
    private static final int DEFAULT_GRID_H = 20;
    private static final int DEFAULT_GRID_W = 10;
    
    //the color completed rows change before they're cleared
    public static final Color COMPLETED_ROW_COLOR = Color.GRAY;
    
    //assignments for the various keyboard controls
    //use Java constant names for key presses
    //http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN   
    public static final int KEY_QUIT_GAME = KeyEvent.VK_Q;
    public static final int KEY_PAUSE_GAME = KeyEvent.VK_P;    
    public static final int KEY_SPEED_UP_DROP = KeyEvent.VK_DOWN;
    public static final int KEY_SLOW_DOWN_DROP = KeyEvent.VK_UP;
    public static final int KEY_ROTATE_PIECE = KeyEvent.VK_SPACE;
    public static final int KEY_SLIDE_LEFT = KeyEvent.VK_LEFT;
    public static final int KEY_SLIDE_RIGHT = KeyEvent.VK_RIGHT;
    
    
    
    //---------------- Instance Variables -----------------//    
    
    
    //Stores the active (ie, current "falling") tetromino.  If no piece is 
    //actively falling, this is null
    protected Tetromino activePiece;
    
    //The player's current score
    protected int score;
    
    //boolean determining if the game is over
    //game ends when board is filled and there's no space to generate next piece
    private boolean isGameOver = false;
    
    
    
    
    //---------------- Constructors -----------------//
    
    //uses default grid dimensions and speed (see 3 arg constructor)
    public TetrisGame() {
        this(DEFAULT_GRID_H, DEFAULT_GRID_W);
    }
    
    
    //uses provided grid dimensions and default speed (see 3 arg)
    public TetrisGame(int grid_h, int grid_w){
        this(grid_h, grid_w, DEFAULT_TIMER_DELAY);
    }
    
    
    //Creates game with provided grid dimensions and speed.
    //hdim/wdim: determines number of rows/columns on board, respectively
    //timerDelay: determines initial speed of game -- time between "ticks" (see game loop code for more)
    public TetrisGame(int hdim, int wdim, int timerDelay) {
        //Parent class initializes the basic game properties 
        //ex: the dimensions of game board, the game speed, etc
        super(hdim, wdim, timerDelay);
        
        //Initialize the TetrisGame-specific properties here
        grid.setLineColor(Color.BLACK); //draws black grid lines between blocks
        updateTitle(); //initializes title bar
    }
    
    
    
    
    //---------------- Instance Methods -----------------//
    
    //contains all of the tasks that need to be done each time a game is
    //started or reset
    protected void startGame(){
        this.score = 0; //reset score
        this.activePiece = null; //make sure there is no actively falling piece
        
        //displays the introduction screen until user presses enter
        //See parent class method for more info
        super.displaySplashScreen(INTRO_SPLASH_IMAGE);
    }
    
    
    //contains all of the tasks that need to be done each time a game ends
    protected void endGame(){
        grid.setTitle("GAME OVER | Score: " + getScore());
    }
    
    
    //Contains tasks that must be done on each game tick
    protected void performGameUpdates() {
        handleKeyPress();
    }
    
    
    //Contains tasks that are only done on "render ticks"
    protected void performRenderUpdates(){
        //If the game is currently paused
        if (isPaused)
            return;
        //If there's currently no falling piece, create one!
        else if (activePiece == null){
            clearCompletedRows();
            updateTitle();
            spawnNextTetromino();
        }
        //Check to see if falling piece has collided with something
        else if (activePiece.isDoneFalling(grid)){
            activePiece = null;
            currentTimerDelay = defaultTimerDelay;
            highlightCompletedRows(); //check if dropped piece completed a row
        }
        //Otherwise, drop the currently falling piece one more row
        else {
            activePiece.erase(grid);
            activePiece.dropOneRow();
            activePiece.paint(grid);
        }
    }
    
    
    //Erase any completed lines, and updates the score accordingly
    private void clearCompletedRows() {
        
        int rows = grid.getNumRows();
        int cols = grid.getNumCols();
        int rowsCleared = 0;
        for (int row = rows-1; row >=0; row--){     
            Location check = new Location(row, 0);
            //Completed rows have already been highlighted gray
            //so check the first cell in the row
            if (grid.getColor(check) == COMPLETED_ROW_COLOR){
                //we found a completed row, so we need to clear it!
                rowsCleared++; 
                grid.setColor(check, null);
                for (int col = 0; col < cols; col++){
                    check = new Location(row, col);
                    grid.setColor(check, null);
                }
            }
            else {
                //move blocks down appropriately
            }
        }
        
        //don't forget to update the score (hint, look at TetrisGame's final variables...)
    }
    
    
    
    //Highlight any completed rows gray
    private void highlightCompletedRows() {
        int rows = grid.getNumRows();
        int cols = grid.getNumCols();
        for (int row = 0; row < rows; row++){
            boolean isFull = true;
            for (int col = 0; col < cols; col++){
                Location check = new Location(row, col);
                if (grid.getColor(check) == null){
                    isFull = false;
                    break;
                }
            }
            if (isFull){
                for (int col = 0; col < cols; col++) {
                    Location check = new Location(row, col);
                    grid.setColor(check, COMPLETED_ROW_COLOR);
                }
            }


            //you highlight the row, so change its color to gray
        }
    }
    
    
    //Generates a new active (or "falling") tetromino piece at top of game board
    private void spawnNextTetromino(){
        
        activePiece = instantiateRandomTetromino();
        
        //center the piece on the board (taking into account piece's width)
        int startingCol = grid.getNumCols()/2 - (activePiece.getWidth()/2);
        
        //make sure piece fits on the board
        int startingRow = activePiece.getHeight()-1;
        activePiece.setBottomLeftLoc(new Location(startingRow, startingCol));
        
        //if piece spawns ontop of another piece, board is filled and its a GameOver
        if (!arePointsSafe(activePiece.getPoints(), activePiece.getBottomLeftLoc()))
            isGameOver = true;
        
        activePiece.paint(grid);
    }
    
    
    //reacts to user's keyboard input as the game is being played
    protected void handleKeyPress() {
        
        int key = grid.checkLastKeyPressed();
        
        
        if (key == KEY_QUIT_GAME)
            System.exit(0);
        else if (key == KEY_PAUSE_GAME)
            isPaused = !isPaused;        
        else if (key == KEY_SPEED_UP_DROP)
            currentTimerDelay = defaultTimerDelay / DROP_SPEED_FACTOR;
        else if (key == KEY_SLOW_DOWN_DROP)
            currentTimerDelay = defaultTimerDelay;
        else if (activePiece != null){
            if (key == KEY_ROTATE_PIECE && !isPaused){
                activePiece.erase(grid);                
                if (safeToRotate(activePiece))
                    activePiece.rotate();
                activePiece.paint(grid);
            }
            else if (key == KEY_SLIDE_LEFT && !isPaused){
                activePiece.erase(grid);
                if (safeToSlide(activePiece, LEFT))
                    activePiece.slide(LEFT);
                activePiece.paint(grid);      
            }
            else if (key == KEY_SLIDE_RIGHT && !isPaused){
                activePiece.erase(grid);
                if (safeToSlide(activePiece, RIGHT))
                    activePiece.slide(RIGHT);
                activePiece.paint(grid); 
            }                
        }
    }
    
    
    
    //Checks if the cells at the provided coordinates are "safe"
    //Cells are safe if there is no collision and are not out of bounds
    //Can optionally provide points offset by a bottom left location
    public boolean arePointsSafe(int[][] points){
        return arePointsSafe(points, new Location(0,0));        
    }
    
    public boolean arePointsSafe(int[][] points, Location bottomLeftLoc){
        
        int originRow = bottomLeftLoc.getRow();
        int originCol = bottomLeftLoc.getCol();
        
        for (int i = 0; i < points.length; i++){
            Location checkCollision = new Location(originRow - points[i][0], originCol + points[i][1]);
            //if point is outside bounds of window
            if (checkCollision.getRow() < 0 || checkCollision.getRow() >= grid.getNumRows())
                return false;            
            if (checkCollision.getCol() < 0 || checkCollision.getCol() >= grid.getNumCols())
                return false;
            //if point is ontop of another block
            if (grid.getColor(checkCollision) != null)
                return false;
        }
        return true;
    }
    
    
    
    //Checks if cells are safe to slide argument Tetromino left or right
    //Cells are safe if there is no collision and are not out of bounds
    private boolean safeToSlide(Tetromino piece, int dir){
        System.out.println("calling safeToSlide, dir = " + dir);
        Location currentBottomLeftLoc = piece.getBottomLeftLoc();
        int currentRow = currentBottomLeftLoc.getRow();
        int currentCol = currentBottomLeftLoc.getCol();
        Location newBottomLeftLoc = new Location(currentRow, currentCol + dir);
        return arePointsSafe(piece.getPoints(), newBottomLeftLoc);
    }
    
    
    //Checks if cells are safe to rotate argument Tetromino to next rotation
    //Cells are safe if there is no collision and are not out of bounds    
    private boolean safeToRotate(Tetromino piece){
        int[][] points = piece.getNextRotationPoints();
        Location bottomLeftLoc = piece.getBottomLeftLoc();
        return arePointsSafe(points, bottomLeftLoc);
    }
    
    
    //return the score of the game 
    private int getScore() {
        return score; 
    }
    
    
    //updates the title bar of the game window 
    private void updateTitle() {
        grid.setTitle("Tetris | Score:  " + getScore());
    }
    
    
    // return true if the game is finished, false otherwise
    protected boolean isGameOver() {
        return isGameOver;
    }
    
    
    
    
    //---------------- Class/Helper Functions -----------------// 
    
    
    //Instantiates and returns a random Tetromino piece
    private static Tetromino instantiateRandomTetromino(){
        int roll = DICE.nextInt(Tetromino.ALL_SHAPES.length);    
        switch (roll){
            case (Tetromino.T_SHAPE):
                return new ShapeT();
            case (Tetromino.SQUARE_SHAPE):
                return new ShapeSquare();
            case (Tetromino.STICK_SHAPE):
                return new ShapeStick();
            case (Tetromino.PERISCOPE_L_SHAPE):
                return new ShapePeriscopeL();
            case (Tetromino.PERISCOPE_R_SHAPE):
                return new ShapePeriscopeR();
            case (Tetromino.DOG_L_SHAPE):
                return new ShapeDogL();
            default:
                return new ShapeDogR();            
        }
        
    }  
    
}
