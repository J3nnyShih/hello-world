package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

public class TransferGUI {
	private JFrame frame;
	private String ID;
	private ArrayList<String> accounts = new ArrayList<>();
	private ArrayList<String> transactions = new ArrayList<>();
	private int selfBalance = 0;

	public TransferGUI(String ID) {
		frame = new JFrame();
		this.ID = ID;

	}

	public void run() {
		frame.setSize(600, 600);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.pink);
		frame.setTitle("Mini ATM - Transfer");
		frame.setLocationRelativeTo(null);

		JLabel amount = new JLabel("Please enter transfer amount: ");
		GridBagConstraints amountPosition = new GridBagConstraints();
		amountPosition.gridx = 0;
		amountPosition.gridy = 0;
		amount.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(amount, amountPosition);

		JTextField transferAmountTextField = new JTextField();
		GridBagConstraints trasferAmountTextFieldPosition = new GridBagConstraints();
		transferAmountTextField.setPreferredSize(new Dimension(150, 40));
		trasferAmountTextFieldPosition.gridx = 1;
		trasferAmountTextFieldPosition.gridy = 0;
		transferAmountTextField.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(transferAmountTextField, trasferAmountTextFieldPosition);

		JLabel depositAccount = new JLabel("Please enter Deposit Account: ");
		GridBagConstraints depositAccountPosition = new GridBagConstraints();
		depositAccountPosition.gridx = 0;
		depositAccountPosition.gridy = 1;
		depositAccount.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(depositAccount, depositAccountPosition);

		JTextField depositAccountTextField = new JTextField();
		GridBagConstraints depositAccountTextFieldPosition = new GridBagConstraints();
		depositAccountTextField.setPreferredSize(new Dimension(150, 40));
		depositAccountTextFieldPosition.gridx = 1;
		depositAccountTextFieldPosition.gridy = 1;
		depositAccountTextField.setFont(new Font("DialogInput", Font.PLAIN, 30));
		frame.add(depositAccountTextField, depositAccountTextFieldPosition);
		
		JButton backToMainButton = new JButton("Return"); 
		  GridBagConstraints backToMainButtonPosition = new GridBagConstraints(); 
		  backToMainButton.setPreferredSize(new Dimension(150, 40)); 
		  backToMainButtonPosition.gridx = 1; 
		  backToMainButtonPosition.gridy = 3; 
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
		submitButtonPosition.gridy = 2;
		submitButton.setFont(new Font("DialogInput", Font.PLAIN, 30));
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String amount = "-" + transferAmountTextField.getText();
				int balance = 0;
				boolean balanceEnough = false;
				boolean depositAccountExist = false;
				try {
					BufferedReader in = new BufferedReader(new FileReader("Accounts.txt"));
					String line;
					while ((line = in.readLine()) != null) {
						String[] temp = line.split(" ");
						if (depositAccountTextField.getText().equals(temp[0])) {
							depositAccountExist = true;
						}
					}
					in.close();
					BufferedReader in2 = new BufferedReader(new FileReader("Accounts.txt"));
					while ((line = in2.readLine()) != null) {
						String[] temp = line.split(" ");
						if (ID.equals(temp[0])) {
							balance = Integer.parseInt(temp[2]);
							if (balance > Integer.parseInt(amount.split("-")[1])) {
								balanceEnough = true;
								balance = balance + Integer.parseInt(amount);
								accounts.add(temp[0] + " " + temp[1] + " " + balance);
							}
						} else {
							accounts.add(temp[0] + " " + temp[1] + " " + temp[2]);
						}

					}
					in2.close();
				} catch (Exception e2) {

				}
				if (balanceEnough && depositAccountExist) {

					try {
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
						while ((line = in.readLine()) != null) {
							transactions.add(line);
						}
						in.close();

						try {
							PrintWriter writer = new PrintWriter(ID + ".txt", "UTF-8");
							for (int i = 0; i < transactions.size(); i++) {
								writer.println(transactions.get(i));
							}
							writer.println(ID + " " + "Transfer" + " " + amount + " " + balance);
							selfBalance = balance;
							writer.close();
						} catch (IOException f) {
							// do something
						}
					} catch (Exception e2) {
						try {
							PrintWriter writer = new PrintWriter(ID + ".txt", "UTF-8");
							writer.println(ID + " " + "Transfer" + " " + amount + " " + balance);
							writer.close();
						} catch (IOException f) {
							// do something
						}

					}

					try {
						accounts = new ArrayList<>();
						BufferedReader in = new BufferedReader(new FileReader("Accounts.txt"));
						String line;
						while ((line = in.readLine()) != null) {
							String[] temp = line.split(" ");
							if (depositAccountTextField.getText().equals(temp[0])) {
								balance = Integer.parseInt(temp[2]);
								balance = balance - Integer.parseInt(amount);
								accounts.add(temp[0] + " " + temp[1] + " " + balance);
							}
							else {
								accounts.add(temp[0] + " " + temp[1] + " " + temp[2]);
							}
							
						}
						in.close();
					} catch (Exception e2) {

					}

					try {
						PrintWriter writer = new PrintWriter("Accounts.txt", "UTF-8");
						for (int i = 0; i < accounts.size(); i++) {
							writer.println(accounts.get(i));

						}
						writer.close();

					} catch (IOException f) {
						// do something
					}
					try {
						try {
							BufferedReader in = new BufferedReader(new FileReader(depositAccountTextField.getText() + ".txt"));
							
						} catch (Exception e2) {
							PrintWriter writer = new PrintWriter(depositAccountTextField.getText() + ".txt", "UTF-8");
							writer.close();
						}
						BufferedReader in = new BufferedReader(new FileReader(depositAccountTextField.getText() + ".txt"));
						String line;
						transactions = new ArrayList<>();
						while ((line = in.readLine()) != null) {
							transactions.add(line);
						}
						in.close();

						try {
							PrintWriter writer = new PrintWriter(depositAccountTextField.getText() + ".txt", "UTF-8");
							for (int i = 0; i <transactions.size(); i++) {
								writer.println(transactions.get(i));
							}
							writer.println(ID + " " + "Transfer" + " "
									+ amount.split("-")[1] + " " + balance);
							writer.close();
						} catch (IOException f) {
							 //do something
						}
					} catch (Exception e2) {
//						try {
//							PrintWriter writer = new PrintWriter(depositAccountTextField.getText() + ".txt", "UTF-8");
//							writer.println(depositAccountTextField.getText() + " " + "Transfer" + " " + amount + " "
//									+ balance);
//							writer.close();
//						} catch (IOException f) {
//							// do something
//						}

					}

					TransactionDetailGUI gui = new TransactionDetailGUI(ID, "Transfer", amount, selfBalance);
					gui.run();
					frame.setVisible(false);

				} else {
					if (!balanceEnough) {
						JDialog h = new JDialog(frame, "You don't have enough balance!", true);
						h.setSize(800, 50);
						h.setLocationRelativeTo(frame);
						h.setVisible(true);
					} else if (!depositAccountExist) {
						JDialog h = new JDialog(frame, "Deposit account doesn't exist!", true);
						h.setSize(800, 50);
						h.setLocationRelativeTo(frame);
						h.setVisible(true);
					}
				}

			}
		});
		frame.add(submitButton, submitButtonPosition);
		frame.setVisible(true);

	}
}
