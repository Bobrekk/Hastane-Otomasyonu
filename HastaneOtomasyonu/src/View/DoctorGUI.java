package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {
	private static Doctor doctor = new Doctor();
	private JPanel contentPane;
	private JTable w_whourtable;
	private DefaultTableModel wHourModel;
	private Object [] wHourData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		wHourModel = new DefaultTableModel();
		Object [] colwHour = new Object [2];
		colwHour [0] = "ID";
		colwHour [1] = "Tarih";
		wHourModel.setColumnIdentifiers(colwHour);
		wHourData = new Object [2];
		for (int i = 0; i < doctor.addwHourList(doctor.getId()).size(); i++) {
			wHourData [0] = doctor.addwHourList(doctor.getId()).get(i).getDoctorid();
			wHourData [1] = doctor.addwHourList(doctor.getId()).get(i).getDoctorname();
			wHourModel.addRow(wHourData);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBounds(10, 102, 718, 336);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Saati Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JDateChooser selectdate = new JDateChooser();
		selectdate.setBounds(10, 10, 114, 19);
		panel.add(selectdate);
		
		JComboBox selecttime = new JComboBox();
		selecttime.setModel(new DefaultComboBoxModel(new String[] {"10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		selecttime.setBounds(134, 8, 72, 21);
		panel.add(selecttime);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(selectdate.getDate());
				}
				catch (Exception e2) {
				}
				if (date.length() == 0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz !");
				}
				else {
					String time = " " + selecttime.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					boolean control;
					try {
						control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("success");
							updateWHourModel(doctor);
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
		btn_addWhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_addWhour.setBounds(221, 10, 72, 29);
		panel.add(btn_addWhour);
		
		JScrollPane w_scrollwhour = new JScrollPane();
		w_scrollwhour.setBounds(0, 55, 713, 254);
		panel.add(w_scrollwhour);
		
		w_whourtable = new JTable(wHourModel);
		w_whourtable.setBackground(Color.WHITE);
		w_scrollwhour.setViewportView(w_whourtable);
		
		JButton btn_deletewhour = new JButton("Sil");
		btn_deletewhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = w_whourtable.getSelectedRow();
				if (selRow >= 0) {
					String selID = w_whourtable.getValueAt(selRow, 0).toString();
					int selHour = Integer.parseInt(selID);
					boolean control = doctor.deleteDoctor(selHour);
					if (control) {
						Helper.showMsg("success");
						updateWHourModel(doctor);
					}
					else {
						Helper.showMsg("error");
					}
				}
			}
		});
		btn_deletewhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btn_deletewhour.setBounds(631, 10, 72, 29);
		panel.add(btn_deletewhour);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 10, 287, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnNewButton.setBounds(365, 14, 121, 32);
		contentPane.add(btnNewButton);
	}
	public void updateWHourModel(Doctor doctor) {
		DefaultTableModel clear = (DefaultTableModel) w_whourtable.getModel();
		clear.setRowCount(0);
		for (int i = 0; i < doctor.addwHourList(doctor.getId()).size(); i++) {
			wHourData [0] = doctor.addwHourList(doctor.getId()).get(i).getDoctorid();
			wHourData [1] = doctor.addwHourList(doctor.getId()).get(i).getWdate();
			wHourModel.addRow(wHourData);
		}
	}
}
