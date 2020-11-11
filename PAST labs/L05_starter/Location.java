import java.awt.Color;

//Represents a "coordinate" on a game grid, represented by a row and column
public class Location {
    
    //---------------- Instance Variables -----------------//    
    
    //The location's row/column
    private int row;
    private int col;
    
    
    //---------------- Constructors -----------------//
    
    //both row and col arguments required at instantiation
    public Location(int r, int c) {
        row = r;
        col = c;
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public boolean equals(Location otherLoc) {
        return row == otherLoc.getRow() && col == otherLoc.getCol();
    }
    
    public String toString() {
        return "(" + row + ", " + col + ")";
    }
    
}