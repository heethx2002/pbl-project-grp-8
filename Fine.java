import javax.swing.*;

import Password.password;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Fine {
	//Welcome f;
	
    public static PrintWriter out;

    public static void main(String[] args) {
		
	
		Fine sp;
        try {
			
            out = new PrintWriter(new FileOutputStream("Password.txt"));
        } catch (Exception ee) {
			
        }

        // creating radio for generate password
        JRadioButton custom = new JRadioButton("Custom");
        JRadioButton random = new JRadioButton("Random");
        JRadioButton save = new JRadioButton("Save Password");
        custom.setBorder(null);
        custom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        random.setBorder(null);
        random.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBorder(null);
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Creating textfield for input
        JTextField textField = new JTextField(25);
        textField.setPreferredSize(new Dimension(50, 38));
        
        // Creating buttons
        JButton proceed = new JButton("Proceed");
        proceed.setPreferredSize(new Dimension(150, 38));
        
        proceed.setCursor(new Cursor(Cursor.HAND_CURSOR));
        proceed.setBackground(Color.WHITE);
        // Copy button
        JButton copy = new JButton("Copy");
        copy.setPreferredSize(new Dimension(150, 38));
        copy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        copy.setBackground(Color.WHITE);
        
        // Creating ButtonGroup to ensure no multiple buttons are selected
        ButtonGroup groupForRadioFile = new ButtonGroup();
        groupForRadioFile.add(custom);
        groupForRadioFile.add(random);
        groupForRadioFile.add(save);
        
        // Jpanel for radiobuttons
        JPanel panelForRadio = new JPanel();
        panelForRadio.add(custom);
        panelForRadio.add(random);
        panelForRadio.add(save);
        panelForRadio.setBackground(new Color(0x123456));
        // Creating panel for proceed and textfield
        JPanel panelForProceed = new JPanel();
        panelForProceed.add(proceed);
        panelForProceed.add(textField);
        panelForProceed.setBackground(new Color(0x123456));
        //Creating panel for copy
        JPanel panelForCopy=new JPanel();
        panelForCopy.add(copy);
        panelForCopy.setSize(430, 380);
        panelForCopy.setBackground(new Color(0x123456));

        copy.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(textField.getText()); // getting text from textfield
            // in stringselectoin
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard(); // getting access to clipboard
            clpbrd.setContents(stringSelection, null); // adding text to clipboard
            JOptionPane.showMessageDialog(null, "Text Copied!"); // done message
        });
        
        proceed.addActionListener(e -> {
            if (!custom.isSelected() && !save.isSelected() && !random.isSelected())
            JOptionPane.showMessageDialog(null, "Please Select a choice", "Alert", JOptionPane.WARNING_MESSAGE);// alert
            else if (save.isSelected()) {
                if (textField.getText().toString().isEmpty())
                isValidInput("No Text Found");
                else {
                    String name = JOptionPane.showInputDialog(null, "Enter Enter Your Name");
                    out.println(name + ": " + textField.getText().toString());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    out.println("Password generated on: " + dtf.format(now));
                    out.flush();
                }
            } else if (random.isSelected()) {
                JOptionPane.showMessageDialog(null, "Random Password generated!");// Done Message
                password genpass = new password(); // creating an object of userdefine package
                textField.setText(genpass.generate());
            } else if (custom.isSelected()) { // for custom password
                // taking length, and requirements from the user
                int length = 0;
                String temp = JOptionPane.showInputDialog(null, "Enter Length for the password(8-18)");
                if (!checkInput(temp))
                isValidInput("InValid Integer");
                else {
                    length = Integer.parseInt(temp);
                    
                    if (length > 18 || length < 8) // if length is invalid
                    JOptionPane.showMessageDialog(null, "Enter a Valid Length", "Alert",
                    JOptionPane.WARNING_MESSAGE);
                    else {
                        // Checking number of uppercase
                        temp = JOptionPane.showInputDialog(null, "Enter number of UpperCase for the password");
                        int noUpperCase = 0;
                        while (!checkInput(temp)) {
                            isValidInput("InValid Integer");
                            temp = JOptionPane.showInputDialog(null, "Enter number of UpperCase for the password");
                        }
                        noUpperCase = Integer.parseInt(temp);
                        
                        // checking number of lowercase
                        temp = JOptionPane.showInputDialog(null, "Enter number of LowerCase for the password");
                        int nolowerCase = 0;
                        while (!checkInput(temp)) {
                            isValidInput("InValid Integer");
                            temp = JOptionPane.showInputDialog(null, "Enter number of LowerCase for the password");
                        }
                        nolowerCase = Integer.parseInt(temp);
                        
                        // checking number of specialcase
                        temp = JOptionPane.showInputDialog(null, "Enter number of Special Characters");
                        int noSpecialCase = 0;
                        while (!checkInput(temp)) {
                            isValidInput("InValid Integer");
                            temp = JOptionPane.showInputDialog(null, "Enter number of Special Characters");
                        }
                        noSpecialCase = Integer.parseInt(temp);
                        
                        // calculating total by userInput
                        int total = noSpecialCase + noUpperCase + nolowerCase;
                        
                        // calculating Number of numbers for password
                        int number = length - total;
                        
                        if (total > length) // if values are incorrect
                        JOptionPane.showMessageDialog(null, "Credentials went beyond Length", "Alert",
                        JOptionPane.WARNING_MESSAGE);
                        else {
                            password genpass = new password(); // creating an object of user defined package
                            JOptionPane.showMessageDialog(null, "Password is Generated in TextField!");
                            // displaying password
                            textField.setText(
                                genpass.generate(length, noUpperCase, nolowerCase, noSpecialCase, number));
                            }
                        }
                    }
                }
            });
            
            JFrame FineFrame = new JFrame();
            FineFrame.setTitle("Password Dashboard");
            FineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            FineFrame.getContentPane().setBackground(new Color(18,52,86));
            
            FineFrame.setSize(430, 380);
            //FineFrame.setBounds(500, 200, 430, 380);       
            FineFrame.setLocation(350,20);
            FineFrame.setResizable(false);
            FineFrame.setLocationRelativeTo(null);
            FineFrame.setLayout(new FlowLayout());
            FineFrame.add(panelForProceed);
            FineFrame.add(panelForRadio);
            FineFrame.add(panelForCopy);
            FineFrame.setVisible(true);
            ImageIcon image = new ImageIcon("photo.jpeg");
            FineFrame.setIconImage(image.getImage());
            
        }
        
    public static void isValidInput(String output) {
        JOptionPane.showMessageDialog(null, output, "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean checkInput(String str) {
        if (str == null || str.length() == 0)
            return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}