import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
* A class that creates the primary menu user interface, which allows
* choosing between seeing instructions or playing the game.
*/
public class MainMenu {
	
	JFrame frame = new JFrame("Maze");
	
	/**
	 *  Constructor for the Main Menu that adds the title along with
	 *  the two buttons for playing the game and viewing the instructions
	 *  
	 *  One anonymous classes is used to listen for a play game click, 
	 *  in which it will call up the difficulty class.
	 *  
	 *  The second anonymous class listens for a click on the instructions,
	 *  in which it would display the appropriate instructions screen.
	 */
	public MainMenu(){
		//box layout to allow for a simple two box interface
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
		
		//make two buttons, one called "Play" and the other called "Instructions" and set appropriate fonts
		Button play = new Button("Play");
		play.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button instruction = new Button("Instructions");
		instruction.setFont(new Font("Verdana", Font.BOLD, 32));
		
		//add the buttons to the frame
		frame.add(play); 
		frame.add(instruction);

		//tune the frame to an appropiate setting
		frame.setLayout(boxLayout);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
		
		//if play is clicked, dispose of the current frame and call Difficulty
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				new Difficulty();
			}
		});
		
		//if instruction is clicked, make a new frame with all the instructions written in them.
		instruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				JFrame frame = new JFrame("Instructions");
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				
				String text = "1. The blue character is the player.\r\n";
				text += "2. Player must reach the red endpoint to win.\r\n";
				text += "3. While going through the maze, get coins \r\n";
				text += "4. Achieve a higher score.\r\n";
				text += "5. Move teh character by using the 'W-A-S-D' keyboard or \r\n";
				text += "by using the standard arrow keys to move.\r\n";
				text += "6. Mouse clicks can also be used on the on=screen \r\ndirections buttons as well.";
				
				JTextPane textPane = new JTextPane();
				textPane.setText(text);
				
				frame.getContentPane().add(textPane, BorderLayout.CENTER);
				frame.setLocationRelativeTo(null);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
