package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinic;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 171, 158);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_1_2_2 = new JLabel("Poliklinik");
		lblNewLabel_1_1_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2_2.setBounds(45, 10, 66, 22);
		contentPane.add(lblNewLabel_1_1_2_2);
		
		fld_clinic = new JTextField();
		fld_clinic.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_clinic.setColumns(10);
		fld_clinic.setBounds(10, 42, 146, 28);
		fld_clinic.setText(clinic.getName());
		contentPane.add(fld_clinic);
		JButton btnEkle_1 = new JButton("G\u00FCncelle");
		btnEkle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.control("sure")) {
				 try {
					clinic.updateClinic(clinic.getId(), fld_clinic.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				 dispose();
				}
			}
		});
		btnEkle_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1.setBounds(20, 80, 121, 32);
		contentPane.add(btnEkle_1);
	}

}
