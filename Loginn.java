import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import Password.password;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

class Loginn  extends JFrame implements ActionListener{
	Welcome f;
	
    public static PrintWriter out;
    
	Welcome fp;
    JLabel un,p;
    JTextField tuser;
    JPasswordField tpassword;
    JButton b1,b2;
	//con=null;
   
    Loginn(){
		 //con=(Connection) DB.dbconnect();
         ImageIcon image = new ImageIcon("photo.jpeg");
         this.setIconImage(image.getImage());
        setBackground(Color.white);
        setLayout(null);

        un = new JLabel("Username");
        un.setBounds(40,20,100,30);
        add(un);
        un.setForeground(Color.WHITE);
        
        p = new JLabel("Password");
        p.setBounds(40,70,100,30);
        add(p);
        p.setForeground(Color.WHITE);
 
        tuser=new JTextField();
        tuser.setBounds(150,20,150,30);
        add(tuser);

        tpassword=new JPasswordField();
        tpassword.setBounds(150,70,150,30);
        add(tpassword);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login.png"));
        Image i2 = i1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,20,150,150);
        add(l3);


        b1 = new JButton("Login");
        b1.setBounds(40,140,120,30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.addActionListener(this);
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        add(b1);

        b2=new JButton("Cancel");
        b2.setBounds(180,140,120,30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);
        add(b2);

        b2.addActionListener(this);
        
        //getContentPane().setBackground(Color.WHITE);
		setTitle("Login Page");
        setVisible(true);
        setSize(600,300);
        setLocation(350,20);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x123456));
    }

    public void actionPerformed(ActionEvent ae){
			

        try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl project","root","heetkalaria1234");
			String userName = tuser.getText();
            String password = String.valueOf(tpassword.getPassword());
			PreparedStatement pst =con.prepareStatement("select * from signup where pusername=? and ppass=?");
			pst.setString(1,userName);
			pst.setString(2,password);
			ResultSet rs = pst.executeQuery();
			
            //Conn c1 = new Conn();
			//PreparedStatement st=con.prepareStatement("select * from signup where pusername = ? and ppass = ? ");
			//st.setString(1,userName);
			//st.setString(2,password);
			//ResultSet rs = st.executeQuery();

            //String u = tuser.getText();
            //String v = tpassword.getText();
            
            //String q = "select * from signup where pusername='"+u+"' and ppass='"+v+"'";
            
            //ResultSet rs = c1.s.executeQuery(q); 
            if(rs.next()){
				this.setVisible(false);
			     //Fine f =new Fine();
                //new Main().n.setVisible(true);
                //f.setVisible(true);
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
        custom.setBackground(new Color(0x123456));
        random.setBackground(new Color(0x123456));
        save.setBackground(new Color(0x123456));
        custom.setForeground(Color.WHITE);
        random.setForeground(Color.WHITE);
        save.setForeground(Color.WHITE);

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

            }else{
                JOptionPane.showMessageDialog(null, "Invalid login Credentials ");
                this.setVisible(false);
				fp.setVisible(true);
            }
			/*else if(e.getActionCommand().equals("Cancel"))
        {
            this.setVisible(false);
            fp.setVisible(true);
        }*/
        }catch(Exception e){
            e.printStackTrace();
        }
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




















    /*String sub="";
          String s=e.getActionCommand();
          if(s.equals("Login"))
          {
              this.setVisible(false);
              fp.setVisible(true);
          try
        {	 conn c1 = new conn();
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl project","root","Wiseviki");    
            String q ="select *from signup where pusername='"+tuser+"' and ppass='"+tpassword+"'";
            ResultSet rs = c1.s.executeQuery(q); 
            if(rs.next()){
                new Main().setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Invalid login Credentials ");
                this.setVisible(false);
				f.setVisible(true);
            }
            
        }
      catch(Exception e){
            e.printStackTrace();
        }*/