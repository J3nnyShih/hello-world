package test; 

import java.awt.Color;
import java.awt.Dimension; 
import java.awt.Font; 
import java.awt.GridBagConstraints; 
import java.awt.GridBagLayout; 
import java.awt.SystemColor; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.io.BufferedReader; 
import java.io.FileReader; 


import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JScrollPane; 
import javax.swing.JTextArea; 
import javax.swing.ScrollPaneConstants; 



public class TransactionOverviewGUI { 
 private JFrame frame; 
 private String ID; 
 private boolean fromNewToOld = true; 
 private boolean sortByType = false; 
 private JTextArea transactionOverviewTextArea = new JTextArea("                                            \n                                            \n                                            \n                                            \n                                            \n"); 

 public TransactionOverviewGUI(String ID) { 
  this.ID = ID; 
  frame = new JFrame(); 
 } 

 public void run() { 
  frame.setSize(600, 600); 
  frame.setLayout(new GridBagLayout()); 
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  frame.getContentPane().setBackground(Color.pink);
  frame.setTitle("Mini ATM");
  frame.setLocationRelativeTo(null);
  
  JButton latestButton = new JButton("New-Old"); 
  GridBagConstraints latestButtonPosition = new GridBagConstraints(); 
  latestButton.setPreferredSize(new Dimension(400, 40)); 
  latestButtonPosition.gridx = 0; 
  latestButtonPosition.gridy = 0; 
  latestButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
  latestButton.addActionListener(new ActionListener() { 

   @Override 
   public void actionPerformed(ActionEvent e) { 

    fromNewToOld = true; 
    sortAndDisplayResult(); 
   } 

  }); 
  frame.add(latestButton, latestButtonPosition); 

  JButton oldestButton = new JButton("Old-New"); 
  GridBagConstraints oldestButtonPosition = new GridBagConstraints(); 
  oldestButton.setPreferredSize(new Dimension(400, 40)); 
  oldestButtonPosition.gridx = 0; 
  oldestButtonPosition.gridy = 1; 
  oldestButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
  oldestButton.addActionListener(new ActionListener() { 

   @Override 
   public void actionPerformed(ActionEvent e) { 

    fromNewToOld = false; 
    sortAndDisplayResult(); 
   } 
  }); 
  frame.add(oldestButton, oldestButtonPosition); 

  JButton sortByTypeButton = new JButton("Sort by transaction type"); 
  GridBagConstraints sortByTypeButtonPosition = new GridBagConstraints(); 
  sortByTypeButton.setPreferredSize(new Dimension(400, 40)); 
  sortByTypeButtonPosition.gridx = 0; 
  sortByTypeButtonPosition.gridy = 2; 
  sortByTypeButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
  sortByTypeButton.addActionListener(new ActionListener() { 

   @Override 
   public void actionPerformed(ActionEvent e) { 

    sortByType = true; 
    sortAndDisplayResult(); 

   } 
  }); 
  frame.add(sortByTypeButton, sortByTypeButtonPosition); 

  JButton sortByTimeButton = new JButton("Sort by transaction date"); 
  GridBagConstraints sortByTimeButtonPosition = new GridBagConstraints(); 
  sortByTimeButton.setPreferredSize(new Dimension(400, 40)); 
  sortByTimeButtonPosition.gridx = 0; 
  sortByTimeButtonPosition.gridy = 3; 
  sortByTimeButton.setFont(new Font("DialogInput", Font.PLAIN, 30)); 
  sortByTimeButton.addActionListener(new ActionListener() { 

   @Override 
   public void actionPerformed(ActionEvent e) { 
    sortByType = false; 
    sortAndDisplayResult(); 

   } 
  }); 
  frame.add(sortByTimeButton, sortByTimeButtonPosition); 

  transactionOverviewTextArea.setEditable(false); 
  transactionOverviewTextArea.setBackground(SystemColor.control); 

  JScrollPane scroll = new JScrollPane(transactionOverviewTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// 設定為永久顯示Y軸捲動容器 
  scroll.setSize(400, 80); 
  scroll.setHorizontalScrollBarPolicy( 
    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
  scroll.setVerticalScrollBarPolicy( 
    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
//  scroll.setLocation(0, 0); 
  //transactionOverviewTextArea.setEditable(false); 

  GridBagConstraints transactionOverviewTextAreaPosition = new GridBagConstraints(); 
  transactionOverviewTextAreaPosition.gridx = 0; 
  transactionOverviewTextAreaPosition.gridy = 4; 
  transactionOverviewTextAreaPosition.anchor = GridBagConstraints.WEST; 
  transactionOverviewTextArea.setFont(new Font("DialogInput", Font.PLAIN, 30)); 

  // frame.add(transactionOverviewTextArea,transactionOverviewTextAreaPosition); 
  frame.add(scroll, transactionOverviewTextAreaPosition); 
//  sortAndDisplayResult(); 

  JButton backToMainButton = new JButton("Confirm"); 
  GridBagConstraints backToMainButtonPosition = new GridBagConstraints(); 
  backToMainButton.setPreferredSize(new Dimension(150, 50)); 
  backToMainButtonPosition.gridx = 0; 
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

  frame.setVisible(true); 
 } 

 private void sortAndDisplayResult() { 
  if (fromNewToOld) { 
   Stack transactionResult = new Stack(); 
   try { 
    BufferedReader in = new BufferedReader(new FileReader(ID + ".txt")); 
    String line; 
    while ((line = in.readLine()) != null) { 
     transactionResult.push(line); 

    } 
    in.close(); 

    if (sortByType) { 
     transactionResult.bubbleSort(); 
    } 

    StringBuilder result = new StringBuilder(); 
    int transactionSize = transactionResult.stackSize; 
    for (int i = 0; i < transactionSize; i++) { 
     result.append(transactionResult.pop() + "\n"); 
    } 
    transactionOverviewTextArea.setText(result.toString()); 

   } catch (Exception e2) { 
    transactionOverviewTextArea.setText("目前尚無交易資料"); 
   } 
  } else { 
   Queue transactionResult = new Queue(); 
   try { 
    BufferedReader in = new BufferedReader(new FileReader(ID + ".txt")); 
    String line; 
    while ((line = in.readLine()) != null) { 
     transactionResult.pushBack(line); 

    } 
    in.close(); 

    if (sortByType) { 
     transactionResult.bubbleSort(); 
    } 

    StringBuilder result = new StringBuilder(); 
    int transactionSize = transactionResult.queueSize; 
    for (int i = 0; i < transactionSize; i++) { 
     result.append(transactionResult.popFront() + "\n"); 
    } 
    transactionOverviewTextArea.setText(result.toString()); 

   } catch (Exception e2) { 
    transactionOverviewTextArea.setText("目前尚無交易資料"); 
   } 

  } 

 } 
}