import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.*;

//"Stick" Tetromino Shape
//
//Shape layout at starting rotation:
//    
//   ****
public class ShapeT extends Tetromino{
    
    
    //---------------- Class Variables and Constants -----------------//
    
    //Points data defining shape and rotations of the Tetromino (2 rotations)
    private static final String POINTS =  "0 0 0 1 1 1 0 2 " +
            "1 0 0 1 1 1 2 1 " +
            "1 0 0 1 1 1 1 2 " +
            "0 0 1 0 2 0 1 1";

    //Shape color
    private static final Color SHAPE_COLOR = Color.MAGENTA;
    
    
    
    //---------------- Constructors -----------------//
    
    
    public ShapeT(){
        super(POINTS, SHAPE_COLOR);
    }
    
    
    
    //---------------- Instance Methods -----------------//
    
    public String toString(){
        return "T Shape " + super.toString();
    }
}