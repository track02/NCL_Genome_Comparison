import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class Gui {

	//Fields
	
	//Frame and panels
	private JFrame frame = new JFrame("Genome Comparison");
	private JPanel leftpanel = new JPanel();
	private JPanel rightpanel = new JPanel();
	
	//Buttons
	private JButton btnCompFile = new JButton("Select Comparison File");
	private JButton btnRun = new JButton("Start Program");
		
	private JButton btnTrainFile = new JButton("Select Training Data");
	private JButton btnTrain = new JButton("Train Network");
	
	//Text panel for console output
	private JTextArea console = new JTextArea(20,50);	
	JScrollPane scroll = new JScrollPane(console);
	
	
	
	
	//Menu Bar
	private JMenuBar menu = new JMenuBar();
	//File menu
	private JMenu mfile = new JMenu("File");
	
	//File select / exit options
	private JMenuItem miSave = new JMenuItem("Save Results");
	private JMenuItem miQuit = new JMenuItem("Exit");
	
	//Help menu
	private JMenu mhelp = new JMenu("Help");
	private JMenuItem miStart = new JMenuItem("Quick Start Guide");
		
	
	//Constructor
	public Gui(){
		
		//Add menu bar to frame
		frame.setJMenuBar(menu);
		
		//Add sub menu to each button
		mfile.add(miSave);
		mfile.add(miQuit);
		mhelp.add(miStart);
		
		//Add buttons to menu
		menu.add(mfile);
		menu.add(mhelp);
		
		//Add buttons to left panel
		leftpanel.add(btnCompFile);
		leftpanel.add(btnRun);
		leftpanel.add(btnTrainFile);
		leftpanel.add(btnTrain);
		
		//Add text pane to right
		rightpanel.add(scroll);
		
		
		//Add panels to frame
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(leftpanel, BorderLayout.WEST);
		frame.getContentPane().add(rightpanel, BorderLayout.SOUTH);
		
		
	}
	
	
	//Methods
	public void drawWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	
	
}
