import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
* A board class that creates a 2D array of characters to 
* simulate a whole game board.
*/
@SuppressWarnings("serial")
public class Board extends JPanel {
	
	//instance variables
	private int unVisited;
	private int scale = 1;
	private char[][] board;	//2D array to hold the position of walls and empty spaces
	private int size;
	LinkedList<Position> positionList = new LinkedList<Position>();
	
	/**
	 * The constructor for the board, which takes an x and a y as the
	 * board's length and height. The '#' stands for the exterior walls,
	 * '=' for the inside walls, and 'u' for freespace.
	 * @param x the size of the board in the x direction
	 * @param y the size of the board in the y direction
	 */
	public Board(int x, int y, int obj){
		//set values for all variables
		unVisited = (x * x);
		y *= 2; 
		x *= 2; 
		y++; 
		x++;
		scale = y;
		
		board = new char [x][y];	//initialize board
		size = x;	//set size to x
		
		//make the board
		makeBoard();
		addObject(obj);
	}	

	/**
	 * The makeBoard() resets the board	
	 */
	public void makeBoard(){
		//make the entire board freespace
		for (int i = 0; i < size; i++) {
			for (int k = 0; k < size; k++) {
				board[i][k] = 'u';
			}
		}
		
		//checker board of inner walls every other row as well as rows of complete inner walls
		for (int i = 0; i < size; i+=2) {
			for (int k=0; k < size; k++) {
				board[i][k] = '=';
				board[k][i] = '=';
			}
		}
		
		//exterior walls all around the perimeter
		for (int i = 0; i < size; i++) {
			board[i][0] = '#';
			board[0][i] = '#';
			board[size-1][i] = '#';
			board[i][size-1] = '#';
		}
		
		//this is what it will look like if easy (level 10) is selected
		/*
		 *  #####################
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#===================#
			#u=u=u=u=u=u=u=u=u=u#
			#####################
		*/
		
		//generate a random maze that differs from the base
		generate(1,1);
	}
	
	
	/**
	 * Modification to the paint technique so that the game board 
	 * is correctly painted on the canvas in relation to the maze's 
	 * 2D array, scaled appropriately.
	 */
	public void paint(Graphics g){
		//paint the console and scale the frame according the the pixel width and the scale (level) relation
		super.paint(g);
		int n = 500/(scale+10);
		
		//remember '#' = external wall, '=' = internal wall, '8' = end point, 'X' = player and '+' = coin
		//loop through the 2D array enough to fill up according the level selected (size)
		for(int i = 0; i < size; i++){
			for(int k = 0; k < size; k++) {
				//if the item is an external wall, set the color to black and make a square at the right position
				if((board[i][k] == '#')) {
					g.setColor(Color.black);
					g.fillRect(i * n, k * n, n, n);
				} 
				//else if the item is an internal wall, set the color to black and make a square at the right position
				else if(board[i][k] == '=') {
					g.setColor(Color.black);
					g.fillRect(i * n, k * n, n, n);
				} 
				//else if the item is the red square (end point), set the color to red and make a square at the right position
				else if(board[i][k] == '8') {
					g.setColor(Color.red);
					g.fillRect(i * n, k * n, n, n);
				} 
				//else if the item is the actual player, set the color to blue and make a square at the right position
				else if(board[i][k] == 'X') {
					g.setColor(Color.blue);
					g.fillRect(i * n, k * n, n, n);
				} 
				//else if the item is a coin, make the color yellow and make a circle at the right position
				else if(board[i][k] == '+') {
					g.setColor(Color.yellow);
					g.fillOval(i * n, k * n, n, n);
				}
			}
		}
	}	
	
	/**
	 * A function for calculating the value at the given coordinates (getter method).
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return the x and y coordinates' value
	 */
	public char get(int x, int y) {
		//return the element of the index
		return board[x][y];
	}
	
	/**
	 * A function that sets a value at the given coordinates (setter method).
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param value The value to replace the existing character.
	 */
	public void set(int x, int y, char value) {
		//set the element of the index chosen to value
		board[x][y] = value;
		repaint();
	}
	
