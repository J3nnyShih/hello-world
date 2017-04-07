package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewAccount {
	private JFrame frame;
	private ArrayList<String> accounts = new ArrayList<>();
	
	public NewAccount(){
		frame = new JFrame();
		
	}
	public void run(){
		frame.setSize(1000, 500); 
        frame.setLayout(new GridBagLayout()); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.pink);
        frame.setTitle("Mini ATM - New Account");
        frame.setLocationRelativeTo(null);
        
        JLabel accountIDLabel = new JLabel("Please enter your account ID: "); 
        GridBagConstraints accountIDLabelPosition = new GridBagConstraints(); 
        accountIDLabelPosition.gridx = 0; 
        accountIDLabelPosition.gridy = 0; 
        accountIDLabel.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(accountIDLabel,accountIDLabelPosition); 
        
        JLabel accountPasswordLabel = new JLabel("Please enter your account password: "); 
        GridBagConstraints accountPasswordLabelPosition = new GridBagConstraints(); 
        accountPasswordLabelPosition.gridx = 0; 
        accountPasswordLabelPosition.gridy = 1; 
        accountPasswordLabel.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(accountPasswordLabel,accountPasswordLabelPosition); 
        
        JLabel recheckLabel = new JLabel("Please enter your password again: "); 
        GridBagConstraints recheckLabelPosition = new GridBagConstraints(); 
        recheckLabelPosition.gridx = 0; 
        recheckLabelPosition.gridy = 2; 
        recheckLabel.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(recheckLabel,recheckLabelPosition); 
        
        JTextField accountIDTextField = new JTextField(); 
        GridBagConstraints accountIDTextFieldPosition = new GridBagConstraints(); 
        accountIDTextField.setPreferredSize(new Dimension(150, 40)); 
        accountIDTextFieldPosition.gridx = 1; 
        accountIDTextFieldPosition.gridy = 0; 
        accountIDTextField.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(accountIDTextField,accountIDTextFieldPosition);
        
        JTextField accountPasswordTextField = new JTextField(); 
        GridBagConstraints accountPasswordTextFieldPosition = new GridBagConstraints(); 
        accountPasswordTextField.setPreferredSize(new Dimension(150, 40)); 
        accountPasswordTextFieldPosition.gridx = 1; 
        accountPasswordTextFieldPosition.gridy = 1; 
        accountPasswordTextField.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(accountPasswordTextField,accountPasswordTextFieldPosition);
        
        JTextField recheckTextField = new JTextField(); 
        GridBagConstraints recheckTextFieldPosition = new GridBagConstraints(); 
        recheckTextField.setPreferredSize(new Dimension(150, 40)); 
        recheckTextFieldPosition.gridx = 1; 
        recheckTextFieldPosition.gridy = 2; 
        recheckTextField.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(recheckTextField,recheckTextFieldPosition);
        
        JButton submitButton = new JButton("Submit"); 
        GridBagConstraints submitButtonPosition = new GridBagConstraints(); 
        submitButton.setPreferredSize(new Dimension(150, 40)); 
        submitButtonPosition.gridx = 1; 
        submitButtonPosition.gridy = 3; 
        submitButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputID = accountIDTextField.getText();
				
				if (idHasBeenUsed(inputID)) {
					JDialog d = new JDialog(frame, "This ID has already been used", true); 
					d.setSize(800, 50);
				    d.setLocationRelativeTo(frame); 
				    d.setVisible(true);
					
				}else {
					if (!accountPasswordTextField.getText().equals(recheckTextField.getText())) {
						JDialog a = new JDialog(frame, "Password doesn't match", true);
						a.setSize(800, 50);
					    a.setLocationRelativeTo(frame); 
					    a.setVisible(true);
					}else {
						try{ 
						    PrintWriter writer = new PrintWriter("Accounts.txt", "UTF-8");
						    for (int i = 0; i < accounts.size(); i++) {
						    	writer.println(accounts.get(i));
							}
						    writer.println(inputID+" "+accountPasswordTextField.getText()+" "+0); 
						    writer.close(); 
						} catch (IOException f) { 
						   // do something 
						}
						LogInGUI gui = new LogInGUI();
						gui.run();     
						frame.setVisible(false);
					}
				}
				
			}
		});
        frame.add(submitButton,submitButtonPosition); 
        
        frame.setVisible(true);
	}
	public boolean idHasBeenUsed(String inputID) {
		ArrayList<String> id = new ArrayList<>();
    	try {
    		BufferedReader in = new BufferedReader(new FileReader("Accounts.txt"));
        	String line;
        	while((line = in.readLine()) != null)
        	{
        	    String[] temp = line.split(" ");
        	    id.add(temp[0]);
        	    accounts.add(line);
        	}
        	in.close();	
		} catch (Exception e2) {
			try{ 
			    PrintWriter writer = new PrintWriter("Accounts.txt", "UTF-8"); 				 
			    writer.close();
			    return false;
			} catch (IOException f) { 
			   // do something 
			}
			System.out.println("Accounts.txt file doesn't exist!");
		}
		
		for (int i = 0; i < id.size(); i++) {
			if (inputID.equals(id.get(i))) {
				return true;
			}
		}
		return false;
	}
}
