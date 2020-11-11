import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Left-Facing Periscope" Tetromino Shape
//
//Shape layout at starting rotation:
//     *
//     *
//    **
public class ShapePeriscopeL extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (4 rotations)
    private static final String POINTS =  "0 0 0 1 1 1 2 1 " + 
        "1 0 1 1 0 2 1 2 " + 
        "0 0 1 0 2 0 2 1 " + 
        "0 0 1 0 0 1 0 2";
    
    //Shape color
    private static final Color SHAPE_COLOR = Color.BLUE;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapePeriscopeL(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "Periscope-Left-Piece " + super.toString();
    }
}