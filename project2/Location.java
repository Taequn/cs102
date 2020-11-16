//Represents a "coordinate" on a game grid, represented by a row and column
import java.util.Random;
class Location {
    
    //---------------- Instance Variables -----------------//    
    //The location's row/column
    private Random dice = new Random();
    private int row;
    private int col;
    private int trait;
    private int position = dice.nextInt(4);

    /**
     Changes:
     1) Made new consturctor with 3 parameters
        to give assets and a player traits to ease
        manipulations and cut down repetitive code.
        Also, updated toString() to include the
        trait of the asset.

     2) Each dynamic non-player asset has a position
        that corresponds to the image in the array
        of images. Position is randomized. I implemented
        an accessor method to get it.

     Traits:
     1 — player
     2 — avoid assets
     3 — catch assets
      **/
    
    //---------------- Constructors -----------------//
    //both row and col arguments required at instantiation
    public Location(int r, int c, int t) {
        this(r, c);
        this.trait = t;
    }

    public Location(int r, int c) {
        this.row = r;
        this.col = c;
    }

    
    //---------------- Instance Methods -----------------//
    public int getRow() { return row; }

    public int getCol() { return col; }

    public int getTrait() { return trait; }

    public void setTrait(int number){ this.trait = number; }

    public int getPosition() { return position; }

    public void set(int r, int c) { 
       row = r;
       col = c;
    }
   
    public boolean equals(Location otherLoc) {
        return row == otherLoc.getRow() && col == otherLoc.getCol();
    }
    
    public String toString() {
        return "(" + row + ", " + col + ", " + trait + ")";
    }
    
}
