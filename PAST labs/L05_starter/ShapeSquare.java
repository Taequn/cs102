import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Square" Tetromino Shape
//
//Shape layout at starting rotation:
//    **
//    **
public class ShapeSquare extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (1 rotation)
    private static final String POINTS =  "0 0 1 0 0 1 1 1";
    
    //Shape color
    private static final Color SHAPE_COLOR = Color.YELLOW;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapeSquare(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "Square-Piece " + super.toString();
    }
}