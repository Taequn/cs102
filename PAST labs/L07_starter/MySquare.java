import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class MySquare extends MyShape {

	public MySquare(double x, double y) {
		super();
		shape = new Rectangle2D.Double(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
	}


	//Look at the argument of this constructor.  What could you use this for?
	//You will need to modify code in here when implementing the 't' key
	public MySquare(MyShape s) {
		super(s.getFillColor());
		System.out.println("Constructor called after 't' isn't complete");
		System.out.println("hint: " + s.getFillColor() + " "
			       + s.getShapeX() + " " + s.getShapeY());
		shape = new Rectangle2D.Double(s.getShapeX(), s.getShapeY(),
				DEFAULT_SIZE, DEFAULT_SIZE);
	}

	public void moveFrame(float moveX, float moveY) {
		shape.setFrame(getShapeX()+moveX, getShapeY()+moveY,
			getShapeWidth(), getShapeHeight());
	}


	public void scale(int fac) {
		int sizeChange = fac*SIZE_INC;
		shape.setFrame(getShapeX()-(sizeChange/2), getShapeY()-(sizeChange/2),
				getShapeWidth()+sizeChange,
				getShapeHeight()+sizeChange);
	}

	public String toString() {
		return "rectangle " + shape.toString() + " color " + fillColor;
	}


}
