package SnakeThread.Login.src;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.event.ActionEvent;

import SnakeThread.Server;



//Login and GUI by  Michael Behar - Top of Page
//Database by Anthony - See Bottom of Page

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame playersFrame;
	JButton onePlayer;
	JButton twoPlayer;
	JButton threePlayer;
	JButton fourPlayer;
	JButton okPlayerButton;
	static String one = "OnePlayer";
	static String two = "TwoPlayer";
	static String three = "ThreePlayer";
	static String four = "FourPlayer";
	// Daniel Added for Server
	public int numberOfPlayers = 0;
	JFrame loginFrame;
	JLabel usernameLabel, passwordLabel;
	static JTextField usernameField;
	static JTextField passwordField;
	JTextField usernameField2, passwordField2;
	JButton loginButton, registerButton, loginButton2, registerButton2,
			loginButton3, registerButton3, loginButton4, registerButton4;

	JFrame registerFrame;
	JLabel firstNameLabel, lastNameLabel, streetNumberLabel, streetNameLabel,
			suburbLabel, countryLabel, phoneLabel, emailLabel, makeUserLabel,
			makePasswordLabel, emptyLabel1, emptyLabel2, emptyLabel3,
			emptyLabel4;
	JTextField firstNameField, lastNameField, streetNumberField,
			streetNameField, suburbField, countryField, phoneField, emailField,
			makeUser, makePassword;
	JButton submitButton, submitButton2, submitButton3, submitButton4;

	Connection con;
	Statement st;
	ResultSet rs;
	private boolean succeeded;
	JButton onePlayButton;
	private JButton twoPlayButton;
	private AbstractButton nextButton;
	JFrame loginFrame2;
	private JButton threePlayButton;
	private JButton fourPlayButton;
	private JButton playButton;
	private JButton nextThreeButton;
	private JButton nextnextPlayer;
	private JButton nextFourButton;
	private JButton nextnextFourButton;
	private JButton nextnextnextFourButton;
	JFrame loginFrame3;
	 JFrame loginFrame4;
	 JButton backButton;
	JFrame frame3;

	public static <DisplayThread> void main(String[] args) {

		Login loginUser = new Login();
		loginUser.playersFrame();
		System.out.println("Welcome To Group B Concurrent Programming");
		System.out.println("Please Select How Many Players");
	    new Database();

	}



	private void playersFrame() {
		
		

		playersFrame = new JFrame("PLAYERS");
		onePlayer = new JButton("One Player");
		onePlayer.setSelected(true);
		twoPlayer = new JButton("Two Player");
		twoPlayer.setSelected(true);
		threePlayer = new JButton("Three Player");
		threePlayer.setSelected(true);
		fourPlayer = new JButton("Four Player");
		fourPlayer.setSelected(true);

		playersFrame.setLayout(new GridLayout(4, 1));
		onePlayButton = new JButton("One Player");
		onePlayer.setActionCommand(one);
		twoPlayButton = new JButton("Two Player");
		twoPlayer.setActionCommand(one);
		threePlayButton = new JButton("Three Player");
		threePlayer.setActionCommand(one);
		fourPlayButton = new JButton("Four Player");
		fourPlayer.setActionCommand(one);

		playersFrame.setPreferredSize(new Dimension(300, 140));
		playersFrame.pack();
		playersFrame.setResizable(false);
		playersFrame.setVisible(true);
		playersFrame.setLocationRelativeTo(null);

		playersFrame.add(onePlayButton);
		playersFrame.add(twoPlayButton);
		playersFrame.add(threePlayButton);
		playersFrame.add(fourPlayButton);

		onePlayButton.addActionListener(this);
		twoPlayButton.addActionListener(this);
		threePlayButton.addActionListener(this);
		fourPlayButton.addActionListener(this);

	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == onePlayButton) {
			// ONE PLAYER

			// Player One
			loginFrame = new JFrame("PLAYER ONE LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			playButton = new JButton("Play");
			registerButton = new JButton("Register");
			registerButton.setEnabled(false);

			loginFrame.setLayout(new GridLayout(4, 2));

			loginFrame.add(usernameLabel);
			loginFrame.add(usernameField);
			loginFrame.add(passwordLabel);
			loginFrame.add(passwordField);
			loginFrame.add(loginButton);
			loginFrame.add(registerButton);
			loginFrame.add(playButton);
			
			loginFrame.setPreferredSize(new Dimension(300, 140));
			loginFrame.pack();
			loginFrame.setResizable(false);
			loginFrame.setVisible(true);

			loginFrame.setLocationRelativeTo(null);

			playersFrame.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			playButton.addActionListener(this);
			
		}

		if (src == twoPlayButton) {
			// TWO PLAYER
			if (twoPlayer.isSelected()) {
				// Player One
				loginFrame = new JFrame("PLAYER ONE LOGIN");
				usernameField = new JTextField(30);
				passwordField = new JTextField(30);
				usernameLabel = new JLabel("Username");
				passwordLabel = new JLabel("Password");
				loginButton = new JButton("Login");
				registerButton = new JButton("Register");
				nextButton = new JButton("Next Player");

				loginFrame.setLayout(new GridLayout(4, 2));

				loginFrame.add(usernameLabel);
				loginFrame.add(usernameField);
				loginFrame.add(passwordLabel);
				loginFrame.add(passwordField);
				loginFrame.add(loginButton);
				loginFrame.add(registerButton);
				loginFrame.add(nextButton);

				loginFrame.setPreferredSize(new Dimension(300, 140));
				loginFrame.pack();
				loginFrame.setResizable(false);
				loginFrame.setVisible(true);

				loginFrame.setLocationRelativeTo(null);

				playersFrame.dispose();

				registerButton.addActionListener(this);
				loginButton.addActionListener(this);
				nextButton.addActionListener(this);
				registerButton.setEnabled(false);
}
		}

		if (src == nextButton) {

			loginFrame2 = new JFrame("PLAYER TWO LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			playButton = new JButton("Play >>");

			loginFrame2.setLayout(new GridLayout(4, 2));

			loginFrame2.add(usernameLabel);
			loginFrame2.add(usernameField);
			loginFrame2.add(passwordLabel);
			loginFrame2.add(passwordField);
			loginFrame2.add(loginButton);
			loginFrame2.add(registerButton);
			loginFrame2.add(playButton);

			loginFrame2.setPreferredSize(new Dimension(300, 140));
			loginFrame2.pack();
			loginFrame2.setResizable(false);
			loginFrame2.setVisible(true);

			loginFrame2.setLocationRelativeTo(null);

			playersFrame.dispose();
			loginFrame.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			playButton.addActionListener(this);
			registerButton.setEnabled(false);

		}
		
		if (src == threePlayButton) {
			// THREE PLAYER
			if (threePlayer.isSelected()) {
				// Player One
				loginFrame = new JFrame("PLAYER ONE LOGIN");
				usernameField = new JTextField(30);
				passwordField = new JTextField(30);
				usernameLabel = new JLabel("Username");
				passwordLabel = new JLabel("Password");
				loginButton = new JButton("Login");
				registerButton = new JButton("Register");
				nextThreeButton = new JButton("Next Player");

				loginFrame.setLayout(new GridLayout(4, 2));

				loginFrame.add(usernameLabel);
				loginFrame.add(usernameField);
				loginFrame.add(passwordLabel);
				loginFrame.add(passwordField);
				loginFrame.add(loginButton);
				loginFrame.add(registerButton);
				loginFrame.add(nextThreeButton);
				registerButton.setEnabled(false);


				loginFrame.setPreferredSize(new Dimension(300, 140));
				loginFrame.pack();
				loginFrame.setResizable(false);
				loginFrame.setVisible(true);

				loginFrame.setLocationRelativeTo(null);

				playersFrame.dispose();

				registerButton.addActionListener(this);
				loginButton.addActionListener(this);
				nextThreeButton.addActionListener(this);
			}
		}

		if (src == nextThreeButton) {
			//Player Two
			loginFrame2 = new JFrame("PLAYER TWO LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			nextnextPlayer = new JButton("Next Player");

			loginFrame2.setLayout(new GridLayout(4, 2));

			loginFrame2.add(usernameLabel);
			loginFrame2.add(usernameField);
			loginFrame2.add(passwordLabel);
			loginFrame2.add(passwordField);
			loginFrame2.add(loginButton);
			loginFrame2.add(registerButton);
			loginFrame2.add(nextnextPlayer);

			loginFrame2.setPreferredSize(new Dimension(300, 140));
			loginFrame2.pack();
			loginFrame2.setResizable(false);
			loginFrame2.setVisible(true);

			loginFrame2.setLocationRelativeTo(null);

			playersFrame.dispose();
			loginFrame.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			nextnextPlayer.addActionListener(this);
			registerButton.setEnabled(false);


		}
		
		if (src == nextnextPlayer) {

			
			loginFrame3 = new JFrame("PLAYER THREE LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			playButton = new JButton("Play >>");

			loginFrame3.setLayout(new GridLayout(4, 2));
			loginFrame3.add(usernameLabel);
			loginFrame3.add(usernameField);
			loginFrame3.add(passwordLabel);
			loginFrame3.add(passwordField);
			loginFrame3.add(loginButton);
			loginFrame3.add(registerButton);
			loginFrame3.add(playButton);

			loginFrame3.setPreferredSize(new Dimension(300, 140));
			loginFrame3.pack();
			loginFrame3.setResizable(false);
			loginFrame3.setVisible(true);

			loginFrame3.setLocationRelativeTo(null);

			playersFrame.dispose();
			loginFrame2.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			playButton.addActionListener(this);
			registerButton.setEnabled(false);


		}
		
	
		
		
		
		if (src == fourPlayButton) {
			// FOUR PLAYER
			if (fourPlayer.isSelected()) {
				// Player One
				loginFrame = new JFrame("PLAYER ONE LOGIN");
				usernameField = new JTextField(30);
				passwordField = new JTextField(30);
				usernameLabel = new JLabel("Username");
				passwordLabel = new JLabel("Password");
				loginButton = new JButton("Login");
				registerButton = new JButton("Register");
				nextFourButton = new JButton("Next Player");

				loginFrame.setLayout(new GridLayout(4, 2));

				loginFrame.add(usernameLabel);
				loginFrame.add(usernameField);
				loginFrame.add(passwordLabel);
				loginFrame.add(passwordField);
				loginFrame.add(loginButton);
				loginFrame.add(registerButton);
				loginFrame.add(nextFourButton);

				loginFrame.setPreferredSize(new Dimension(300, 140));
				loginFrame.pack();
				loginFrame.setResizable(false);
				loginFrame.setVisible(true);
				loginFrame.setLocationRelativeTo(null);

				playersFrame.dispose();

				registerButton.addActionListener(this);
				loginButton.addActionListener(this);
				nextFourButton.addActionListener(this);
				registerButton.setEnabled(false);

			}
		}

		if (src == nextFourButton) {
			//Player Two
			loginFrame2 = new JFrame("PLAYER TWO LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			nextnextFourButton = new JButton("Next Player");

			loginFrame2.setLayout(new GridLayout(4, 2));

			loginFrame2.add(usernameLabel);
			loginFrame2.add(usernameField);
			loginFrame2.add(passwordLabel);
			loginFrame2.add(passwordField);
			loginFrame2.add(loginButton);
			loginFrame2.add(registerButton);
			loginFrame2.add(nextnextFourButton);

			loginFrame2.setPreferredSize(new Dimension(300, 140));
			loginFrame2.pack();
			loginFrame2.setResizable(false);
			loginFrame2.setVisible(true);

			loginFrame2.setLocationRelativeTo(null);

			playersFrame.dispose();
			loginFrame.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			nextnextFourButton.addActionListener(this);
			registerButton.setEnabled(false);

		}
		

		if (src == nextnextFourButton) {
			//Player 3
			loginFrame3 = new JFrame("PLAYER THREE LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			nextnextnextFourButton = new JButton("Next Player");

			loginFrame3.setLayout(new GridLayout(4, 2));

			loginFrame3.add(usernameLabel);
			loginFrame3.add(usernameField);
			loginFrame3.add(passwordLabel);
			loginFrame3.add(passwordField);
			loginFrame3.add(loginButton);
			loginFrame3.add(registerButton);
			loginFrame3.add(nextnextnextFourButton);

			loginFrame3.setPreferredSize(new Dimension(300, 140));
			loginFrame3.pack();
			loginFrame3.setResizable(false);
			loginFrame3.setVisible(true);

			loginFrame3.setLocationRelativeTo(null);

			playersFrame.dispose();
			loginFrame2.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			nextnextnextFourButton.addActionListener(this);
			registerButton.setEnabled(false);

		}
		
		if (src == nextnextnextFourButton) {
			//Player Four
			loginFrame4 = new JFrame("PLAYER FOUR LOGIN");
			usernameField = new JTextField(30);
			passwordField = new JTextField(30);
			usernameLabel = new JLabel("Username");
			passwordLabel = new JLabel("Password");
			loginButton = new JButton("Login");
			registerButton = new JButton("Register");
			playButton = new JButton("Play >>");

			loginFrame4.setLayout(new GridLayout(4, 2));

			loginFrame4.add(usernameLabel);
			loginFrame4.add(usernameField);
			loginFrame4.add(passwordLabel);
			loginFrame4.add(passwordField);
			loginFrame4.add(loginButton);
			loginFrame4.add(registerButton);
			loginFrame4.add(playButton);

			loginFrame4.setPreferredSize(new Dimension(300, 140));
			loginFrame4.pack();
			loginFrame4.setResizable(false);
			loginFrame4.setVisible(true);

			loginFrame4.setLocationRelativeTo(null);
			playersFrame.dispose();
			loginFrame3.dispose();

			registerButton.addActionListener(this);
			loginButton.addActionListener(this);
			playButton.addActionListener(this);
			registerButton.setEnabled(false);


		}


		if (src == registerButton) {
			JFrame registerFrame = new JFrame("REGISTER");
			registerFrame.setPreferredSize(new Dimension(300, 300));
			registerFrame.pack();
			registerFrame.setResizable(false);
			registerFrame.setVisible(true);
			registerFrame.setLocationRelativeTo(null);

			firstNameField = new JTextField(25);
			lastNameField = new JTextField(25);
			streetNumberField = new JTextField(3);
			streetNameField = new JTextField(25);
			suburbField = new JTextField(25);
			countryField = new JTextField(25);
			phoneField = new JTextField(15);
			emailField = new JTextField(30);
			makeUser = new JTextField(30);
			makePassword = new JTextField(30);

			firstNameLabel = new JLabel("First Name");
			lastNameLabel = new JLabel("Last Name");
			streetNumberLabel = new JLabel("Street No.");
			streetNameLabel = new JLabel("Street");
			suburbLabel = new JLabel("Suburb");
			countryLabel = new JLabel("Country");
			phoneLabel = new JLabel("Phone");
			emailLabel = new JLabel("E-mail");

			makeUserLabel = new JLabel("Create Username");
			makePasswordLabel = new JLabel("Create Password");

			emptyLabel1 = new JLabel("");
			emptyLabel2 = new JLabel("");
			emptyLabel3 = new JLabel("");
			emptyLabel4 = new JLabel("");

			submitButton = new JButton("Submit");

			registerFrame.setLayout(new GridLayout(14, 2));

			registerFrame.add(firstNameLabel);
			registerFrame.add(firstNameField);
			registerFrame.add(lastNameLabel);
			registerFrame.add(lastNameField);
			registerFrame.add(streetNumberLabel);
			registerFrame.add(streetNumberField);
			registerFrame.add(streetNameLabel);
			registerFrame.add(streetNameField);
			registerFrame.add(suburbLabel);
			registerFrame.add(suburbField);
			registerFrame.add(countryLabel);
			registerFrame.add(countryField);
			registerFrame.add(phoneLabel);
			registerFrame.add(phoneField);
			registerFrame.add(emailLabel);
			registerFrame.add(emailField);
			registerFrame.add(emptyLabel1);
			registerFrame.add(emptyLabel2);
			registerFrame.add(makeUserLabel);
			registerFrame.add(makeUser);
			registerFrame.add(makePasswordLabel);
			registerFrame.add(makePassword);
			registerFrame.add(emptyLabel3);
			registerFrame.add(emptyLabel4);
			registerFrame.add(submitButton);
			submitButton.addActionListener(this);

		}

		else if (src == registerButton2) {
			JFrame registerFrame = new JFrame("REGISTER");
			registerFrame.setPreferredSize(new Dimension(300, 300));
			registerFrame.pack();
			registerFrame.setResizable(false);
			registerFrame.setVisible(true);
			registerFrame.setLocationRelativeTo(null);

			firstNameField = new JTextField(25);
			lastNameField = new JTextField(25);
			streetNumberField = new JTextField(3);
			streetNameField = new JTextField(25);
			suburbField = new JTextField(25);
			countryField = new JTextField(25);
			phoneField = new JTextField(15);
			emailField = new JTextField(30);
			makeUser = new JTextField(30);
			makePassword = new JTextField(30);

			firstNameLabel = new JLabel("First Name");
			lastNameLabel = new JLabel("Last Name");
			streetNumberLabel = new JLabel("Street No.");
			streetNameLabel = new JLabel("Street");
			suburbLabel = new JLabel("Suburb");
			countryLabel = new JLabel("Country");
			phoneLabel = new JLabel("Phone");
			emailLabel = new JLabel("E-mail");

			makeUserLabel = new JLabel("Create Username");
			makePasswordLabel = new JLabel("Create Password");

			emptyLabel1 = new JLabel("");
			emptyLabel2 = new JLabel("");
			emptyLabel3 = new JLabel("");
			emptyLabel4 = new JLabel("");

			submitButton2 = new JButton("Submit");

			registerFrame.setLayout(new GridLayout(14, 2));

			registerFrame.add(firstNameLabel);
			registerFrame.add(firstNameField);
			registerFrame.add(lastNameLabel);
			registerFrame.add(lastNameField);
			registerFrame.add(streetNumberLabel);
			registerFrame.add(streetNumberField);
			registerFrame.add(streetNameLabel);
			registerFrame.add(streetNameField);
			registerFrame.add(suburbLabel);
			registerFrame.add(suburbField);
			registerFrame.add(countryLabel);
			registerFrame.add(countryField);
			registerFrame.add(phoneLabel);
			registerFrame.add(phoneField);
			registerFrame.add(emailLabel);
			registerFrame.add(emailField);
			registerFrame.add(emptyLabel1);
			registerFrame.add(emptyLabel2);
			registerFrame.add(makeUserLabel);
			registerFrame.add(makeUser);
			registerFrame.add(makePasswordLabel);
			registerFrame.add(makePassword);
			registerFrame.add(emptyLabel3);
			registerFrame.add(emptyLabel4);
			registerFrame.add(submitButton);
			submitButton2.addActionListener(this);

		}

		else if (src == registerButton3) {
			JFrame registerFrame = new JFrame("REGISTER");
			registerFrame.setPreferredSize(new Dimension(300, 300));
			registerFrame.pack();
			registerFrame.setResizable(false);
			registerFrame.setVisible(true);
			registerFrame.setLocationRelativeTo(null);

			firstNameField = new JTextField(25);
			lastNameField = new JTextField(25);
			streetNumberField = new JTextField(3);
			streetNameField = new JTextField(25);
			suburbField = new JTextField(25);
			countryField = new JTextField(25);
			phoneField = new JTextField(15);
			emailField = new JTextField(30);
			makeUser = new JTextField(30);
			makePassword = new JTextField(30);

			firstNameLabel = new JLabel("First Name");
			lastNameLabel = new JLabel("Last Name");
			streetNumberLabel = new JLabel("Street No.");
			streetNameLabel = new JLabel("Street");
			suburbLabel = new JLabel("Suburb");
			countryLabel = new JLabel("Country");
			phoneLabel = new JLabel("Phone");
			emailLabel = new JLabel("E-mail");

			makeUserLabel = new JLabel("Create Username");
			makePasswordLabel = new JLabel("Create Password");

			emptyLabel1 = new JLabel("");
			emptyLabel2 = new JLabel("");
			emptyLabel3 = new JLabel("");
			emptyLabel4 = new JLabel("");

			submitButton3 = new JButton("Submit");

			registerFrame.setLayout(new GridLayout(14, 2));

			registerFrame.add(firstNameLabel);
			registerFrame.add(firstNameField);
			registerFrame.add(lastNameLabel);
			registerFrame.add(lastNameField);
			registerFrame.add(streetNumberLabel);
			registerFrame.add(streetNumberField);
			registerFrame.add(streetNameLabel);
			registerFrame.add(streetNameField);
			registerFrame.add(suburbLabel);
			registerFrame.add(suburbField);
			registerFrame.add(countryLabel);
			registerFrame.add(countryField);
			registerFrame.add(phoneLabel);
			registerFrame.add(phoneField);
			registerFrame.add(emailLabel);
			registerFrame.add(emailField);
			registerFrame.add(emptyLabel1);
			registerFrame.add(emptyLabel2);
			registerFrame.add(makeUserLabel);
			registerFrame.add(makeUser);
			registerFrame.add(makePasswordLabel);
			registerFrame.add(makePassword);
			registerFrame.add(emptyLabel3);
			registerFrame.add(emptyLabel4);
			registerFrame.add(submitButton);
			submitButton3.addActionListener(this);

		}

		else if (src == registerButton4) {
			JFrame registerFrame = new JFrame("REGISTER");
			registerFrame.setPreferredSize(new Dimension(300, 300));
			registerFrame.pack();
			registerFrame.setResizable(false);
			registerFrame.setVisible(true);
			registerFrame.setLocationRelativeTo(null);

			firstNameField = new JTextField(25);
			lastNameField = new JTextField(25);
			streetNumberField = new JTextField(3);
			streetNameField = new JTextField(25);
			suburbField = new JTextField(25);
			countryField = new JTextField(25);
			phoneField = new JTextField(15);
			emailField = new JTextField(30);
			makeUser = new JTextField(30);
			makePassword = new JTextField(30);

			firstNameLabel = new JLabel("First Name");
			lastNameLabel = new JLabel("Last Name");
			streetNumberLabel = new JLabel("Street No.");
			streetNameLabel = new JLabel("Street");
			suburbLabel = new JLabel("Suburb");
			countryLabel = new JLabel("Country");
			phoneLabel = new JLabel("Phone");
			emailLabel = new JLabel("E-mail");

			makeUserLabel = new JLabel("Create Username");
			makePasswordLabel = new JLabel("Create Password");

			emptyLabel1 = new JLabel("");
			emptyLabel2 = new JLabel("");
			emptyLabel3 = new JLabel("");
			emptyLabel4 = new JLabel("");

			submitButton4 = new JButton("Submit");

			registerFrame.setLayout(new GridLayout(14, 2));

			registerFrame.add(firstNameLabel);
			registerFrame.add(firstNameField);
			registerFrame.add(lastNameLabel);
			registerFrame.add(lastNameField);
			registerFrame.add(streetNumberLabel);
			registerFrame.add(streetNumberField);
			registerFrame.add(streetNameLabel);
			registerFrame.add(streetNameField);
			registerFrame.add(suburbLabel);
			registerFrame.add(suburbField);
			registerFrame.add(countryLabel);
			registerFrame.add(countryField);
			registerFrame.add(phoneLabel);
			registerFrame.add(phoneField);
			registerFrame.add(emailLabel);
			registerFrame.add(emailField);
			registerFrame.add(emptyLabel1);
			registerFrame.add(emptyLabel2);
			registerFrame.add(makeUserLabel);
			registerFrame.add(makeUser);
			registerFrame.add(makePasswordLabel);
			registerFrame.add(makePassword);
			registerFrame.add(emptyLabel3);
			registerFrame.add(emptyLabel4);
			registerFrame.add(submitButton);
			submitButton4.addActionListener(this);

		}

		else if (src == submitButton) {

			JFrame frame3 = new JFrame("COMING SOON");
			frame3.pack();
			frame3.setResizable(false);
			frame3.setVisible(true);
			frame3.setLocationRelativeTo(null);
			backButton = new JButton("TO LOGIN");
			frame3.add(backButton);
			backButton.addActionListener(this);
		}

		else if (src ==  backButton) {
		
			playersFrame.pack();
			playersFrame.setResizable(false);
			playersFrame.setVisible(true);
			playersFrame.setLocationRelativeTo(null);
	
		}

		
		else if (src == submitButton2) {

			JFrame frame3 = new JFrame("COMING SOON");
			frame3.pack();
			frame3.setResizable(false);
			frame3.setVisible(true);
			frame3.setLocationRelativeTo(null);
			
			backButton = new JButton("TO LOGIN");
			frame3.add(backButton);
			backButton.addActionListener(this);
		}

		else if (src == submitButton3) {
			JFrame frame3 = new JFrame("COMING SOON");
			frame3.pack();
			frame3.setResizable(false);
			frame3.setVisible(true);
			frame3.setLocationRelativeTo(null);
			
			backButton = new JButton("TO LOGIN");
			frame3.add(backButton);
			backButton.addActionListener(this);
			
		} else if (src == submitButton4) {
			JFrame frame3 = new JFrame("COMING SOON");
			frame3.pack();
			frame3.setResizable(false);
			frame3.setVisible(true);
			frame3.setLocationRelativeTo(null);
			
			backButton = new JButton("TO LOGIN");
			frame3.add(backButton);
			backButton.addActionListener(this);
		}
		
		//GAMEPLAY GOES HERE WITH BOUNDER BUFFER THREADS
			 else if (src == playButton) {

			        Server.server = new Server(this.numberOfPlayers);
			        Thread serverThread = new Thread(Server.server);

			        serverThread.start();
			 }

		
//Anthony = Database + Authentication			
		else if (src == loginButton) {
			//Anthony
			if (Login.authenticate(getUsername(), getPassword())) {
				JOptionPane.showMessageDialog(Login.this, "Hello, "
						+ getUsername() + " You Are Now Logged In");
				System.out.println("Welcome To The Game " + getUsername());
				loginButton.setEnabled(false);
				dispose();
				numberOfPlayers++;

			} else {
				JOptionPane.showMessageDialog(Login.this,
						"Invalid Username or Password", "LOGIN",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("Sorry! You Were Not Added To The Game");
				succeeded = false;
			}
			
		}

	}

	//Anthony - Authentication of Passwords
	public static boolean authenticate(String username, String password) {
		if (username.equals("Anthony") && password.equals("Password")) {
			return true;
		}
		if (username.equals("Daniel") && password.equals("Password")) {
			return true;
		}
		if (username.equals("Michael") && password.equals("Password")) {
			return true;
		}
		if (username.equals("Matthew") && password.equals("Password")) {
			return true;
		}
		if (username.equals("Rabou") && password.equals("Password")) {
			return true;
		}

		return false;
	}
		
		//getUsername
		 public static String getUsername() {
	        	return usernameField.getText().trim();
	        }
		//getPassword
		 public String getPassword() {
	        	return passwordField.getText().trim();
	        }
		 
		//isSucceeded
			private boolean isSucceeded() {
				return succeeded;
			}
	}