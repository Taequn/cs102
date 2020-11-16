import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

public class AScrollingGame extends GameCore {
    
    //---------------- Class Variables and Constants -----------------//
    
    // Constants for the various keyboard controls
    // use Java constant names for key presses
    // http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN   
    protected static final int KEY_SPEED_UP = KeyEvent.VK_EQUALS;
    protected static final int KEY_SLOW_DOWN = KeyEvent.VK_MINUS;
    protected static final int KEY_RESET_SPEED = KeyEvent.VK_R;

    protected static final int KEY_DEBUG = KeyEvent.VK_D;
    
    protected static final int KEY_MOVE_DOWN = KeyEvent.VK_DOWN;
    protected static final int KEY_MOVE_UP = KeyEvent.VK_UP;
    protected static final int KEY_MOVE_RIGHT = KeyEvent.VK_RIGHT;
    protected static final int KEY_MOVE_LEFT = KeyEvent.VK_LEFT;
    
    // ADD more
    
    // if needed use the following
    //   public static final int[] MOVE_KEYS = { KEY_MOVE_DOWN, 
    //                                         ... };
    
    // Default row location of player at beginning of the game
    private static final int DEFAULT_PLAYER_ROW = 4;
    private static int countScreen = 1;
    private static Location firstHP = new Location(1, 10);
    private static Location firstStar = new Location(1, 1);
    
    public static final int KEY_SCREENSHOT = KeyEvent.VK_S;

    private static final String INTRO_SCREEN = "ink.png";
    private static final String END_SCREEN = "gameover.jpg";

    private static final String STAR_IMG = "images/star.png";
    protected static String PLAYER_IMG = "images/char.png";
    protected static String AVOID_IMG = "images/avoid_1.png";
    protected static String GET_IMG = "images/get_1.png";
    protected static String[] getList = {"get_1.png",
            "get_2.png", "get_3.png", "get_4.png"
    };
    protected static String[] avoidList = {"avoid_1.png",
            "avoid_2.png", "avoid_3.png", "avoid_4.png"
    };
    protected static String[] playerList = {"char.png", "char1.png"};

    // ADD others for Avoid/Get items 
    // USE ArrayList when you have many similar items
    
    // use if you are interested in click interation
    protected Location clickCoord;
    
    // make sure to update it
    protected Location playerCoord;
    
    protected int score;
    protected int hits;
    protected boolean gameOver = false;
    protected boolean debug = false;
    protected ArrayList<Location> assets = new ArrayList<Location>();
  
    // ADD constructor(s) if needed
    
    public AScrollingGame(int grid_h, int grid_w){
        this(grid_h, grid_w, DEFAULT_TIMER_DELAY);
    }
    
    public AScrollingGame(int hdim, int wdim, int init_delay_ms) {
        super(hdim, wdim, init_delay_ms);        
    }
    public AScrollingGame() {
        super(DEFAULT_GRID_H, DEFAULT_GRID_W, DEFAULT_TIMER_DELAY);
    }
    
    
    // Perform the tasks of beginning the game
    //   - display still screen (until "Enter")
    //   - reset game params for game start
    protected void startGame(){
        updateTitle("Welcome to my game...");
        displayStillScreen(INTRO_SCREEN);

        // Consider below instead if you want multiple still screen
        /*
        ArrayList<String> splashImages = new ArrayList<String>();
        splashImages.add(INTRO_SCREEN);    
        splashImages.add(..); // another intro screen...
        
        for (String screen: splashImages)
        displayStillScreen(screen);
        */

        
        resetGamePlayParam();
    }
    
    
    
     protected void resetGamePlayParam() {
        //displayGameBackground("images/bg1.png");
        //setGameBackgroundColor(Color.BLUE);
        score = 0;
        hits = 0;
        updateTitle("Scrolling Game --- SCORE " + score + " ;hits " + hits);


        // store and initialize user position
        playerCoord = new Location(DEFAULT_PLAYER_ROW, 0, 1);
        setGridImage(playerCoord, "images/"+playerList[0]);


        for (int i=0; i<getTotalGridCols()-1; i++){
            if (i==11 || i==12 || i==13){
                Location loc = new Location(1, i);
                setGridImage(loc, "images/HP.png");
            }
        }

        //setGridImage(coordinateForHP, "images/HP.png");
        // Try the lines below
        //setGridColor(playerCoord, Color.BLUE);

        //debug = true;
        //hideGameBackground();
        System.out.println("debug mode" + debug + " grid lines shown");

    }
    
    //Call for methods that get updated every tick
    //Currently, it handles collisions + score/hits
    protected int performGameUpdates() {
        handleCollision();
        updateTitle("Scrolling Game --- SCORE " + score + "; hits " + hits);
        return handleKeyPress();
    }
    
    
    /****************** Methods to Implement Part I ******************/

    //Call methods that modify assets at each "render ticks"
    //Some assets move each "render ticks", new ones are created
    protected void performRenderUpdates(){
        bgScrolling();
        handleKeyPress();
        scroll();
        populate();
        endGame();
    }
    
    
    //Update game state with new assets 
    // such as adding in A/G images in the right-most column
    // WE GOTTA MAKE THESE METHODS SLOWER
    /**
     To-do list:
     1) Develop certain assets that move faster/slower than others
     2) Write a separate method for those.

     **/

