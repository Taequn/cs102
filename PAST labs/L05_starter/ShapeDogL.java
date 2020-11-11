import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Left-Facing Dog" Tetromino Shape
//
//Shape layout at starting rotation:
//   **
//    **
public class ShapeDogL extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (2 rotations)
    private static final String POINTS =  "1 0 0 1 1 1 0 2 " +  
        "0 0 1 0 1 1 2 1"; 
    
    //Shape color
    private static final Color SHAPE_COLOR = Color.RED;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapeDogL(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "Dog-Left Shape " + super.toString();
    }
}