import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
*	A class that manages the game's difficulty levels and buttons, 
*	ranging from easy to hard.
*/
public class Difficulty {

	/**
	 * The JFrame that contains the difficulty buttons as well as 
	 * the listeners for mouse clicks on these buttons is set up 
	 * by the function for the difficulty class. This class adds three 
	 * anonymous classes that, in essence, listen for mouse clicks
	 * on Easy, Medium, or Hard difficulty levels.
	 */
	public Difficulty(){
		//make a frame with a box layout appropriate for the choosing of levels
		final JFrame frame = new JFrame("Menu");	
		BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS); 
		
		//set up the buttons (easy, medium, hard) appropriately and add them to the frame
		Button easy = new Button("Easy");
		easy.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button medium = new Button("Medium");
		medium.setFont(new Font("Verdana", Font.BOLD, 32));
		
		Button hard = new Button("Hard");
		hard.setFont(new Font("Verdana", Font.BOLD, 32));
		
		frame.add(easy); 
		frame.add(medium);
		frame.add(hard);

		//tune the frame's setting appropriately
		frame.setLayout(boxLayout);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);		
		
		//create action listeners for the easy, medium and hard that dispose
		//of the current frame and call the MazeFrame with appropriate parameters
		
		// EASY difficulty Action Listener
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				int easy = 10;
				new MazeFrame(easy);
			}
		});
		
		// MEDIUM difficulty Action Listener
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				int medium = 15;
				new MazeFrame(medium);
			}
		});
		
		// HARD difficulty Action Listener
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				frame.dispose();
				int hard = 20;
				new MazeFrame(hard);
			}
		});		
	}
}