    protected void populate() {
        //Making a random decision on what asset to use

        Location newPiece  = new Location(DICE.nextInt(getTotalGridRows()-2)+2,
                getTotalGridCols()-1, (DICE.nextInt(2)+2));

        //if the location is occupied, then we don't do anything
        for (int i=0; i<assets.size(); i++){
            if (newPiece.equals(assets.get(i)))
                return;
        }
        assets.add(newPiece);

        //randomize assets by their traits
        //Read Location.java for more info on traits
        for (int j=0; j<assets.size(); j++){
            Location loc = assets.get(j);

            if (loc.getTrait()==2){
                setGridImage(loc, "images/"+avoidList[loc.getPosition()]);
            }
            else if (loc.getTrait()==3){
                setGridImage(loc, "images/"+getList[loc.getPosition()]);
            }

        }

    }
    
    //Update game state with motions
    // such as scrolling items from left to right by one column
    protected void scroll() {

        for (int i=0; i<assets.size(); i++){
            Location loco = assets.get(i);
            if (loco.getCol() > 0) {
                movement(loco, 0, -1);
                handleCollision();
            } else {
                setGridImage(loco, null);
                assets.remove(i);
            }
        }
    }
    
    //Check for collision between user and assets
    protected void handleCollision() {
        for (int i=0; i<assets.size(); i++){
            Location loc = assets.get(i);
            if (loc.equals(playerCoord)){
                if (loc.getTrait()==2){
                    hits++;
                    regulateUI(true);
                }

                else if (loc.getTrait()==3) {
                    score += 10;
                    regulateUI(false);
                }
                setGridImage(loc, null);
                assets.remove(i);
                setGridImage(playerCoord, PLAYER_IMG);

            }
        }
    }

    protected void movement(Location loc, int r, int c){
        setGridImage(loc, null);
        loc.set(loc.getRow() + r, loc.getCol() + c);

        if (loc.getTrait()==1)
            setGridImage(loc, "images/"+playerList[0]);
        else if (loc.getTrait()==2)
            setGridImage(loc, AVOID_IMG);
        else if (loc.getTrait()==3)
            setGridImage(loc, GET_IMG);
        else if (loc.getTrait()==10)
            setGridImage(loc, "images/"+playerList[1]);
    }
    
    
    
    
    /****************** Methods to Complete Part I ******************/
    
    //Handle game player key press for game play

    /** TO-DO LIST:
     1) Check if we can arrange it in an ArrayList/LinkedList
     using the key numbers. It might make the code shorter
     and more clean.
     **/
    protected int handleKeyPress() {
        int key = super.handleKeyPress(); // delegate to parent window level keys
        if (key != GameGrid.NO_KEY) {
            System.out.println("A key #"+key+" has been pressed.");
        }

        if (key == KEY_MOVE_DOWN){
            if (playerCoord.getRow()+1<getTotalGridRows())
                movement(playerCoord, 1, 0);
        }
        else if (key == KEY_MOVE_UP){
            if (playerCoord.getRow()>2)
                movement(playerCoord,-1, 0);
        }
        else if (key == KEY_MOVE_LEFT){
            if (playerCoord.getCol()-1>=0) {
                playerCoord.setTrait(10);
                movement(playerCoord, 0, -1);
            }
        }
        else if (key == KEY_MOVE_RIGHT){
            if (playerCoord.getCol()+1<getTotalGridCols()){
                playerCoord.setTrait(1);
                movement(playerCoord, 0, 1);
            }
        }

        else if (key == KEY_DEBUG){
            displayGridLines();
        }

        //speed controls
        else if (key == KEY_SLOW_DOWN)
            super.slowDown(MIN_TIMER_DELAY);
        else if (key == KEY_SPEED_UP)
            super.speedUp(MIN_TIMER_DELAY);
        else if (key == KEY_RESET_SPEED)
            super.resetSpeed();




        
        return key;
    }

    protected void bgScrolling(){
        displayGameBackground("images/bg"+countScreen+".jpg");
        countScreen++;
        if (countScreen>20)
            countScreen=1;
    }

    //regulates UI and other in-game mechanics
    protected void regulateUI(boolean damage){
        if (hits>0 && damage)
            firstHP.set(1, firstHP.getCol()+1);
        if (score%100==0 && !damage)
            firstStar.set(1, firstStar.getCol()+1);

        if (score%20==0 && !damage)
            super.speedUp(MIN_TIMER_DELAY);

        setGridImage(firstHP,null);
        setGridImage(firstStar, STAR_IMG);

    }

    //contains all of the tasks that need to be done each time a game ends
    protected void endGame(){
    	if (hits==3 || score>250){
            updateTitle("GAME OVER");
            displayStillScreen("images/"+END_SCREEN);
            gameOver=true;
        }
    	
    	//minimum
        //updateTitle("GAME OVER ...
    }
    
    
    // return true if the game is finished, false otherwise
    protected boolean isGameOver() {
        return gameOver;
    }
    
  
    

    
}
