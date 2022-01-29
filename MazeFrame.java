import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
*	A class that covers the game board's features, such as the maze 
*	GUI and movement buttons.
*/
public class MazeFrame {
	
	private static JFrame frame = new JFrame("Maze");
	
	JPanel p = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new BorderLayout());
	
	/**
	 * Constructor that makes a maze by connecting Board.java
	 * and Player.java, as well all of the movement button listeners.
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
		frame.add(panel2, BorderLayout.SOUTH);	
		
		
		//create the on screen buttons using navigational directions as variable names to 
		//avoid overlapping names from other classes
		
		/* use the same formatting for all the movement buttons -- create the button with the movement as the name,
		 * tune it appropriately, and create an action listener for it that will call its movement from player
		 * @author Olivia
		 */
		JButton south = new JButton("Down");
		south.setFocusable(false);
		panel2.add(south, BorderLayout.SOUTH);			
		south.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.down(test);
			}
		});
		
		JButton north = new JButton("Up");
		north.setFocusable(false);
		panel2.add(north, BorderLayout.NORTH);
		north.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.up(test);
			}
		});
		
		JButton east = new JButton("Right");
		east.setFocusable(false);
		panel2.add(east, BorderLayout.EAST);
		east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.right(test);
			}
		});		
		
		
		JButton west = new JButton("Left");
		west.setFocusable(false);
		panel2.add(west, BorderLayout.WEST);
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				player.left(test);
			}
		});	
		
		/*this button, newGame, allows the user to set up a new game by calling MainMenu.java, like MazeGUI.java
		 * Miles, Raed
		 */
		JButton newGame = new JButton("New Game");
		newGame.setFocusable(false);
		panel2.add(newGame, BorderLayout.CENTER);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				new MainMenu();
			}
		});	
			
		//allow 'WASD' keys to be recognized as movement keys
		// Olivia, Mohamed
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
	 * @author Miles, Mohamed
	 */
	public MazeFrame(int level, int money) {
		
      //dispose of the current frame and make a new one
      frame.dispose();        
      JFrame newFrame = new JFrame();
      
      //create a text that congratulates the winner and shows the number of coins they achieved appropriately -- Miles
      JTextPane textPane = new JTextPane();
      textPane.setText("\r\n \r\n \r\n \r\nCongratulations!\r\nYou got " + money + " coin(s)!");
      textPane.setFont(new Font("Verdana", Font.BOLD, 32));
      textPane.setEditable(false);
      
      //Mohamed -- Centered the JTextPane using code from StackOverFlow
      //link: https://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
      StyledDocument doc = textPane.getStyledDocument();
      SimpleAttributeSet center = new SimpleAttributeSet();
      StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
      doc.setParagraphAttributes(0, doc.getLength(), center, false);
      
      //tune the new frame appropriately
      newFrame.setBackground(Color.green);
      newFrame.add(textPane, BorderLayout.CENTER);
      newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      newFrame.setSize(500,500);
      newFrame.setLocationRelativeTo(null);
      newFrame.setVisible(true);
  }
}
