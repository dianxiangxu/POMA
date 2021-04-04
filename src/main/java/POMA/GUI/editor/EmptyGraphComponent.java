 package POMA.GUI.editor;

import javax.swing.*; 
 import java.awt.*; 
 import java.awt.event.*; 
 public class EmptyGraphComponent extends JApplet  
     { 
                     JLabel Grtng = new JLabel("GRAPH IS TOO LARGE TO DISPLAY"); 
                     Font FntOne = new Font("Arial", Font.BOLD, 48); 
                     Container cntnr = getContentPane(); 
                     public void init() 
                     { 
                           Grtng.setFont(FntOne); 
                           cntnr.add(Grtng); 
                           cntnr.setLayout(new FlowLayout()); 
                     }                           
      } 
 