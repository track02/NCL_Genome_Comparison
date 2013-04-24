import javax.swing.*;


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
	private JPanel compbuttons = new JPanel();
	private JPanel trainbuttons = new JPanel();

	private JPanel tolbuttons = new JPanel();
	
	private JPanel rightpanel = new JPanel();
	
	//Buttons
	private JButton btnCompFile = new JButton("Select Comparison File");
	private JButton btnRun = new JButton("Start Program");
	
		
	private JButton btnTrainFile = new JButton("Select Training Data");
	private JButton btnTrain = new JButton("Train Network");
	
	//Button for submitting Tol value
	private JButton tolsub = new JButton("Submit");
	
	private JLabel results = new JLabel("Results Display");
	
	//Radio Buttons for choosing which network to train
	private JRadioButton network1 = new JRadioButton("Network 1 - Single Match Features");
	private JRadioButton network2 = new JRadioButton("Network 2 - Two Match Features");
	
	
	
	private ButtonGroup netselect = new ButtonGroup();
	JPanel radioPanel = new JPanel(new GridLayout(0,1));
	
	//Int to flag which button is pressed
	private int nettoggle = 0;
	
	//Text panel for console output
	private JTextArea console = new JTextArea(25,30);	
	private JScrollPane scroll = new JScrollPane(console);	
	
	//Text panel to display file names
	private JTextArea compfile = new JTextArea(1, 20);
	private JScrollPane cfile = new JScrollPane(compfile);
	private JTextArea testfile = new JTextArea(1, 20);
	private JScrollPane tfile = new JScrollPane(testfile);
	
	//Text panel for user to input tol value
	private JTextArea tolv = new JTextArea(1,6);
	private JScrollPane tolscroll = new JScrollPane(tolv);
		
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
							setCFileText(compf.getName());
							BlastReader.setComp(compf);		
							System.out.println(compf.getName());
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}			
					}
					else{
						
						trainf = filec.getSelectedFile();
						setTFileText(trainf.getName());
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
			
			if(e.getSource().equals(tolsub)){	
				Main.setlimit(Integer.parseInt(tolv.getText()));		
				setResultText("Tolerance Value Set: " + Integer.parseInt(tolv.getText()));
			}
		}
	};
	
	
	//Constructor
	public Display(){
		
		//System.out.println(btnCompFile.getSize().height + "," + btnCompFile.getSize())
		
		results.setHorizontalAlignment(SwingConstants.CENTER);
		
		rightpanel.setLayout(new BorderLayout());
		leftpanel.setLayout(new GridLayout(9, 8));

		
		//Set up	
		console.setEditable(false);
		compfile.setEditable(false);
		testfile.setEditable(false);
		
		btnCompFile.addActionListener(al);					
		btnRun.addActionListener(al);
		
		btnTrainFile.addActionListener(al);
		btnTrain.addActionListener(al);
		
		network1.addActionListener(al);	
		network1.setSelected(true);
		network2.addActionListener(al);	
		
		tolsub.addActionListener(al);
	
		
		//Add menu bar to frame
		frame.setJMenuBar(menu);
		
		//Add sub menu to each button
		mfile.add(miSave);
		mfile.add(miQuit);
		mhelp.add(miStart);
		
		//Add buttons to menu
		menu.add(mfile);
		menu.add(mhelp);
		
		//Add buttons to panels
		compbuttons.add(btnCompFile);
		compbuttons.add(cfile);
		trainbuttons.add(btnTrainFile);
		trainbuttons.add(tfile);
		compbuttons.add(btnRun);		
		trainbuttons.add(btnTrain);
		//trainbuttons.add(radioPanel);
		tolbuttons.add(tolscroll);
		tolbuttons.add(tolsub);
		//runbuttons.add(tolbuttons, BorderLayout.NORTH);
		//runbuttons.add(radioPanel, BorderLayout.SOUTH);
		
		
		network1.setHorizontalAlignment(SwingConstants.CENTER);
		network2.setHorizontalAlignment(SwingConstants.CENTER);
		
		leftpanel.add(new JLabel("Select Comparison File & Run", SwingConstants.CENTER));
		leftpanel.add(compbuttons);
		leftpanel.add(new JLabel("Set Data & Train Network", SwingConstants.CENTER));
		leftpanel.add(trainbuttons);
		leftpanel.add(new JLabel("Select Network for Training", SwingConstants.CENTER));
		leftpanel.add(radioPanel);
		leftpanel.add(new JLabel(""));
		leftpanel.add(new JLabel("Set Tolerance Value", SwingConstants.CENTER));
		leftpanel.add(tolbuttons);

				
		//Add text pane to right
		rightpanel.add(results, BorderLayout.NORTH);
		rightpanel.add(scroll, BorderLayout.CENTER);
		
		//Add radio buttons to group
		netselect.add(network1);
		netselect.add(network2);
		radioPanel.add(network1);
		radioPanel.add(network2);
		
		//Add panels to frame
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(leftpanel, BorderLayout.WEST);
		frame.getContentPane().add(rightpanel, BorderLayout.EAST);
		//frame.getContentPane().add(radioPanel, BorderLayout.NORTH); 
		
		
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
	public void setResultText(String str){
		
		console.append(str);
		
	}
	
	public void setCFileText(String str){
		
		compfile.setText("");
		compfile.setText(str);		
	}
	
	public void setTFileText(String str){
		
		testfile.setText("");
		testfile.setText(str);
		
	}
	
	
	
	//Clear JTextArea
	public void clearText(){
		console.setText("");
	}
	
	
	
}
