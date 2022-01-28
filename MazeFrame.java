import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
*	A class that covers the game board's features, such as the maze 
*	GUI and movement buttons.
*/
public class MazeFrame {
	
	static JFrame frame = new JFrame("Maze");
	JPanel p = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	
	/**
	 * Constructs the maze frame with a board and a player, as well
	 * all of the movement button listeners.
	 * @param level	The size of the Frame
	 */
	public MazeFrame(int level){		
		
		//create a board and player according to level, the level chosen on Difficulty()
		final Board test = new Board(level, level, level);
		final Player player = new Player(test);
		
		//add the panels to the frame
		p.add(test, BorderLayout.CENTER);
		p.setFocusable(true);
		frame.add(p, BorderLayout.CENTER);				
		frame.add(p2, BorderLayout.SOUTH);	
		
		
		//create the on screen buttons using navigational directions as variable names to 
		//avoid overlapping names from other classes
		
		//use the same formatting for all the movement buttons -- create the button with the movement as the name,
		//tune it appropriately, and create an action listener for it that will call its movement from player
		JButton south = new JButton("Down");
		south.setFocusable(false);
		p2.add(south, BorderLayout.SOUTH);			
		south.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.down(test);
			}
		});
		
		JButton north = new JButton("Up");
		north.setFocusable(false);
		p2.add(north, BorderLayout.NORTH);
		north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.up(test);
			}
		});
		
		JButton east = new JButton("Right");
		east.setFocusable(false);
		p2.add(east, BorderLayout.EAST);
		east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.right(test);
			}
		});		
		
		
		JButton west = new JButton("Left");
		west.setFocusable(false);
		p2.add(west, BorderLayout.WEST);
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.left(test);
			}
		});	
		
		//this button, newGame, allows the user to set up a new game by calling MainMenu.java, like MazeGUI.java
		JButton newGame = new JButton("New Game");
		newGame.setFocusable(false);
		p2.add(newGame, BorderLayout.CENTER);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				new MainMenu();
			}
		});	
			
		//allow 'WASD' keys to be recognized as movement keys
		p.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {				
				int keyCode = e.getKeyCode();
				if(e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) player.left(test);
				if(e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) player.right(test);
				if(e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) player.up(test);
				if(e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) player.down(test);
			}
		});	
		
		//tune the frame appropriately
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.green);
		frame.setVisible(true);
	}

	/**
	 * The constructor for the labyrinth frame that shows the winning frame.
	 * @param level The size of the Frame
	 * @param money The total number of coins a player has gathered.
	 */
	public MazeFrame(int level, int money) {
		
      //dispose of the current frame and make a new one
      frame.dispose();        
      JFrame newFrame = new JFrame();
      
      //create a text that congratulates the winner and shows the number of coins they achieved appropriately
      JLabel textLabel = new JLabel("<html>Congratulations!<br>You got " + money + " coin(s)!</html>", JLabel.CENTER);
      textLabel.setFont(new Font("Verdana", Font.BOLD, 32));
      
      //tune the new frame appropriately
      newFrame.setBackground(Color.green);
      newFrame.add(textLabel, BorderLayout.CENTER);
      newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      newFrame.setSize(500,500);
      newFrame.setLocationRelativeTo(null);
      newFrame.setVisible(true);
  }
}
