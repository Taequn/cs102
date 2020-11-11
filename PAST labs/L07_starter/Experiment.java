import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

// Driver class
//
// ***** YOU DO NOT NEED TO MODIFY ANY CODE IN THIS FILE *****

public class Experiment {

	private static final int DEFAULT_NUM_OF_SHAPES = 12;

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 600;

	private static final int RGBMAX = 256;

	private static int numOfShapes;
	private static Random random = new Random();

	public static Color generateRandomColor(Color mix) {

		int red = random.nextInt(RGBMAX);
		int green = random.nextInt(RGBMAX);
		int blue = random.nextInt(RGBMAX);

		// mix the color
		if (mix != null) {
			red = (red + mix.getRed()) / 2;
			green = (green + mix.getGreen()) / 2;
			blue = (blue + mix.getBlue()) / 2;
		}

		Color color = new Color(red, green, blue);
		return color;
	}

	public static double getRandomX() {
		return random.nextInt(FRAME_WIDTH-MyShape.DEFAULT_SIZE);
	}

	public static double getRandomY() {
		return random.nextInt(FRAME_HEIGHT-MyShape.DEFAULT_SIZE);
	}


	//Initializes our windows (represented by a JFrame object)
	private static void createAndShowGUI() {

		JFrame window = new JFrame("Experiment");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(FRAME_WIDTH, FRAME_HEIGHT);

		//instantiate a drawing canvas, pass in the number of shapes to draw
		DrawingCanvas canvas = new DrawingCanvas(numOfShapes);
		window.add(canvas);

		window.setBackground(Color.WHITE);
		window.setVisible(true);
		canvas.requestFocus();
	}


	public static void main (String[] args) {

		//default incase no/invalid argument is entered
		numOfShapes = DEFAULT_NUM_OF_SHAPES;
		if (args.length > 0){
			try{
				//converts String to int, takes the first CL argument
				numOfShapes = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException nfe){
				System.err.println("Invalid argument entered!  Using default value for number of shapes.");
			}
		}
		createAndShowGUI();

	}
}