	/** 
	 * A method that prints the board in text base format (testing purposes).
	 */
	public void print(){
		//loop through the 2D array and print each element in neat format
		for (int i = 0; i < size; i++)
			for (int k=0; k < size; k++)
				System.out.print(board[i][k] + " ");
			System.out.println();
	}
	
	/**
	 * A function that adds a certain number of items to 
	 * various points on the maze.
	 * @param amount The total number of objects to be 
	 * placed on the board.
	 */
	public void addObject(int num) {
		//declare and initialize location to a Random object
		Random location = new Random();
		
		//loop through this loop the number of times requested
		for(int i = 0; i < num; i++) {
			//reset the x and y position with every cycle
			int xPos = 0, yPos = 0;
			//while the x and y position are both equal to 0, run this loop
			while((xPos == 0) || (yPos == 0)) {
				//set randX and randY to random integers using location with the upper limit (upperbound) being size
				int randX = location.nextInt(size);
				int randY = location.nextInt(size);
		
				//if randX is not even and does not equal 0 or size set xPos to randX
				//do the same with randY and yPos
				if (((randX % 2) != 0) && (randX != 0 || randX != size))	
					xPos = randX;
				if (((randY % 2) != 0) && (randY != 0 || randY != size))	
					yPos = randY;
				
				//if the position of xPos and yPos is the player or the end point (red square) set the positions to 0
				if((get(xPos, yPos) == 'X') || get(xPos, yPos) == '8') {
					xPos = 0;
					yPos = 0;
				}
			}
			
			//set the final position to be '+', a coin
			set(xPos, yPos, '+');
		}
	}
		
	/**
	 * A position structure whose sole purpose is to keep track 
	 * of the position's x and y coordinates. This is a variable 
	 * that is used in generate.
	 */
	public class Position {
		//instance variables
		int x; 
		int y;
		
		//default constructor will simply set x and y to 0
		public Position(){
			this.x = 0;
			this.y = 0;
		}
				
		//constructor that takes in x and y as parameters and sets them to instance variables
		public Position(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		//setter method for x
		public void setX(int x){
			this.x = x;
		}
		
		//setter method for y
		public void setY(int y){
			this.y = y;
		}
		
		//getter method for x
		public int getX(){
			return x;
		}
		
		//getter method for y
		public int getY(){
			return y;
		}
	}
	
