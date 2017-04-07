package test; 

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class MainGUI { 
    private JFrame frame; 
    private String ID;
    public MainGUI(String ID){
    		this.ID = ID;
        frame = new JFrame(); 
    } 
    public void run() { 
        frame.setSize(600, 600); 
        frame.setLayout(new GridBagLayout()); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().setBackground(Color.pink);
        frame.setTitle("Mini ATM - Main");
        frame.setLocationRelativeTo(null);
        
        JButton depositButton = new JButton("Deposit"); 
        GridBagConstraints depositButtonPosition = new GridBagConstraints(); 
        depositButton.setPreferredSize(new Dimension(150, 40)); 
        depositButtonPosition.gridx = 0; 
        depositButtonPosition.gridy = 1; 
        depositButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        depositButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DepositGUI gui = new DepositGUI(ID);
				gui.run();     
				frame.setVisible(false);
				
			}
		});
        frame.add(depositButton,depositButtonPosition); 

        JButton withdrawButton = new JButton("Withdraw"); 
        GridBagConstraints withdrawButtonPosition = new GridBagConstraints(); 
        withdrawButton.setPreferredSize(new Dimension(150, 40)); 
        withdrawButtonPosition.gridx = 0; 
        withdrawButtonPosition.gridy = 2; 
        withdrawButton.setFont(new Font("DialogInput", Font.PLAIN, 30));
        withdrawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WithdrawGUI gui = new WithdrawGUI(ID);
				gui.run();     
				frame.setVisible(false);
				
			}
		});
        frame.add(withdrawButton,withdrawButtonPosition); 

        JButton transferButton = new JButton("Transfer"); 
        GridBagConstraints transferButtonPosition = new GridBagConstraints(); 
        transferButton.setPreferredSize(new Dimension(150, 40)); 
        transferButtonPosition.gridx = 0; 
        transferButtonPosition.gridy = 3; 
        transferButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        transferButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransferGUI gui = new TransferGUI(ID);
				gui.run();     
				frame.setVisible(false);
				
			}
		});
        frame.add(transferButton,transferButtonPosition); 

        JLabel welcomeLabel = new JLabel("Welcome, "+ID); 
        GridBagConstraints welcomeLabelPosition = new GridBagConstraints(); 
        welcomeLabelPosition.gridx = 0; 
        welcomeLabelPosition.gridy = 0; 
        welcomeLabelPosition.anchor = GridBagConstraints.WEST; 
        welcomeLabel.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        frame.add(welcomeLabel,welcomeLabelPosition);
        
        JButton transactionOverviewButton = new JButton("Transaction\nOverview"); 
        GridBagConstraints transactionOverviewButtonPosition = new GridBagConstraints(); 
        transactionOverviewButton.setPreferredSize(new Dimension(400, 40)); 
        transactionOverviewButtonPosition.gridx = 0; 
        transactionOverviewButtonPosition.gridy = 4; 
        transactionOverviewButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        transactionOverviewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TransactionOverviewGUI gui = new TransactionOverviewGUI(ID);
				gui.run();     
				frame.setVisible(false);
				
			}
		});
        frame.add(transactionOverviewButton,transactionOverviewButtonPosition);

        JButton logoutButton = new JButton("Logout"); 
        GridBagConstraints logoutButtonPosition = new GridBagConstraints(); 
        logoutButton.setPreferredSize(new Dimension(150, 40)); 
        logoutButtonPosition.gridx = 0; 
        logoutButtonPosition.gridy = 5; 
        logoutButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
        logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInGUI gui = new LogInGUI();
				gui.run();     
				frame.setVisible(false);
				
			}
		});
        frame.add(logoutButton,logoutButtonPosition); 

        frame.setVisible(true); 

 
    } 

}
