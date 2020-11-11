import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Stick" Tetromino Shape
//
//Shape layout at starting rotation:
//    
//   ****
public class ShapeStick extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (2 rotations)
    private static final String POINTS =  "0 0 0 1 0 2 0 3 " + 
        "0 0 1 0 2 0 3 0";
    
    //Shape color
    private static final Color SHAPE_COLOR = Color.CYAN;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapeStick(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "T-Shape " + super.toString();
    }
}