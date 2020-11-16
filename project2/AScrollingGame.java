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
    
    private static final int PLAYER_TRAIT_RIGHT=1;
    private static final int PLAYER_TRAIT_LEFT=10;
    private static final int ASSET_TRAIT_BAD=2;
    private static final int ASSET_TRAIT_GOOD=3;
    private static final int TOP_BORDER=2;
    private static final int BG_LIMIT=20;
    private static final int STAR_FACTOR=100;
    private static final int STAR_SPEED_UP=20;
    private static final int WIN_COND=250;
    private static final int LOSE_COND=3;
    private static final int DEFAULT_SCORE_ADD=10;

    
    // Default row location of player at beginning of the game
    private static final int DEFAULT_PLAYER_ROW = 4;
    private static int countScreen = 1;
    private static int currentScreenshotCounter = 0;
    private static Location firstHP = new Location(1, 10);
    private static Location firstStar = new Location(1, 1);
    
    public static final int KEY_SCREENSHOT = KeyEvent.VK_S;

    private static final String INTRO_SCREEN = "ink.png";
    private static final String[] END_SCREENS = {"gameover.png",
        "gameover1.png"};

    private static final String DIRECTORY_LOC = "images/";
    private static final String STAR_IMG = DIRECTORY_LOC+"star.png";
    protected static String PLAYER_IMG = DIRECTORY_LOC+"char.png";
    protected static String AVOID_IMG = DIRECTORY_LOC+"avoid_1.png";
    protected static String GET_IMG = DIRECTORY_LOC+"get_1.png";
    protected static final String HP_IMG= DIRECTORY_LOC+"HP.png";
    protected static final String[] getList = {"get_1.png",
            "get_2.png", "get_3.png", "get_4.png"
    };
    protected static final String[] avoidList = {"avoid_1.png",
            "avoid_2.png", "avoid_3.png", "avoid_4.png"
    };
    protected static final String[] playerList = {"char.png", "char1.png"};

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
        setGridImage(playerCoord, DIRECTORY_LOC+playerList[0]);

        //put hearts into the UI
        for (int i=0; i<getTotalGridCols()-1; i++){
            if (i==11 || i==12 || i==13){
                Location loc = new Location(1, i);
                setGridImage(loc, HP_IMG);
            }
        }

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

    protected void populate() {
        //Making a random decision on what asset to use

        Location newPiece  = new Location(DICE.nextInt(getTotalGridRows()-2)+TOP_BORDER,
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

            if (loc.getTrait()==ASSET_TRAIT_BAD){
                setGridImage(loc, DIRECTORY_LOC+avoidList[loc.getPosition()]);
            }
            else if (loc.getTrait()==ASSET_TRAIT_GOOD){
                setGridImage(loc, DIRECTORY_LOC+getList[loc.getPosition()]);
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
    
    protected void handleCollision() {
        for (int i=0; i<assets.size(); i++){
            Location loc = assets.get(i);
            if (loc.equals(playerCoord)){
                if (loc.getTrait()==ASSET_TRAIT_BAD){
                    hits++;
                    regulateUI(true);
                }

                else if (loc.getTrait()==ASSET_TRAIT_GOOD) {
                    score += DEFAULT_SCORE_ADD;
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

        if (loc.getTrait()==PLAYER_TRAIT_RIGHT)
            setGridImage(loc, DIRECTORY_LOC+playerList[0]);
        else if (loc.getTrait()==ASSET_TRAIT_BAD)
            setGridImage(loc, AVOID_IMG);
        else if (loc.getTrait()==ASSET_TRAIT_GOOD)
            setGridImage(loc, GET_IMG);
        else if (loc.getTrait()==PLAYER_TRAIT_LEFT)
            setGridImage(loc, DIRECTORY_LOC+playerList[1]);
    }
    
    
    
    
    /****************** Methods to Complete Part I ******************/
    
    //Handle game player key press for game play

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
            if (playerCoord.getRow()>TOP_BORDER)
                movement(playerCoord,-1, 0);
        }
        else if (key == KEY_MOVE_LEFT){
            if (playerCoord.getCol()-1>=0) {
                playerCoord.setTrait(PLAYER_TRAIT_LEFT);
                movement(playerCoord, 0, -1);
            }
        }
        else if (key == KEY_MOVE_RIGHT){
            if (playerCoord.getCol()+1<getTotalGridCols()){
                playerCoord.setTrait(PLAYER_TRAIT_RIGHT);
                movement(playerCoord, 0, 1);
            }
        }

        else if (key == KEY_DEBUG)
            displayGridLines();
        else if (key == KEY_SCREENSHOT) {
            super.takeScreenShot("screenshot"+(++currentScreenshotCounter)+".jpg");
            if (currentScreenshotCounter==9)
                currentScreenshotCounter=0;
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

    //method scrolls background and seemlessly loops it
    protected void bgScrolling(){
        displayGameBackground(DIRECTORY_LOC+"bg"+countScreen+".jpg");
        if (++countScreen>BG_LIMIT)
            countScreen=1;
    }

    //regulates UI and other in-game mechanics
    protected void regulateUI(boolean damage){
        if (hits>0 && damage)
            firstHP.set(1, firstHP.getCol()+1);
        if (score%STAR_FACTOR==0 && !damage)
            firstStar.set(1, firstStar.getCol()+1);

        if (score%STAR_SPEED_UP==0 && !damage)
            super.speedUp(MIN_TIMER_DELAY);

        setGridImage(firstHP,null);
        setGridImage(firstStar, STAR_IMG);

    }

    //contains all of the tasks that need to be done each time a game ends
    protected void endGame(){
    	if (hits>=LOSE_COND){
            updateTitle("YOU LOST! Your score: " + score + ". You had to get: " + WIN_COND);
            displayStillScreen(DIRECTORY_LOC+END_SCREENS[0]);
            gameOver=true;
        }
    	else if (score>WIN_COND){
            updateTitle("YOU WON!");
            displayStillScreen(DIRECTORY_LOC+END_SCREENS[1]);
            gameOver=true;
        }
    }
    
    
    // return true if the game is finished, false otherwise
    protected boolean isGameOver() {
        return gameOver;
    }
    
  
    

    
}
