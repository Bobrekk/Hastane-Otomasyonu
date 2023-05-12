package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.Helper;
import Model.Hasta;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_adsoyad;
	private JTextField fld_tcno;
	private JPasswordField passwordField;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hasta Kay\u0131t");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 300);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Ad Soyad");
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(10, 10, 103, 22);
		w_pane.add(lblNewLabel_1_1_2);
		
		fld_adsoyad = new JTextField();
		fld_adsoyad.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_adsoyad.setColumns(10);
		fld_adsoyad.setBounds(10, 42, 146, 28);
		w_pane.add(fld_adsoyad);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC Kimlik No ");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 84, 103, 22);
		w_pane.add(lblNewLabel_1_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 116, 146, 28);
		w_pane.add(fld_tcno);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 164, 48, 22);
		w_pane.add(lblNewLabel_1_1_1);
		
		JButton btnKaytOl = new JButton("Kay\u0131t Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_adsoyad.getText().length() == 0 || fld_tcno.getText().length() == 0 || fld_pass.getText().length() == 0) {
					Helper.showMsg ("fill");
				}
				else {
					boolean control;
					try {
						control = hasta.register(fld_adsoyad.getText(), fld_tcno.getText(), fld_pass.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnKaytOl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnKaytOl.setBounds(185, 74, 121, 32);
		w_pane.add(btnKaytOl);
		
		JButton btnGeriDn = new JButton("Geri D\u00F6n");
		btnGeriDn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnGeriDn.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnGeriDn.setBounds(185, 153, 121, 32);
		w_pane.add(btnGeriDn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 116, 146, 18);
		w_pane.add(passwordField);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(10, 201, 146, 28);
		w_pane.add(fld_pass);
	}
}
