import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class DrawingCanvas extends JComponent implements KeyListener,
										MouseListener, MouseMotionListener{


	private static final int NONE = -1;
	private ArrayList<MyShape> listOfShapes = new ArrayList<MyShape>();

	private static Random random = new Random();

	//Provided: two hard-coded shapes
	//For Part 2.1, you will only draw these two hard coded shapes!
	//private MyShape shapeOne;
	//private MyShape shapeTwo;

	//For Part 2.2, you will instead need to draw a dynamic number of shapes!
	//hmmm... what data structure would work well here?


	//[For Part 2.2] Keep track of which shape is selected -- tracking its index
	//will make some of these functions easier to implement AND more efficient!
	private int selectedIndex = NONE;

	//used to track the position of the Mouse cursor in the window
	private int lastKnownMouseX, lastKnownMouseY;


	public DrawingCanvas(int numOfShapes) {
		super();
		System.out.println("Canvas created, number of shapes = ");
		//initializes the hard-coded shapes for Part 2.1
		//initHardCodedShapes();

		//In Part 2.2, you will use the initDynamic() method instead!
		initDynamicShapes(numOfShapes);

		//initializes the mouse and keyboard functionality
		initMouseAndKeyboard();
	}


	//This function is no longer necessary in Part 2.2
	/*
	private void initHardCodedShapes() {
		shapeOne =  new MySquare(Experiment.getRandomX(), Experiment.getRandomY());
		shapeTwo =  new MyCircle(Experiment.getRandomX(), Experiment.getRandomY());
	}

	 */


	private void initDynamicShapes(int numOfShapes) {
		for (int i=0; i<numOfShapes; i++){
			if (i%2==0){
				listOfShapes.add(new MySquare(Experiment.getRandomX(), Experiment.getRandomY()));
			}
			else{
				listOfShapes.add(new MyCircle(Experiment.getRandomX(), Experiment.getRandomY()));
			}
		}

	}



	private void initMouseAndKeyboard() {
		requestFocus();

		//Let's the window know that the methods to react to keyboard/mouse actions
		//are implemented in this class
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	// (re)Paints all of the shapes in the window
	//*** This gets called everytime repaint() is called! ***
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

		//For Part 2.1, this is for painting  two hard coded shapes
		//shapeTwo.paintComponent(g2d);
		//shapeOne.paintComponent(g2d);

		//For Part 2.2, this will now paint your collection of shapes
		for (int i=0; i<listOfShapes.size(); i++){
			listOfShapes.get(i).paintComponent(g2d);
		}
	}

	//Scales the currently selected shape by a specific vector
	public void scaleSelectedShape(int vector) {

		//For Part 2.1, determines which of the hard-coded shapes is selected
		/*
		if (shapeTwo.getSelected())
			shapeTwo.scale(vector);
		else if (shapeOne.getSelected())
			shapeOne.scale(vector);
		 */

		//For Part 2.2, scale the selected shape in your collection
		for (int i = 0; i < listOfShapes.size(); i++) {
			if (listOfShapes.get(i).getSelected()) {
				listOfShapes.get(i).scale(vector);
				}
			}
		repaint();
	}

	//Deselects the currently seleted shape
	public void deselect() {
		//For Part 2.1, this just needs to look at the two hard coded shapes
		//if (shapeTwo.getSelected())
		//	shapeTwo.setSelected(false);

		//if (shapeOne.getSelected())
		//	shapeOne.setSelected(false);

		//For Part 2.2, determine which shape in your array is selected
		for (int i = 0; i < listOfShapes.size(); i++){
			if (listOfShapes.get(i).getSelected()){
				listOfShapes.get(i).setSelected(false);
			}
		}
	}



	//Switches the currently selected shape to be the opposite shape
	//retains the original shape's color and size
	//(ex: if the shape is a Circle it becomes a Square)
	public void switchSelectedShape() {
		//Complete this method!
		//For Part 2.1, this just looks at the two hard coded shapes
		//For Part 2.2, you will need to look through your collection
		//(instanceof will be handy here!)
		/*
		if (selectedIndex != NONE) {
			if (shapeOne.getSelected()) {
				if (shapeOne instanceof MySquare) {
					shapeOne = new MyCircle(shapeOne);

				}
				else {
					shapeOne = new MySquare(shapeOne);
				}
				shapeOne.setSelected(true);
				selectedIndex = 0;
				repaint();
				return;
			}
			else if (shapeOne.getSelected()){
				if (shapeTwo.getSelected()) {
					if (shapeTwo instanceof MySquare) {
						shapeTwo = new MyCircle(shapeTwo);

					} else {
						shapeTwo = new MySquare(shapeTwo);
					}
					shapeTwo.setSelected(true);
					selectedIndex = 0;
					repaint();
					return;
				}
			}*/

		if (selectedIndex != NONE) {
				for (int i=0; i<listOfShapes.size(); i++){
					if (listOfShapes.get(i).getSelected()){
						if (listOfShapes.get(i) instanceof MySquare){
							listOfShapes.set(i, new MyCircle(listOfShapes.get(i)));
						} else {
							listOfShapes.set(i, new MySquare(listOfShapes.get(i)));
						}
						listOfShapes.get(i).setSelected(true);
						selectedIndex = 0;
						repaint();
						return;
					}
				}
			}
		repaint();
	}


    //This method is called anytime the mouse is clicked in the window
    public void mousePressed(MouseEvent event) {

    	lastKnownMouseX = event.getX();
    	lastKnownMouseY = event.getY();

    	//For Part 2.1 - this will determine if the mouse was clicked inside
    	//either of the hard-coded shapes

    	//For Part 2.2 - this will need to to determine which shape (if any)
    	//was selected.

    	//Remember, more recently selected shapes should be drawn ON TOP
    	//of less recently selected shapes!
    	/*
		if (shapeOne.contains(lastKnownMouseX, lastKnownMouseY)) {
    		deselect();
    		shapeOne.setSelected(true);
    		selectedIndex = 0;
    		repaint();
    		return;
    	}
    	else if
        (shapeTwo.contains(lastKnownMouseX, lastKnownMouseY)) {
        	deselect();
        	shapeTwo.setSelected(true);
        	selectedIndex = 0;
        	repaint();
        	return;
        }
		else {

    	 */
		for (int i = 0; i < listOfShapes.size(); i++) {
				if (listOfShapes.get(i).contains(lastKnownMouseX, lastKnownMouseY)) {
					deselect();
					listOfShapes.get(i).setSelected(true);
					listOfShapes.add(listOfShapes.get(i));
					listOfShapes.remove(i);
					selectedIndex = 0;
					repaint();
					return;
				}
			}
		deselect();
		repaint();
}

        //what happens if you click inside the window but not on any shape?


    //This method is called anytime the mouse is dragged inside the window
    public void mouseDragged(MouseEvent event) {
    	//For Part 2.2, you will need to move whichever shape is selected.
    	//You should NOT search through your entire
    	//array to find your selected shape (look at your instance variables!)
    	if (selectedIndex != NONE) {
    		int moveX = event.getX()-lastKnownMouseX;
    		int moveY = event.getY()-lastKnownMouseY;
/*
    		if (shapeTwo.getSelected())
    			shapeTwo.moveFrame(moveX, moveY);

    		else if (shapeOne.getSelected())
    			shapeOne.moveFrame(moveX, moveY);
 */

			for (int i = 0; i < listOfShapes.size(); i++){
				if (listOfShapes.get(i).getSelected())
					listOfShapes.get(i).moveFrame(moveX, moveY);
			}
    		lastKnownMouseX = event.getX();
    		lastKnownMouseY = event.getY();
    		repaint();
    	}
    }

    public void addShape() {
		int ranNum = random.nextInt(2);
		if (ranNum==0)
			listOfShapes.add(new MySquare(Experiment.getRandomX(), Experiment.getRandomY()));
		if (ranNum==1)
			listOfShapes.add(new MyCircle(Experiment.getRandomX(), Experiment.getRandomY()));
		repaint();
	}

	public void deleteShape() {
		for (int i=0; i<listOfShapes.size(); i++){
			if (listOfShapes.get(i).getSelected()){
				listOfShapes.remove(i);
			}
		}
		repaint();
	}

    //This method is called anytime a keyboard key is pressed
    public void keyPressed(KeyEvent e) {
        //Prints the key pressed
        System.out.println("key " + e.getKeyChar() + " " + e.getKeyCode());

        //'Q' quits the program
        if (e.getKeyCode()==KeyEvent.VK_Q)
        	System.exit(1);

        //'+' Grows the shape
        else if (e.getKeyCode()==KeyEvent.VK_EQUALS)
        	scaleSelectedShape(1);

        //'-' Shrinks the Shape
        else if (e.getKeyCode()==KeyEvent.VK_MINUS)
        	scaleSelectedShape(-1);

        //'t' switches the selected item to the opposite shape
        else if (e.getKeyCode()==KeyEvent.VK_T)
        	switchSelectedShape();
        //'A' key will eventually add a shape (used in Part 2.3)
        else if (e.getKeyCode()==KeyEvent.VK_A)
			addShape();
        //'D' key will eventually delete a shape (used in Part 2.3)
        else if (e.getKeyCode()==KeyEvent.VK_D)
        	deleteShape();
    }


    //****   DO NOT MODIFY BELOW CODE ****
    //These functions are required by various interfaces, but are not used
    public void mouseReleased(MouseEvent event) { }

    public void mouseClicked(MouseEvent event) { }

    public void mouseEntered(MouseEvent event) { }

    public void mouseExited(MouseEvent event) { }

    public void mouseMoved(MouseEvent event) { }

    public void keyReleased(KeyEvent event) {  }

    public void keyTyped(KeyEvent event) {  }
}
