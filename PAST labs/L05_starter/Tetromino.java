import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//A Tetromino is a single Tetris piece on the game board.  
//They come in various varieties (ie shapes), as implemented in children classes 
public abstract class Tetromino{
    
    //---------------- Class Variables and Constants -----------------//
    
    //Number of blocks that make up each Tetromino piece
    public static final int BLOCKS_PER_TETROMINO = 4;
    
    //ints representing the different varieties of tetromino pieces
    //used in random generation of pieces
    public static final int SQUARE_SHAPE = 0;
    public static final int STICK_SHAPE = 1;
    public static final int PERISCOPE_L_SHAPE = 2;
    public static final int PERISCOPE_R_SHAPE = 3;
    public static final int DOG_L_SHAPE = 4;
    public static final int DOG_R_SHAPE = 5;
    public static final int T_SHAPE = 6;

    //an array containing each of the ints above
    public static final int[] ALL_SHAPES = {SQUARE_SHAPE, STICK_SHAPE, PERISCOPE_L_SHAPE, PERISCOPE_R_SHAPE,
        DOG_L_SHAPE, DOG_R_SHAPE, T_SHAPE};
    
    
    
    //---------------- Instance Variables -----------------//
    
    //color of the tetromino (see Java Color API page)
    private Color pieceColor;
    
    //location object of the bottom left corner of the tetromino on the board
    private Location bottomLeftLoc;
    //height and width of the tetromino
    private int height, width;
    
    //an array containing the coordinate data for all possible rotations for
    //the tetromino piece
    private int[][][] rotations;
    //tracks how the tetromino piece is currently rotated
    private int currentRotation;
    //helper data used in determining collisions
    private int[] skirt;
    
    
    
    //---------------- Constructors -----------------//
    
    //uses provided points data and a random color (see 2 arg constructor)
    //One of the shapes calls that constructor

    public Tetromino(String rawPoints){
        this(rawPoints, getRandomColor());
    }
    
    
    //Creates tetromino with provided points data and color
    //rawPoints is String containing data of shapes' layout (see child classes) 
    //Color specifying color of tetromino
    public Tetromino(String rawPoints, Color pieceColor){
        
        //Parse the String of points data into a more usable (array) format
        this.rotations = parseRawPoints(rawPoints);
        
        //Set the attributes of the tetromino
        this.pieceColor = pieceColor;
        this.currentRotation = 0;
        this.height = calcHeight(this.rotations[currentRotation]);
        this.width = calcWidth(this.rotations[currentRotation]);
        this.skirt = calcSkirt(this.rotations[currentRotation]);
    }
    
    
    
    
    //---------------- Instance Methods -----------------//
    
    
    //Rotates this tetromino to the next rotation (90 degrees counter-clockwise)
    public void rotate(){ 
        
        //wrap around in case piece has been completely rotated
        this.currentRotation = (this.currentRotation + 1) % rotations.length;
        
        //update attributes
        this.height = calcHeight(getPoints());
        this.width = calcWidth(getPoints());
        this.skirt = calcSkirt(getPoints());        
    }
    
    
    //Returns an array of the coordinate data of the Tetromino's current state
    public int[][] getPoints(){
        return rotations[currentRotation];
    }
    
    
    //Returns an array of the coordinate data of the Tetromino's next rotation
    public int[][] getNextRotationPoints(){
        //wrap around in case piece has been completely rotated back to original
        return rotations[(currentRotation + 1) % rotations.length];
    }
    
    
    //Moves the tetromino piece horizontally left or right
    public void slide(int dir){
        this.bottomLeftLoc = new Location(bottomLeftLoc.getRow(), bottomLeftLoc.getCol() + dir);
    }
    
    
    //Returns the width of the tetromino piece   
    public int getWidth(){
        return width;
    }
    
    
    //paints the Tetromino piece to the argument game grid
    public void paint(GameGrid grid){
        
        // determine the placement of the piece on the grid
        int originRow = bottomLeftLoc.getRow();
        int originCol = bottomLeftLoc.getCol();
        int[][] points = this.getPoints();
        
        //color in the appropriate cells of the grid based on the piece's color
        for (int i = 0; i < points.length; i++){
            Location toColor = new Location(originRow - points[i][0], originCol + points[i][1]);
            grid.setColor(toColor, pieceColor);
        }
    }
    
    
    //erases the Tetromino piece from the argument game grid
    public void erase(GameGrid grid){
        
        // determine the current placement of the piece on the grid
        int originRow = bottomLeftLoc.getRow();
        int originCol = bottomLeftLoc.getCol();
        int[][] points = this.getPoints();
        
        //erase every cell of each of the piece's blocks from the grid
        for (int i = 0; i < points.length; i++){
            Location toColor = new Location(originRow - points[i][0], originCol + points[i][1]);
            grid.setColor(toColor, null);
        }
    } 
    
    
    //lowers the piece's location vertically one row (used for falling pieces)
    public void dropOneRow(){
        this.bottomLeftLoc = new Location(this.bottomLeftLoc.getRow()+1, this.bottomLeftLoc.getCol());
    }
    
    
    //Returns Location of the bottom left corner of the tetromino on the board
    public Location getBottomLeftLoc(){
        return bottomLeftLoc;
    }
    
    
    //Sets Location of the bottom left corner of the piece to the argument value
    public void setBottomLeftLoc(Location newLoc){
        this.bottomLeftLoc = newLoc;
    }
    
    
    //returns the height of the Tetromino piece
    public int getHeight(){
        return height; 
    }
    
    
    //returns String of piece data, including location and coords
    public String toString(){
        return "Piece at " + bottomLeftLoc + ", points = " + Arrays.deepToString(rotations[currentRotation]);
    }
    
    
    //Checks to see if the piece is done falling -- in other words, whether any 
    //of its blocks has collided with and is resting ontop of another piece
    public boolean isDoneFalling(GameGrid grid){
        int originRow = bottomLeftLoc.getRow();
        int originCol = bottomLeftLoc.getCol();
        for (int i = 0; i <skirt.length; i++){
            if (originRow - skirt[i] +1 >= grid.getNumRows())
                return true;
            Location toCheck = new Location(originRow - skirt[i] + 1, originCol + i);
            if (grid.getColor(toCheck) != null)
                return true;
        }
        return false;
    }
    
    
    
    
    //---------------- Class/Helper Functions -----------------//    
    
