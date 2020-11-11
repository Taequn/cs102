import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Right-Facing Periscope" Tetromino Shape
//
//Shape layout at starting rotation:
//     *
//     *
//     **
public class ShapePeriscopeR extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (4 rotations)
    private static final String POINTS =  "0 0 1 0 2 0 0 1 " + 
        "0 0 0 1 0 2 1 2 " + 
        "2 0 0 1 1 1 2 1 " + 
        "0 0 1 0 1 1 1 2";
    
    //Shape color
    private static final Color SHAPE_COLOR = Color.ORANGE;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapePeriscopeR(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "Periscope-Right-Piece " + super.toString();
    }
}