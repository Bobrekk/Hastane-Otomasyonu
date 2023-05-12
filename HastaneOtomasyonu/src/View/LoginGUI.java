package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.*;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField passwordField;
	private JPasswordField fld_hastaPass;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon (getClass().getResource("Hastane.png")));
		lblNewLabel.setBounds(179, 10, 115, 100);
		w_pane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(86, 120, 300, 31);
		w_pane.add(lblNewLabel_1);
		
		JTabbedPane Logs = new JTabbedPane(JTabbedPane.TOP);
		Logs.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		Logs.setBounds(10, 161, 476, 201);
		w_pane.add(Logs);
		
		JPanel HastaLog = new JPanel();
		HastaLog.setBackground(Color.WHITE);
		Logs.addTab("Hasta Giriþi", null, HastaLog, null);
		HastaLog.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC Kimlik No: ");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 10, 103, 31);
		HastaLog.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre:  ");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 63, 48, 31);
		HastaLog.add(lblNewLabel_1_1_1);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_hastaTc.setBounds(117, 19, 133, 19);
		HastaLog.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btnNewButton = new JButton("Kay\u0131t Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(20, 117, 116, 46);
		HastaLog.add(btnNewButton);
		
		JButton btnGiriYap = new JButton("Giri\u015F Yap");
		btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_hastaTc.getText().length() == 0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					boolean key = false;
					try {
						Connection c = conn.connDb();
						Statement st = c.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setName(rs.getString("name"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = true;
								}
							}
						} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					if (key == false) {
						Helper.showMsg("Bu TC'de kayýtlý bir hasta bulunmadý, lütfen kayýt olunuz !");
					}
				}
			}
		});
		btnGiriYap.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnGiriYap.setBounds(202, 117, 116, 46);
		HastaLog.add(btnGiriYap);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(117, 72, 133, 19);
		HastaLog.add(fld_hastaPass);
		
		JPanel DoktorLog = new JPanel();
		DoktorLog.setBackground(Color.WHITE);
		Logs.addTab("Doktor Giriþi", null, DoktorLog, null);
		DoktorLog.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("TC Kimlik No: ");
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(10, 10, 103, 31);
		DoktorLog.add(lblNewLabel_1_1_2);
		
		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(117, 19, 133, 19);
		DoktorLog.add(fld_doctorTc);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u015Eifre:  ");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 63, 48, 31);
		DoktorLog.add(lblNewLabel_1_1_1_1);
		
		JButton btnGiriYap_1 = new JButton("Giri\u015F Yap");
		btnGiriYap_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection c = conn.connDb();
						Statement st = c.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									BasHekim bashekim = new BasHekim();
									bashekim.setId(rs.getInt("id"));
									bashekim.setName(rs.getString("name"));
									bashekim.setPassword(rs.getString("password"));
									bashekim.setTcno(rs.getString("tcno"));
									bashekim.setType(rs.getString("type"));
									BasHekimGUI bGUI = new BasHekimGUI(bashekim);
									bGUI.setVisible(true);
									dispose();
								}
								if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setName(rs.getString("name"));
									doctor.setPassword(rs.getString("password"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnGiriYap_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnGiriYap_1.setBounds(79, 117, 308, 46);
		DoktorLog.add(btnGiriYap_1);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(117, 72, 133, 19);
		DoktorLog.add(fld_doctorPass);
	}
}
