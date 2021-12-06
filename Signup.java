import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class Signup extends JFrame implements ActionListener {
	Container c;
	Welcome fp;
	JLabel lname,lsurname,lemail,lusername,lpassword;
	JTextField tname,tsurname,temail,tusername;
	JPasswordField tpass;
	JButton Submit,Reset,Back;
	Signup(Welcome f){
		 fp=f;
		c=getContentPane();
		
		lname=new JLabel("FirstName:");
		lsurname=new JLabel("Surname:");
		lemail=new JLabel("Email:");
		lusername=new JLabel("Username");
		lpassword=new JLabel("Set Password:");
		
		tname=new JTextField();
		tsurname=new JTextField();
		temail=new JTextField();
		tusername=new JTextField();
		tpass=new JPasswordField();
		
		Submit=new JButton("Submit");
		Reset=new JButton("Reset");
		Back=new JButton("Back");
		
		lname.setBounds(150,50,100,25);
		tname.setBounds(250,50,100,25);
		lsurname.setBounds(150,100,100,25);
		tsurname.setBounds(250,100,100,25);
		lemail.setBounds(150,150,100,25);
		temail.setBounds(250,150,170,25);
		lusername.setBounds(150,200,100,25);
		tusername.setBounds(250,200,100,25);
		lpassword.setBounds(150,250,100,25);
		tpass.setBounds(250,250,100,25);
		 Submit.setBounds(100, 360, 100, 25);
         Reset.setBounds(230, 360, 100, 25);
         Back.setBounds(360, 360, 100, 25);
		Submit.setBackground(Color.WHITE);
		Reset.setBackground(Color.WHITE);
		Back.setBackground(Color.WHITE);
		this.getContentPane().setBackground(new Color(0x123456));
		lname.setForeground(Color.WHITE);
		lsurname.setForeground(Color.WHITE);
		lemail.setForeground(Color.WHITE);
		lusername.setForeground(Color.WHITE);
		lpassword.setForeground(Color.WHITE);
		tname.setForeground(Color.BLACK);
		tsurname.setForeground(Color.BLACK);
		temail.setForeground(Color.BLACK);
		tusername.setForeground(Color.BLACK);
		tpass.setForeground(Color.BLACK);


		
		setLayout(null);
		c.add(lname);
		c.add(tname);
		c.add(lsurname);
		c.add(tsurname);
		c.add(lemail);
		c.add(temail);
		c.add(lusername);
		c.add(tusername);
		c.add(lpassword);
		c.add(tpass);
		c.add(Submit);
        c.add(Reset);
        c.add(Back);
		Submit.addActionListener(this);
        Reset.addActionListener(this);
        Back.addActionListener(this);
		setSize(600,500);
		setLocation(350,20);
		setVisible(true);
		setTitle("Password Management Signup Window");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("photo.jpeg");
        this.setIconImage(image.getImage());
		
	}
	public void actionPerformed(ActionEvent e)
      {
          String sub="";
          String s=e.getActionCommand();
          if(s.equals("Submit"))
          {
              this.setVisible(false);
              fp.setVisible(true);
              try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/pbl project","root","heetkalaria1234");    
            String query ="insert into signup values(?,?,?,?,?)";
            PreparedStatement st =con.prepareStatement(query);
            st.setString(1, tname.getText());
            st.setString(2, tsurname.getText());
            st.setString(3, temail.getText());
			st.setString(4, tusername.getText());
            st.setString(5, new String(tpass.getPassword()));
            
            st.execute();
            con.close();
			JOptionPane.showMessageDialog(c, "NAME :"+tname.getText()+"\n"+"Last Name:"+tsurname.getText()+"\n Profile Registered Successfully");

            
            
        }
        catch (Exception em)
        {
            JOptionPane.showMessageDialog(c, "\nRecord not Saved");

			System.out.println(em);
        }
		}
       
          
          else if(s.equals("Reset"))
          {
              tname.setText("");
              tsurname.setText("");
              temail.setText("");
			  tusername.setText("");
              tpass.setText("");
          }
          else if(e.getActionCommand().equals("Back"))
        {
            this.setVisible(false);
            fp.setVisible(true);
        }
        
      }
	
}
