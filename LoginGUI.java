package test;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


import javax.swing.*;
 

public class LogInGUI { 
    private JFrame frame; 
    private String[] name; 

    public LogInGUI(){ 
        frame = new JFrame(); 
        String n[] = {"User name","Password","Welcome","Login","New Account"}; 
        name = n; 
    } 
    public void run(){ 
        frame.setSize(600, 160); 
        frame.setLayout(new GridBagLayout()); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.getContentPane().setBackground(Color.pink);
        frame.setTitle("Mini ATM");
        frame.setLocationRelativeTo(null);
        
        JLabel n0 = new JLabel(name[0]); 
        GridBagConstraints c0 = new GridBagConstraints();
        n0.setForeground(Color.darkGray);
        c0.gridx = 0; 
        c0.gridy = 0; 
        c0.gridheight = 1; 
        c0.gridwidth = 1; 
        c0.weightx = 0; 
        c0.weighty = 0; 
        c0.fill = GridBagConstraints.NONE; 
        c0.anchor = GridBagConstraints.WEST; 
        frame.add(n0,c0); 

        JLabel n1 = new JLabel(name[1]); 
        GridBagConstraints c1 = new GridBagConstraints();
        n1.setForeground(Color.darkGray);
        c1.gridx = 0; 
        c1.gridy = 1; 
        c1.gridheight = 1; 
        c1.gridwidth = 1; 
        c1.weightx = 0; 
        c1.weighty = 0; 
        c1.fill = GridBagConstraints.NONE; 
        c1.anchor = GridBagConstraints.WEST; 

        frame.add(n1,c1); 

        JLabel n2 = new JLabel(name[2]); 
        GridBagConstraints c2 = new GridBagConstraints();
        n2.setForeground(Color.darkGray);
        c2.gridx = 0; 
        c2.gridy = 3; 
        c2.gridheight = 7; 
        c2.gridwidth = 1; 
        c2.weightx = 0; 
        c2.weighty = 0; 
        c2.fill = GridBagConstraints.BOTH; 
        c2.anchor = GridBagConstraints.WEST; 

        frame.add(n2,c2); 

        JTextField n3 = new JTextField(); 
        GridBagConstraints c3 = new GridBagConstraints(); 
        c3.gridx = 1; 
        c3.gridy = 0; 
        c3.gridheight = 1; 
        c3.gridwidth = 6; 
        c3.weightx = 0; 
        c3.weighty = 0; 
        c3.fill = GridBagConstraints.BOTH; 
        c3.anchor = GridBagConstraints.WEST; 

        frame.add(n3,c3); 

        JPasswordField n4 = new JPasswordField(); 
        n4.setEchoChar('*');
        GridBagConstraints c4 = new GridBagConstraints(); 
        c4.gridx = 1; 
        c4.gridy = 1; 
        c4.gridheight = 1; 
        c4.gridwidth = 6; 
        c4.weightx = 0; 
        c4.weighty = 0; 
        c4.fill = GridBagConstraints.BOTH; 
        c4.anchor = GridBagConstraints.WEST; 

        frame.add(n4,c4); 

        JButton n5 = new JButton(name[3]); 
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0; 
        c5.gridy = 2; 
        c5.gridheight = 1; 
        c5.gridwidth = 1; 
        c5.weightx = 0; 
        c5.weighty = 0; 
        c5.fill = GridBagConstraints.BOTH; 
        c5.anchor = GridBagConstraints.CENTER; 

        n5.addActionListener(new ActionListener() { 
            @Override 
            public void actionPerformed(ActionEvent e) {
            	ArrayList<String> id = new ArrayList<>();
            	ArrayList<String> password = new ArrayList<>();
            	try {
            		BufferedReader in = new BufferedReader(new FileReader("Accounts.txt"));
                	String line;
                	while((line = in.readLine()) != null)
                	{
                	    String[] temp = line.split(" ");
                	    id.add(temp[0]);
                	    password.add(temp[1]);
                	}
                	in.close();	
				} catch (Exception e2) {
					
					System.out.println("Accounts.txt file doesn't exist!");
				}
            
            	
        
            	for (int i = 0; i < id.size(); i++) {
					if (n3.getText().equals(id.get(i))) {
						if (n4.getText().equals(password.get(i))) {
							MainGUI gui = new MainGUI (n3.getText());
		        				gui.run();     
		        				frame.setVisible(false);
						}else {
							JDialog d = new JDialog(frame, "Wrong password", true); 
							d.setSize(800, 50);
						    d.setLocationRelativeTo(frame); 
						    d.setVisible(true);
						}
						i = id.size();
					}
					String ID = n3.getText();
					if (i == id.size()-1 && !ID.equals(id.get(i))) {
						JDialog h = new JDialog(frame, "Account ID not found", true);
						h.setSize(800, 50);
					    h.setLocationRelativeTo(frame); 
					    h.setVisible(true);
					}
						
					
				}
            	
            	
            	
                 
                 
            } 
        }); 

        frame.add(n5,c5); 

 

        JButton n6 = new JButton(name[4]); 
        GridBagConstraints c6 = new GridBagConstraints(); 
        c6.gridx = 1; 
        c6.gridy = 2; 
        c6.gridheight = 1; 
        c6.gridwidth = 1; 
        c6.weightx = 0; 
        c6.weighty = 0; 
        c6.fill = GridBagConstraints.BOTH; 
        c6.anchor = GridBagConstraints.CENTER; 
        n6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewAccount gui = new NewAccount();
				gui.run();     
				frame.setVisible(false);
				
			}
		});

        frame.add(n6,c6); 

 
        frame.setVisible(true); 
    } 

}
