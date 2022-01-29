import java.awt.event.*;
import javax.swing.*;

/**
* A class that generates all of the critical aspects
* for a player to navigate the maze.
* @author Mohamed, Olivia, Miles, Raed
*/
@SuppressWarnings("serial")
public class Player extends JPanel {
	
	//instance variables
	private int coins = 0;
	private int xPos = 0;
	private int yPos = 0;
	
	private final Board board;
	
	/**
	 * Constructor for the player who will be assigned to
	 * the given board. The player will begin from the top
	 * left corner of the board.
	 * @param board The player's playing board.
	 * @author Raed
	 */
	public Player(Board board) {
		this.board = board;
		xPos = 1;
		yPos = 1;
	}	
	
	/**
	 * The method repaints the board.
	 * @author Raed
	 */
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	/**
	 * Inner class that lets you to move up, left, 
	 * down, and right using the keys {w,a,s,d}.
	 * @author Olivia
	 */
	public class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			if(e.getKeyChar() == 'a') 	left(board);
			if(e.getKeyChar() == 'w') 	up(board);
			if(e.getKeyChar() == 'd')	right(board);
			if(e.getKeyChar() == 's')	down(board);
		}
	}
	
	/**
	 * Allows the player to advance left while examining
	 * whether or not their next move will land on a win
	 * cell or a coin cell.
	 * @param board The player's playing board.
	 * @author Mohamed, Raed, Olivia
	 */
	public void left(Board board){
		//if there is no external wall or internal wall to the left, code will run
		if((board.get(xPos - 1, yPos) != '#') && (board.get(xPos - 1, yPos) != '=')) {
			//the current position will be set as 'O', temporary
			board.set(xPos, yPos, 'O');
			//if the new cell is '8' (end point/red square), Win() is called -- also subtracts one from xPos (moves left)
			if(board.get(xPos -= 1, yPos) == '8') {	
				Win();		//player has won
			}
			//else if the new cell is '+' (a coin), increment the number of coins
			else if(board.get(xPos, yPos) == '+') {
				coins++;
			}
			//otherwise just set the new position as 'X', the player
			else {
				board.set(xPos, yPos, 'X');
			}
		}
	}
	
	/**
	 * Allows the player to move right while checking
	 * to see if his or her next move will land on a
	 * win or coin cell.
	 * @param board The player's playing board.
	 * @author Mohamed, Raed, Olivia
	 */
	public void right(Board board) {
		//if the cell to the right is not a wall
		if((board.get(xPos + 1, yPos) != '#') && (board.get(xPos + 1, yPos) != '=')) {
			//set the current cell to 'O'
			board.set(xPos, yPos, 'O');
			//if the cell to the right is '8' (end point, red square), call Win() -- xPos is also incremented (moves right)
			if(board.get(xPos += 1, yPos) == '8') {
				Win();
			}
			//else if the cell to the right is '+' (a coin), increment coins
			else if(board.get(xPos, yPos) == '+') {
				coins++;
			}
			//otherwise set the cell as 'X' (player)
			else {
				board.set(xPos, yPos, 'X');
			}
		}
	}
	
	/**
	 * Allows the player to advance by examining whether 
	 * their next move will move up to a win cell or a 
	 * coin cell.
	 * @param board The player's playing board.
	 * @author Mohamed, Raed, Olivia
	 */
	public void up(Board board) {
		//if the cell above is not a wall
		if((board.get(xPos, yPos - 1) != '#') && (board.get(xPos, yPos - 1) != '=')) {
			//set the current cell to 'O' -- temporary
			board.set(xPos, yPos, 'O');
			//if the cell above is '8' (end point, red square), call Win() -- yPos is also subtracted by 1 (moves up)
			if(board.get(xPos, yPos-=1) == '8') {
				Win();
			}
			//else if the cell above is '+' (a coin), increment coins
			else if(board.get(xPos, yPos) == '+') {
				coins++;
			}
			//otherwise set the new cell to 'X' (player, blue square)
			else {
				board.set(xPos, yPos, 'X');
			}
		}
	}
	
	/**
	 * Allows the player to proceed down the board while checking to see
	 * if their next move will land on a win cell or a coin cell.
	 * @param board The player's playing board.
	 * @author Mohamed, Raed, Olivia
	 */
	public void down(Board board) {
		//if the cell below is not a wall
		if((board.get(xPos, yPos + 1) != '#') && (board.get(xPos, yPos + 1) != '=')) {
			//set the current cell to 'O' -- temporary
			board.set(xPos, yPos, 'O');
			//if the cell below is '8' (end point, red square), call Win() -- yPos is also incremeneted (moves down)
			if(board.get(xPos, yPos += 1) == '8') {
				Win();
			}
			//else if the cell below is '+' (a coin), increment coins
			else if(board.get(xPos, yPos) == '+') {
				coins++;
			}
			//otherwise set the new cell to 'X' (player, blue square)
			else {
				board.set(xPos, yPos, 'X');
			}
		}
	}
	
	/**
	 * A method that will show the amount of coins achieved via a frame.
	 * @author Olivia, Miles
	 */
	public void Win() {
	  //call MazeFrame(int level, int money) where a congratulations screen is prepared
      new MazeFrame(20, coins);        
	}	
}
