import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.border.*;



public class Welcome extends JFrame implements ActionListener {

	

	Welcome f;
	JButton signup,login;
	Welcome(){

		
		signup =new JButton("Sign up");
		login = new JButton("Login");
		this.setLayout(null);
		this.setSize(600,600);
		this.setLocation(350,20);
		signup.setBounds(130,280,150,50);
		login.setBounds(330,280,150,50);
		add(signup);
		add(login);
		signup.addActionListener(this);
		login.addActionListener(this);
		login.setBackground(Color.WHITE);
		//login.setBorder(new RoundedBorder(10));
		//login.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		signup.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0x123456));
		//setLayout(new BorderLayout());
        //JLabel background=new JLabel(new ImageIcon("photo2.jpeg"));
		// ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("photo2.jpeg"));
        // Image i2 = i1.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        // ImageIcon i3 =  new ImageIcon(i2);
        // JLabel l3 = new JLabel(i3);
        // l3.setBounds(350,20,150,150);
        // add(l3);
		// ImageIcon img=new ImageIcon("login(1).png");
        // JLabel backgroundImage=new JLabel("",img,JLabel.HORIZONTAL);
        // backgroundImage.setBounds(0,0,100,100);
        // this.add(backgroundImage);
		ImageIcon image = new ImageIcon("photo.jpeg");
        this.setIconImage(image.getImage());
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		String s=e.getActionCommand();
		if(s.equals("Sign up")){
			this.setVisible(false);
			Signup m = new Signup(this);
		}
		else if(e.getActionCommand().equals("Login")){
			this.setVisible(false);
			Loginn l =new Loginn();
			//new Loginn().setVisible(true);
		}
	}
	
	public class JPanelWithBackground extends JPanel {

		private Image backgroundImage;
	  
		// Some code to initialize the background image.
		// Here, we use the constructor to load the image. This
		// can vary depending on the use case of the panel.
		public JPanelWithBackground(String fileName) throws IOException {
		  backgroundImage = ImageIO.read(new File(fileName));
		}
	  
		public void paintComponent(Graphics g) {
		  super.paintComponent(g);
	  
		  // Draw the background image.
		  g.drawImage(backgroundImage, 0, 0, this);
		}
	}
	public static void main(String args[])
    {
        Welcome f =new Welcome();
		f.setTitle("Welcome to Pasword Management System");
    }
}
