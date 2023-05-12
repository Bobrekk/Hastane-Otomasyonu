package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.BasHekim;
import Model.Clinic;
import Model.Hasta;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;

import Model.wHour;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable w_tabledoctor;
    private DefaultTableModel doctorModel;
    private Object [] doctorData;
    private JTable w_tablewhour;
    private wHour wHour = new wHour();
    private DefaultTableModel wHourModel;
    private Object [] wHourData;
    private int selectDoctorID = 0;
    private String selectDoctorName = " ";
    private JTable w_appointtable;
    private DefaultTableModel appointmentModel;
    private Object [] appointmentData;
    private Appointment appointment = new Appointment();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorData = new Object[2];
		colDoctorData[0] = "Id";
		colDoctorData[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctorData);
		doctorData = new Object[2];
		
		wHourModel = new DefaultTableModel();
		Object[] colwHourData = new Object[2];
		colwHourData[0] = "Id";
		colwHourData[1] = "Tarih";
		wHourModel.setColumnIdentifiers(colwHourData);
		wHourData = new Object[2];
			
		appointmentModel = new DefaultTableModel();
		Object[] colAppointmentData = new Object[3];
		colAppointmentData[0] = "Id";
		colAppointmentData[1] = "Doktor";
		colAppointmentData[2] = "Tarih";
		appointmentModel.setColumnIdentifiers(colAppointmentData);
		appointmentData = new Object[3];
		for (int i = 0; i < appointment.addAppointmentDoctorList(hasta.getId()).size(); i++) {
			appointmentData [0] = appointment.addAppointmentDoctorList(hasta.getId()).get(i).getId();
			appointmentData [1] = appointment.addAppointmentDoctorList(hasta.getId()).get(i).getDoctorname();
			appointmentData [2] = appointment.addAppointmentDoctorList(hasta.getId()).get(i).getAppdate();
			appointmentModel.addRow(appointmentData);
		}
		
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 485);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JTabbedPane w_tabbed = new JTabbedPane(JTabbedPane.TOP);
		w_tabbed.setForeground(Color.BLACK);
		w_tabbed.setBounds(10, 111, 718, 336);
		w_pane.add(w_tabbed);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tabbed.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_doctorscroll = new JScrollPane();
		w_doctorscroll.setBounds(10, 30, 280, 269);
		w_appointment.add(w_doctorscroll);
		
		w_tabledoctor = new JTable(doctorModel);
		w_doctorscroll.setViewportView(w_tabledoctor);
		
		JLabel lblNewLabel = new JLabel("Doktor Listesi");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 7, 106, 21);
		w_appointment.add(lblNewLabel);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(316, 30, 106, 21);
		select_clinic.addItem ("Poliklinik Seç");
		for (int i = 0; i < clinic.addClinicList().size(); i++) {
			select_clinic.addItem(new Item (clinic.addClinicList().get(i).getId(), clinic.addClinicList().get(i).getName()));
		}
		w_appointment.add(select_clinic);
		
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selIndex = select_clinic.getSelectedIndex();
				if (selIndex != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clear = (DefaultTableModel) w_tabledoctor.getModel();
					clear.setRowCount(0);
					for (int i = 0; i < clinic.addClinicDoctorList(item.getKey()).size(); i++) {
						doctorData [0] = clinic.addClinicDoctorList(item.getKey()).get(i).getId();
						doctorData [1] = clinic.addClinicDoctorList(item.getKey()).get(i).getName();
						doctorModel.addRow(doctorData);
					}
				}
				else {
					DefaultTableModel clear = (DefaultTableModel) w_tabledoctor.getModel();
					clear.setRowCount(0);
				}
			}
		});
		
		JLabel lblPoliklinikler = new JLabel("Poliklinikler");
		lblPoliklinikler.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblPoliklinikler.setBounds(316, 10, 85, 13);
		w_appointment.add(lblPoliklinikler);
		
		JLabel lblDoktorSe = new JLabel("Doktor Se\u00E7");
		lblDoktorSe.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblDoktorSe.setBounds(316, 81, 85, 21);
		w_appointment.add(lblDoktorSe);
		
		JLabel lblNewLabel_1 = new JLabel("Uygun Saatler");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(432, 7, 106, 21);
		w_appointment.add(lblNewLabel_1);
		
		JScrollPane w_whourscroll = new JScrollPane();
		w_whourscroll.setBounds(432, 30, 280, 269);
		w_appointment.add(w_whourscroll);
		
		w_tablewhour = new JTable(wHourModel);
		w_whourscroll.setViewportView(w_tablewhour);
		
		JButton btnEkle_1_1_1 = new JButton("Se\u00E7");
		btnEkle_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = w_tabledoctor.getSelectedRow();
				if (selRow >= 0) {
					String getid = w_tabledoctor.getValueAt(selRow, 0).toString();
					int id = Integer.parseInt(getid);
					for (int i = 0; i < wHour.addwHourList(id).size(); i++) {
						wHourData [0] = wHour.addwHourList(id).get(i).getDoctorid();
						wHourData [1] = wHour.addwHourList(id).get(i).getWdate();
						wHourModel.addRow(wHourData);
					}
					w_tablewhour.setModel(wHourModel);
				    selectDoctorID = id;
				    selectDoctorName = w_tabledoctor.getModel().getValueAt(selRow, 1).toString();
				}
				else {
					Helper.showMsg("Lütfen geçerli bir doktor giriniz !");
				}
			}
		});
		btnEkle_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1_1_1.setBounds(316, 112, 106, 32);
		w_appointment.add(btnEkle_1_1_1);
		
		JLabel lblRandevuAl = new JLabel("Randevu Al");
		lblRandevuAl.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblRandevuAl.setBounds(316, 167, 85, 21);
		w_appointment.add(lblRandevuAl);
		
		JButton btnEkle_1_1_1_1 = new JButton("Al");
		btnEkle_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = w_tablewhour.getSelectedRow();
				if (selRow >= 0) {
					String date = w_tablewhour.getValueAt(selRow, 1).toString();
					boolean control;
					try {
						control = hasta.addAppointment(selectDoctorID, selectDoctorName, hasta.getId(), hasta.getName(), date);
						if (control) {
							Helper.showMsg("success");
							hasta.UpdateAppointment(selectDoctorID, date);
							updateWHourTable(selectDoctorID);
							updateappointmentTable(hasta.getId());
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
		btnEkle_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1_1_1_1.setBounds(316, 198, 106, 32);
		w_appointment.add(btnEkle_1_1_1_1);
		
		JPanel w_panel = new JPanel();
		w_panel.setBackground(Color.WHITE);
		w_tabbed.addTab("Randevularým", null, w_panel, null);
		w_panel.setLayout(null);
		
		JScrollPane w_appointscrollpane = new JScrollPane();
		w_appointscrollpane.setBounds(10, 10, 693, 289);
		w_panel.add(w_appointscrollpane);
		
		w_appointtable = new JTable(appointmentModel);
		w_appointtable.setBackground(Color.WHITE);
		w_appointscrollpane.setViewportView(w_appointtable);
		
		JLabel lblNewLabel_2 = new JLabel("Hoþgeldiniz Sayýn " + hasta.getName());
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 10, 219, 22);
		w_pane.add(lblNewLabel_2);
		
		JButton btnEkle_1_1_1_1_1 = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnEkle_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnEkle_1_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1_1_1_1_1.setBounds(622, 14, 106, 32);
		w_pane.add(btnEkle_1_1_1_1_1);
	}
	public void updateWHourTable (int doctorid) {
		DefaultTableModel clear = (DefaultTableModel) w_tablewhour.getModel();
		clear.setRowCount(0);
		
		for (int i = 0; i < wHour.addwHourList(doctorid).size(); i++) {
			wHourData [0] = wHour.addwHourList(doctorid).get(i).getDoctorid();
			wHourData [1] = wHour.addwHourList(doctorid).get(i).getWdate();
			wHourModel.addRow(wHourData);
		}
		w_tablewhour.setModel(wHourModel);
	}
	
	public void updateappointmentTable (int hastaid) {
		DefaultTableModel clear = (DefaultTableModel) w_appointtable.getModel();
		clear.setRowCount(0);
		
		for (int i = 0; i < appointment.addAppointmentList(hastaid).size(); i++) {
			appointmentData [0] = appointment.addAppointmentDoctorList(hastaid).get(i).getId();
			appointmentData [1] = appointment.addAppointmentDoctorList(hastaid).get(i).getDoctorname();
			appointmentData [2] = appointment.addAppointmentDoctorList(hastaid).get(i).getAppdate();
			appointmentModel.addRow(appointmentData);
		}
		w_appointtable.setModel(appointmentModel);
	}
	
}
