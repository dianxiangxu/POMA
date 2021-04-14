 package POMA.GUI.editor;

import javax.swing.*; 
 import java.awt.*; 
 public class EmptyGraphComponent extends JApplet  
     { 
                     /**
       *
       */
      private static final long serialVersionUID = 2415626688976954704L;
                  JLabel Grtng = new JLabel("THE GRAPH IS TOO LARGE TO DISPLAY"); 
                     Font FntOne = new Font("Arial", Font.BOLD, 48); 
                     Container cntnr = getContentPane(); 
                     public void init() 
                     { 
                           Grtng.setFont(FntOne); 
                           cntnr.add(Grtng); 
                           cntnr.setLayout(new FlowLayout()); 
                     }                           
      } 
 