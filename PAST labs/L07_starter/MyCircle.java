import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class MyCircle extends MyShape {

	public MyCircle(double x, double y) {
		super();
		System.out.println(x + " " + y);
		shape  = new Ellipse2D.Double(x, y, DEFAULT_SIZE, DEFAULT_SIZE);
	}


	public MyCircle(MyShape s) {
		super(s.getFillColor());
		shape = new Ellipse2D.Double(s.getShapeX(), s.getShapeY(),
			                       s.getShapeWidth(), s.getShapeHeight());
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
    	return "circle " + shape.toString() + " color " + fillColor;
    }
}
