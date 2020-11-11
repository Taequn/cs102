import java.awt.event.KeyEvent;
import java.awt.Color;


//Abstract parent Game class - contains general game properties and acts as the 
//"framework" of our game engine, more specifc methods/attributes go in children 
public abstract class AbstractGame {
    
    
    
    //---------------- Class Variables and Constants -----------------//
    
    
    //The initial timer delay -- the number of milliseconds between each
    //"tick" in the game loop.  See play() and sleep() for more info.
    protected static final int DEFAULT_TIMER_DELAY = 100;
    
    //the keyboard key used to advance past a splash screen
    private static final int KEY_ADVANCE_SPLASH_SCREEN = KeyEvent.VK_ENTER;
    
    //initial value for factor (see "factor" instance variable below
    private static final int STARTING_FACTOR = 3;
    
    //---------------- Instance Variables -----------------//
    
    //The grid that represents our game "board" (i.e. the window that the game
    //is rendered in) -- essentially a matrix of cells (see GameGrid code)
    protected GameGrid grid;
    
    //Game clock -- milliseconds elapsed since game started (see play()) 
    protected long msElapsed;  
    
    //tracks total number of "ticks" since game started (see play())
    protected int ticksElapsed; 
    
    //Controls game speed (ms between game "ticks"); retain a default in case we speed up/slow down game
    protected int currentTimerDelay;  
    protected int defaultTimerDelay;
    
    //determines how frequently (ie. once every "factor" number of game ticks)
    //the game board is updated, meaning redrawn or repainted.
    protected int factor = STARTING_FACTOR;
    
    //determines if the game is paused or not
    protected boolean isPaused = false;
    
    
    //---------------- Constructor -----------------//
    
    //arguments provided by child class, no background image (see 4 arg constructor)
    public AbstractGame(int hdim, int wdim, int timerDelay) {
        this(hdim, wdim, timerDelay, null);
    }
    
    
    //Initializes general game properties
    //hdim/wdim: determines number of rows/columns on board, respectively
    //timerDelay: determines initial speed of game -- time between "ticks" (see game loop code for more)
    //backgroundImg: filename of image to be used as background for the game window
    public AbstractGame(int hdim, int wdim, int timerDelay, String backgroundImg) {
        //initialize grid
        this.grid = new GameGrid(hdim, wdim, backgroundImg);
        //Initialize attributes and counters
        this.currentTimerDelay = timerDelay;
        this.defaultTimerDelay = timerDelay;
        this.ticksElapsed = 0;
        this.msElapsed = 0;
    }    
    
    
    
    //---------------- Instance Methods -----------------//
    
    //Runs the game, including pre and post game tasks
    public void run(){     
        startGame(); 
        gameLoop();  
        endGame();   
    }
    
    
    
    
    //Sets the background of the game window to the specified image file, or null for no background image
    public void setBackgroundImage(String backgroundImg){
        grid.setGridBackgroundImage(backgroundImg);        
    }
    
    
    
    //Sets the background color of the game window to the specified Color, or null for no background color
    public void setBackgroundColor(Color backgroundColor){
        grid.setGridBackgroundColor(backgroundColor);    
    }
    
    
    
    //the main loop that handles the execution of the game
    private void gameLoop() {
        //Continue looping until the game is over
        //Each iteration of this loop represents one game "tick"
        while (!isGameOver()){            
            this.sleep(currentTimerDelay); 
            
            //Some tasks get performed on every tick of the game loop
            //and some only get performed on "render ticks"... trace the methods!
            performGameUpdates();
            if (isRenderTick())
                performRenderUpdates();
            
            //don't update accumulators if the game is paused
            if (!isPaused){
                msElapsed += currentTimerDelay;
                ticksElapsed++;
            } 
        }   
    }
    
    
    //determines if the current tick is a "render tick" or not...
    private boolean isRenderTick(){
        return (ticksElapsed % factor == 0);
    }
    
    
    
    // Pauses the execution of the code for the argument number of milliseconds
    // Essential in the game loop!
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } 
        catch(Exception e) { 
            //shouldn't ever reach here, but try/catch is necessary due to 
            //Java's implementation of Thread.sleep function
        }
    }
    
    
    //Displays splash screen, uses default advance key (see 2 argument version below)
    protected void displaySplashScreen(String splashImage){
        displaySplashScreen(splashImage, KEY_ADVANCE_SPLASH_SCREEN);
    }
    
    
    
    //Displays a splash screen image covering the entirety of the board, until user presses the argument key to advance.
    //splashImage is file name of image to be displayed
    //splashScreenAdvanceKey is the keyboard key user must press to continue
    protected void displaySplashScreen(String splashImage, int splashScreenAdvanceKey){
        
        grid.setSplashScreen(splashImage);
        
        while (grid.checkLastKeyPressed() != KEY_ADVANCE_SPLASH_SCREEN) {
            //A call to sleep() is necessary here to slow down execution of the 
            //game, otherwise the user's key press won't be recognized!
            sleep(currentTimerDelay);
        }
        
        grid.setSplashScreen(null); 
    }
    
    
    
    
    //---------------- Abstract methods -----------------//
    //        (to be implemented in the child!)
    
    
    //checks to see if the game is over
    protected abstract boolean isGameOver();
    
    //reacts to the user's input
    protected abstract void handleKeyPress();
    
    //handles all of the tasks done on each tick
    protected abstract void performGameUpdates();
    //handles all of the tasks done only on each "render tick"
    protected abstract void performRenderUpdates();
    
    //contains all of the tasks that need to be done when game starts/ends
    protected abstract void startGame();
    protected abstract void endGame();
    
    
    
    
    
}
