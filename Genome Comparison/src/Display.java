import javax.swing.*;
import javax.swing.event.*;

import weka.classifiers.Classifier;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Display {

	//Fields
	private JFileChooser filec = new JFileChooser();
	
	//Frame and panels
	private JFrame frame = new JFrame("Genome Comparison");
	private JPanel leftpanel = new JPanel();
	private JPanel rightpanel = new JPanel();
	
	//Buttons
	private JButton btnCompFile = new JButton("Select Comparison File");
	private JButton btnRun = new JButton("Start Program");
	
		
	private JButton btnTrainFile = new JButton("Select Training Data");
	private JButton btnTrain = new JButton("Train Network");
	
	//Radio Buttons for choosing which network to train
	private JRadioButton network1 = new JRadioButton("Network 1 - Single Match Features");
	private JRadioButton network2 = new JRadioButton("Network 2 - Two Match Features");
	
	private ButtonGroup netselect = new ButtonGroup();
	JPanel radioPanel = new JPanel(new GridLayout(0,1));
	
	//Int to flag which button is pressed
	private int nettoggle = 0;
	
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
	
	//Bayes Nets - to be passed to called methods
	private Classifier net1 = null;
	private Classifier net2 = null;
	
	//Current Comparison / Training files
	private File compf;
	private File trainf;
	
		
	//Action Listener for Buttons
	private ActionListener al = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//If run button is pressed
			if(e.getSource().equals(btnRun)){
				
				//Call the run method
				try {
					Main.run(net1, net2);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			//Select comparison file button
			if(e.getSource().equals(btnCompFile) || e.getSource().equals(btnTrainFile)){
				
				int retval = filec.showOpenDialog(frame);
				
				//File chosen, pass this file to the blastreader
				if(retval == JFileChooser.APPROVE_OPTION){			
					
					
					if(e.getSource().equals(btnCompFile)){
					
						compf = filec.getSelectedFile();		
						try {
							BlastReader.setComp(compf);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}			
					}
					else{
						
						trainf = filec.getSelectedFile();
						TrainNet.setFile(trainf);						
					}
						
				}
			}			
				
			
			if(e.getSource().equals(btnTrain)){
				if(nettoggle == 0)
					try {
						TrainNet.train(net1);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				else
					try {
						TrainNet.train(net2);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			
			
			if(e.getSource().equals(network1)){
				nettoggle = 0;
				System.out.println(nettoggle);
			}
			if(e.getSource().equals(network2)){
				nettoggle = 1;
				System.out.println(nettoggle);
			}
		}
	};
	
	
	//Constructor
	public Display(){
		
				
		//Set up	
		console.setEditable(false);
		
		btnCompFile.addActionListener(al);					
		btnRun.addActionListener(al);
		
		btnTrainFile.addActionListener(al);
		btnTrain.addActionListener(al);
		
		network1.addActionListener(al);	
		network1.setSelected(true);
		network2.addActionListener(al);	
		
		
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
		
		//Add radio buttons to group
		netselect.add(network1);
		netselect.add(network2);
		radioPanel.add(network1);
		radioPanel.add(network2);
		
		//Add panels to frame
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(leftpanel, BorderLayout.WEST);
		frame.getContentPane().add(rightpanel, BorderLayout.SOUTH);
		frame.getContentPane().add(radioPanel, BorderLayout.EAST); 
		
		
	}
	
	
	//Methods
	public void drawWindow(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setNetworks(Classifier net1, Classifier net2){
		
		this.net1 = net1;
		this.net2 = net2;
		
	}
	
	
	//Set text in JTextArea
	public void setText(String str){
		
		console.append(str);
		
	}
	
	//Clear JTextArea
	public void clearText(){
		console.setText("");
	}
	
	
	
}
