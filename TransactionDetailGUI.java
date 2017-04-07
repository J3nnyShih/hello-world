package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TransactionDetailGUI {
	 private JFrame frame; 
	 private String type;
	 private String amount;
	 private String ID;
	 private int balance;
	    public TransactionDetailGUI(String ID,String type,String amount,int balance) {
	        frame = new JFrame();
	        this.ID = ID;
	        this.type = type;
	        this.amount = amount;
	        this.balance = balance;
	        
	    } 
	    public void run() { 
	        frame.setSize(1000, 500); 
	        frame.setLayout(new GridBagLayout()); 
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.getContentPane().setBackground(Color.pink);
	        frame.setTitle("Mini ATM - Transaction Overview");
	        frame.setLocationRelativeTo(null);
	        
	        JTextArea successLabel = new JTextArea(type + " Success!!\n" +type+": $"+amount+"\nYour account balance:"+ balance); 
	        successLabel.setEditable(false);
	        successLabel.setBackground(SystemColor.control);
	        GridBagConstraints successLabelPosition = new GridBagConstraints(); 
	        successLabelPosition.gridx = 0; 
	        successLabelPosition.gridy = 0; 
	        successLabelPosition.anchor = GridBagConstraints.WEST; 
	        successLabel.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
	        frame.add(successLabel,successLabelPosition); 
	        
	        JButton backToMainButton = new JButton("Confirm"); 
	        GridBagConstraints backToMainButtonPosition = new GridBagConstraints(); 
	        backToMainButton.setPreferredSize(new Dimension(150, 50)); 
	        backToMainButtonPosition.gridx = 0; 
	        backToMainButtonPosition.gridy = 1; 
	        backToMainButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
	        backToMainButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MainGUI gui = new MainGUI(ID);
					gui.run();     
					frame.setVisible(false);
					
				}
			});
	        frame.add(backToMainButton,backToMainButtonPosition);
	        frame.setVisible(true);
}
}
