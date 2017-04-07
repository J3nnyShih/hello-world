package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DepositGUI {
	private JFrame frame;
	private String ID;
	private ArrayList<String> accounts = new ArrayList<>();
	private ArrayList<String> transactions = new ArrayList<>();

	public DepositGUI(String ID) {
		frame = new JFrame();
		this.ID = ID;
	}

	public void run() {
		frame.setSize(600, 600);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.pink);
		frame.setTitle("Mini ATM - Deposit");
		frame.setLocationRelativeTo(null);

		JLabel amount = new JLabel("Please enter deposit amount: ");
		GridBagConstraints amountPosition = new GridBagConstraints();
		amountPosition.gridx = 0;
		amountPosition.gridy = 0;
		amount.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(amount, amountPosition);

		JTextField depositAmountTextField = new JTextField();
		GridBagConstraints depositAmountTextFieldPosition = new GridBagConstraints();
		depositAmountTextField.setPreferredSize(new Dimension(150, 40));
		depositAmountTextFieldPosition.gridx = 1;
		depositAmountTextFieldPosition.gridy = 0;
		depositAmountTextField.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(depositAmountTextField, depositAmountTextFieldPosition);
		
		JButton backToMainButton = new JButton("Return"); 
		  GridBagConstraints backToMainButtonPosition = new GridBagConstraints(); 
		  backToMainButton.setPreferredSize(new Dimension(150, 40)); 
		  backToMainButtonPosition.gridx = 1; 
		  backToMainButtonPosition.gridy = 5; 
		  backToMainButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
		  backToMainButton.addActionListener(new ActionListener() { 
		   @Override 
		   public void actionPerformed(ActionEvent e) { 
		    MainGUI gui = new MainGUI(ID); 
		    gui.run(); 
		    frame.setVisible(false); 

		   } 
		  }); 
		  frame.add(backToMainButton, backToMainButtonPosition); 


		JButton submitButton = new JButton("Submit");
		GridBagConstraints submitButtonPosition = new GridBagConstraints();
		submitButton.setPreferredSize(new Dimension(150, 40));
		submitButtonPosition.gridx = 1;
		submitButtonPosition.gridy = 1;
		submitButton.setFont(new Font("DialogInput", Font.PLAIN, 30));
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = depositAmountTextField.getText();
				int balance = 0;

				try {
					BufferedReader in = new BufferedReader(new FileReader("Accounts.txt"));
					String line;
					while ((line = in.readLine()) != null) {
						String[] temp = line.split(" ");
						if (ID.equals(temp[0])) {
							balance = Integer.parseInt(temp[2]);
							balance = balance + Integer.parseInt(amount);
							accounts.add(temp[0] + " " + temp[1] + " " + balance);
						}
						else {
							accounts.add(temp[0] + " " + temp[1] + " " + temp[2]);
						}
						
					}
					in.close();
				} catch (Exception e2) {

				}
				
				try{ 
				    PrintWriter writer = new PrintWriter("Accounts.txt", "UTF-8");
				    for (int i = 0; i < accounts.size(); i++) {
				    	writer.println(accounts.get(i));
				    	
					}
				    writer.close();
				    
				} catch (IOException f) { 
				   // do something 
				}
				try {
		    		BufferedReader in = new BufferedReader(new FileReader(ID + ".txt"));
		        	String line;
		        	while((line = in.readLine()) != null)
		        	{
		        		transactions.add(line);
		        	}
		        	in.close();
		        	
		        	try{ 
					    PrintWriter writer = new PrintWriter(ID + ".txt", "UTF-8");
					    for (int i = 0; i < transactions.size(); i++) {
							writer.println(transactions.get(i));
						}
					    writer.println(ID + " " + "Deposit" + " " + amount + " " + balance);
					    writer.close();
					} catch (IOException f) { 
					   // do something 
					}
				} catch (Exception e2) {
					try{ 
					    PrintWriter writer = new PrintWriter(ID + ".txt", "UTF-8");
					    writer.println(ID + " " + "Deposit" + " " + amount + " " + balance);
					    writer.close();
					} catch (IOException f) { 
					   // do something 
					}
					
				}
				
				
				TransactionDetailGUI gui = new TransactionDetailGUI(ID, "Deposit", amount, balance);
				gui.run();
				frame.setVisible(false);
			}
		});

		frame.add(submitButton, submitButtonPosition);

		frame.setVisible(true);

	}
}
