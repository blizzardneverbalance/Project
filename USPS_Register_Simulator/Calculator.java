
import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.Queue;
import java.util.Scanner; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Calculator {
	static Scanner userInput;
	//Represent number of people in wait list.
	private static int peopleOfWait;
	//List of estimate time of each customer in wait list.
	static Queue<Double> timeList; 
	//List of name of each customer in wait list.
	static Queue<String> nameList;
	//Represent if the customer needs these services.
	static int sv1, sv2, sv3, sv4, sv5 = 0;
	//Second page.
	static JFrame frame2;
	//Front page.
	static JFrame frame;
	//Estimate time of each operation.
	private final static double timeOfEachOperation = 0.5;
	
	//Constructor.  Initialize wait list.
	public Calculator() {
		peopleOfWait = 0;
		timeList = new LinkedList<>();
		nameList = new LinkedList<>();
	}
	
	//Count how many people in wait list.
	public int countPeople() {
		return peopleOfWait;
	}
	
	//For staff. Call the first person in wait list.
	public String callPerson() {
		return nameList.peek();
	}
	
	//Add name into wait list.
	public void signUpWithName(String name) {
		nameList.add(name);
	}
	
	//Sign up a person with the service he needs.
	public static void signUp(int sv1, int sv2, int sv3, int sv4, int sv5) {
		if (!(sv1 + sv2 + sv3 + sv4 + sv5 == 0)) peopleOfWait++;
		//The coefficient can be changed by actual situation.
		timeList.add((10*sv1 + 5*sv2 + 3*sv3 + sv4 + sv5) * timeOfEachOperation);
	}
	
	//Subtract a person from wait list since his job is done.
	public static void subtract() {
		peopleOfWait--;
		timeList.remove();
		nameList.remove();
	}
	
	//For customer to check how many people are ahead of him and how many time he needs to wait.
	public static void check() {
		double timeOfWait = 0;
		for(Double d : timeList) {
			timeOfWait += d;
		}
		System.out.println("There are " + peopleOfWait + " people ahead of you.");
		System.out.println("Estimate waiting time is " + timeOfWait + " minutes.");
	}
	
	public static void frame() {
		JTextArea nameField;

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.add(new JLabel("Enter your name to be called:"), BorderLayout.NORTH);
		nameField = new JTextArea(3, 75);
		namePanel.add(nameField, BorderLayout.CENTER);

		
		namePanel.setBackground(Color.YELLOW);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new OkButton());
		
		
		// Adding buttons to to panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		
		frame2 = new JFrame("Welcome to the USPS website");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(namePanel, BorderLayout.NORTH);
		frame2.add(buttonPanel, BorderLayout.SOUTH);

		frame2.pack();
		frame2.setVisible(true);
		frame2.setSize(500,500);
	}
	
	private static void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		frame.setVisible(false);
		frame2.setVisible(true);
    }
	
	static class OkButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			// Creating a label for text (instructions)
			// Adding label to panel
			frame2.setVisible(false);
			JPanel instructionPanel = new JPanel();
			instructionPanel.setLayout(new BorderLayout());
			instructionPanel.add(new JLabel("Select the services you need assistance with:"), BorderLayout.NORTH);
			//Create service bottoms.
			jToggleButton1 = new javax.swing.JToggleButton();
			jToggleButton1.setText("P.O. Box");
			jToggleButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jToggleButton1.isSelected()) sv1 = 1;
					else sv1 = 0;
				}
			});
			
			jToggleButton2 = new javax.swing.JToggleButton();
			jToggleButton2.setText("Delivery Inquiry");
			jToggleButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jToggleButton2.isSelected()) sv2 = 1;
					else sv2 = 0;
				}
			});
			
			jToggleButton3 = new javax.swing.JToggleButton();
			jToggleButton3.setText("Tracking Info");
			jToggleButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jToggleButton3.isSelected()) sv3 = 1;
					else sv3 = 0;
				}
			});
			
			jToggleButton4 = new javax.swing.JToggleButton();
			jToggleButton4.setText("Retail");
			jToggleButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jToggleButton4.isSelected()) sv4 = 1;
					else sv4 = 0;
				}
			});
			
			jToggleButton5 = new javax.swing.JToggleButton();
			jToggleButton5.setText("Mail");
			jToggleButton5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (jToggleButton5.isSelected()) sv5 = 1;
					else sv5 = 0;
				}
			});
			
			// Adding buttons to panel
			JPanel keyPanel = new JPanel();
			keyPanel.setLayout(new GridLayout(4, 3));
			keyPanel.add(jToggleButton1);
			keyPanel.add(jToggleButton2);
			keyPanel.add(jToggleButton3);
			keyPanel.add(jToggleButton4);
			keyPanel.add(jToggleButton5);
			
			// Creating Check, Confirm, and Cancel buttons
						 
			JButton checkButton = new JButton("Check");
			checkButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					check();
				}
			});
						 
			jButton1 = new javax.swing.JButton();
			jButton1.setText("Confirm");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	signUp(sv1, sv2, sv3, sv4, sv5);
	            	jButton2ActionPerformed(evt);
	            }
	        });

			jButton2 = new javax.swing.JButton();
			jButton2.setText("Exit");
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	jButton2ActionPerformed(evt);
	            }
	        });

			// Adding buttons to to panel
			JPanel buttonPanel = new JPanel();
			buttonPanel.add(checkButton);
			buttonPanel.add(jButton1);
			buttonPanel.add(jButton2);
						
			// Adding all the panels to frame
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(instructionPanel, BorderLayout.NORTH);
			frame.add(keyPanel, BorderLayout.CENTER);
			frame.add(buttonPanel, BorderLayout.SOUTH);

			frame.pack();
			frame.setVisible(true);
			frame.setSize(500, 500);
		}	
	}
	
	public static void main(String[] args) {
		Calculator c = new Calculator();
		frame();
	}
	
	private static javax.swing.JButton jButton1;
	private static javax.swing.JButton jButton2;
	private static javax.swing.JToggleButton jToggleButton1;
    private static javax.swing.JToggleButton jToggleButton2;
    private static javax.swing.JToggleButton jToggleButton3;
    private static javax.swing.JToggleButton jToggleButton4;
    private static javax.swing.JToggleButton jToggleButton5;
}