	/**
	 * The primary purpose of this method is to update
	 * the cardinal direction values in relation to 
	 * the current Cell variable.
	 * @param cC currentCell, which is a position, should be passed in.
	 * @return cardinal locations that have been updated
	 */
	public char[] updateDirection(Position cC){
		//set the cardinal values to 0
		char north = 0, south = 0, east = 0, west = 0;
		
		//if the position to the bottom is not an external wall, east is two spaces below
		if (get(cC.getX(), cC.getY() + 1) != '#')
			east = get(cC.getX(), cC.getY() + 2);
		//if the position above is not an external wall, west is two spaces above
		if (get(cC.getX(), cC.getY() - 1) != '#')
			west = get(cC.getX(), cC.getY() - 2);
		//if the position to the left is not an external wall, north is two space to the left
		if (get(cC.getX() - 1, cC.getY()) != '#')
			north = get(cC.getX() - 2, cC.getY());
		//if the position to the right is not an external wall, south is two spaces to the right
		if (get(cC.getX() + 1, cC.getY()) != '#')
			south = get(cC.getX() + 2, cC.getY());		
		
		//set char direction[] to be the cardinal positions calculated and return it
		char direction[] = {west,east,south,north};
		return direction;
	}

	
	Position pos[] = new Position[(2 * (getX() / 2))];
	Position cC = new Position(5,5);
	
	
	/**
	 * At the supplied posX and posY coordinates on the game
	 * board, this method starts randomly generating a random 
	 * labyrinth on the game board. Wikipedia pseudocode was utilised.
	 * @param posX The x coordinates of the game board.
	 * @param posY The y coordinates of the game board.
	 */
	public void generate(int posX, int posY) {
		//the current cell is an object of the class Position equal to the position of the the parameters
		cC = new Position(posX, posY);
		//set the current cell to 'v' to mark it as visited and subtract 1 from unVisited
		set(cC.getX(), cC.getY(), 'v');
		unVisited -= 1;
		
		//initialize the cardinal position and put them in an array
		char north = 0, south = 0, east = 0, west = 0;
		char direction[] = {west, east, south, north};
		
		//set the array to an updated direction using the current cell as a reference point
		direction = updateDirection(cC);

		//while there are still unvisited cells, run this loop
		while(unVisited != 0) {
			//set free to 0 to represent freespace in the grid
			int free = 0;
			
			//if any of the cardinal directions assumed in direction[] are free spaces, set free to 1
			if((direction[0] == 'u') || (direction[1] == 'u') || (direction[2] == 'u') || (direction[3] == 'u'))
				free = 1;
			
			//declare and initialize generator to be a Random object
			Random generator = new Random();
			int random = generator.nextInt(4);	//random integer using generator with the upper limit (upperbound) being 4
			set(cC.getX(),cC.getY(), 'v');		//set the current cell to visited
		
			//if the random number is equal to 0 and the west cardinal is a free space
			if((random == 0) && (direction[0] == 'u')) { //West
				//and if the cell above the current cell is not an exterior wall
				if (get(cC.getX(),cC.getY() - 1) != '#') {
					//set said cell to 'v' and have the current cell now be two cells above and push the new current cell to the first index
					set(cC.getX(), cC.getY() - 1, 'v');
					cC = new Position(cC.getX(), cC.getY() - 2);
					positionList.push(cC);
					
					//updated direction with updateDirection() using the new current cell as the reference point and subtract 1 from unVisited
					direction = updateDirection(cC);
					unVisited--;
					
				}
			}
			
			//else if the random number is equal to 1 and the east cardinal is a free space
			else if((random == 1) && (direction[1] == 'u')){ //East
				//and if the cell below is not an exterior wall
				if (get(cC.getX(), cC.getY() + 1) != '#') {
					//set said cell to 'v' and have the current cell now be two cells below and push the new current cell to the first index
					set(cC.getX(), cC.getY() + 1, 'v');
					cC = new Position(cC.getX(), cC.getY() + 2);
					positionList.push(cC);
					
					//update direction with updateDirection() using the new current cell as the reference point and subtract 1 from unVisited
					direction = updateDirection(cC);
					unVisited--;
				}
			}
			
			//else if random is equal to 2 and the south cardinal is a free space
			else if((random == 2) && (direction[2] == 'u')){ //South
				//and if the cell to the right is not an exterior wall
				if (get(cC.getX() + 1, cC.getY()) != '#') {
					//set said cell to 'v' and have the current cell now be one cell to the right and push the new current cell to the first index
					set(cC.getX() + 1, cC.getY(), 'v');
					cC = new Position(cC.getX() + 2, cC.getY());
					positionList.push(cC);
					
					//update direction with updateDirection() using the new current cell as the reference point and subtract 1 from unVisited
					direction = updateDirection(cC);
					unVisited--;
					
				}
			}
			
			//else if random is equal to 3 and the north cardinal is a free space
			else if((random == 3) && (direction[3] == 'u')) { //North
				//and if the cel lto the left is not an exterior wall
				if (get(cC.getX() - 1, cC.getY()) != '#') {
					//set said cell to 'v' and have the current cell now be one cell to the left and push the new current cell to the first index
					set(cC.getX() - 1, cC.getY(), 'v');
					cC = new Position(cC.getX() - 2, cC.getY());
					positionList.push(cC);
					
					//update direction with updateDirection() using the new current cell as the reference point and subtract 1 from unVisited
					direction = updateDirection(cC);
					unVisited--;
					
				}
			} 
			
			//otherwise if free is equal to 0 and the number of elements of positionList is not 0
			else {
				if(free == 0 && positionList.size() != 0) {
					//current cell is equal to the last element of positionList and said element is removed
					cC = positionList.get(positionList.size() - 1);
					positionList.remove(positionList.size() - 1);
					direction = updateDirection(cC);		//direction is updated with updateDirection() using the new current cell as
				}											//the reference point
			}
		}
		//set the current cell to '8' to have it be the red square (end point) and set the top left (not exterior) cell to be the player (blue square)
		set(cC.getX(),cC.getY(),'8');
		set(1,1,'X');
	}
}