    //Calculates the skirt data, used to help determine collisions
    private static int[] calcSkirt(int[][] points){        
        int[] skirt = new int[calcWidth(points)];
        int height = calcHeight(points);
        for (int i = 0; i < skirt.length; i++)
            skirt[i] = height;  
        for (int i = 0; i < points.length; i++)
            skirt[points[i][1]] = Math.min(skirt[points[i][1]], points[i][0]);
        return skirt;
    }
    
    
    //Calculates the height of the piece
    private static int calcHeight(int[][] points){
        int height = 0;
        //finds the tallest point in each "column" of the piece's blocks
        for(int i = 0; i < points.length; i++)
            height = Math.max(height, points[i][0]);
        height += 1;
        return height;
    }
    
    
    //Calculates the width of the piece
    private static int calcWidth(int[][] points){
        int width = 0;
        //finds the widest point in each "row" of the piece's blocks
        for(int i = 0; i < points.length; i++)
            width = Math.max(width, points[i][1]);
        width += 1;
        return width;
    }   
    
    
    //Parses a String containing the raw points data (see Tetromino child 
    //classes for example) into arrays of ints... more convenient for what we 
    //need to use them for.
    private static int[][][] parseRawPoints(String rawPoints){
        Scanner points = new Scanner(rawPoints);
        int numOfRotations = rawPoints.split(" ").length / (BLOCKS_PER_TETROMINO * 2);
        int[][][] rotationArray = new int[numOfRotations][BLOCKS_PER_TETROMINO][2];
        for (int rotation = 0; rotation < numOfRotations; rotation++){
            for (int block = 0; block < BLOCKS_PER_TETROMINO; block++){
                for (int coord = 0; coord < 2; coord++){
                    rotationArray[rotation][block][coord] = points.nextInt(); 
                }
            }
        }
        return rotationArray;
    } 
    
    
    //Returns a Color object of a random color
    public static Color getRandomColor() {
        
        int r = TetrisGame.DICE.nextInt(255);
        int g = TetrisGame.DICE.nextInt(255);
        int b = TetrisGame.DICE.nextInt(255);
        return  new Color (r, g, b);
    }    
    
}

//For question 2.4.1:
/*    
    private static final String POINTS =  "0 0 0 1 1 1 0 2 " +    
        "1 0 0 1 1 1 2 1 " +    
        "1 0 0 1 1 1 1 2 " +    
        "0 0 1 0 2 0 1 1"; 
 */