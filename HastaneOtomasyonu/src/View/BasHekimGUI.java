package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.*;
import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {

	private JPanel w_pane;
	static Clinic poliklinik = new Clinic();
	static BasHekim bashekim = new BasHekim();
	private JTextField fld_dTc;
	private JTextField fld_dPass;
	private JTextField fld_dName;
	private JTextField fld_doctorID;
	private JTable w_tabledoctor;
	private DefaultTableModel doktorModel = null;
	private Object[] doktorData = null;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private DefaultTableModel workerModel = null;
	private Object [] workerData = null;
	private JPopupMenu clinicMenu;
	private JTable w_tableclinic;
	private JTextField fld_clinic;
	private JTable w_tableworker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(bashekim);
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
	public BasHekimGUI(BasHekim bashekim) {
		doktorModel = new DefaultTableModel();
		Object[] colDoktorData = new Object[4];
		colDoktorData[0] = "Id";
		colDoktorData[1] = "Ad Soyad";
		colDoktorData[2] = "Tc No";
		colDoktorData[3] = "Þifre";
		doktorModel.setColumnIdentifiers(colDoktorData);
		doktorData = new Object[4];
		for (int i = 0; i < bashekim.addDoctorList().size(); i++) {
			doktorData[0] = bashekim.addDoctorList().get(i).getId();
			doktorData[1] = bashekim.addDoctorList().get(i).getName();
			doktorData[2] = bashekim.addDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.addDoctorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}
		clinicModel = new DefaultTableModel();
		Object[] clinicCol = new Object[2];
		clinicCol[0] = "ID";
		clinicCol[1] = "Ýsim";
		clinicModel.setColumnIdentifiers(clinicCol);
		clinicData = new Object[2];
		for (int i = 0; i < poliklinik.addClinicList().size(); i++) {
			clinicData[0] = poliklinik.addClinicList().get(i).getId();
			clinicData[1] = poliklinik.addClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		workerModel = new DefaultTableModel();
		Object[] colworkerData = new Object[2];
		colworkerData[0] = "Id";
		colworkerData[1] = "Poliklinik Adý";
		workerModel.setColumnIdentifiers(colworkerData);
		workerData = new Object[2];
		setTitle("Hastane Y\u00F6netim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 485);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + bashekim.getName());
		lblNewLabel.setBounds(10, 10, 287, 22);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(365, 14, 121, 32);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(btnNewButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBounds(10, 110, 718, 336);
		w_pane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("TC Kimlik No: ");
		lblNewLabel_1_1.setBounds(10, 49, 103, 22);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel.add(lblNewLabel_1_1);

		fld_dTc = new JTextField();
		fld_dTc.setBounds(123, 46, 146, 28);
		fld_dTc.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_dTc.setColumns(10);
		panel.add(fld_dTc);

		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre:  ");
		lblNewLabel_1_1_1.setBounds(10, 92, 48, 22);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		panel.add(lblNewLabel_1_1_1);

		fld_dPass = new JTextField();
		fld_dPass.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_dPass.setColumns(10);
		fld_dPass.setBounds(123, 89, 146, 28);
		panel.add(fld_dPass);

		JLabel lblNewLabel_1_1_2 = new JLabel("Ad Soyad: ");
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(10, 10, 103, 22);
		panel.add(lblNewLabel_1_1_2);

		fld_dName = new JTextField();
		fld_dName.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_dName.setColumns(10);
		fld_dName.setBounds(123, 7, 146, 28);
		panel.add(fld_dName);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTc.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean control;
					try {
						control = bashekim.addDoctor(fld_dName.getText(), fld_dTc.getText(), fld_dPass.getText());
						if (control == true) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTc.setText(null);
							fld_dPass.setText(null);
							updateDoktorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle.setBounds(132, 146, 121, 32);
		panel.add(btnEkle);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Kullan\u0131c\u0131 ID: ");
		lblNewLabel_1_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(10, 224, 103, 22);
		panel.add(lblNewLabel_1_1_2_1);

		fld_doctorID = new JTextField();
		fld_doctorID.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(123, 221, 146, 28);
		panel.add(fld_doctorID);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçin!");
				} else {
					if (Helper.control("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						boolean control = bashekim.deleteDoctor(selectID);
						if (control) {
							Helper.showMsg("success");
							fld_doctorID.setText(null);
							updateDoktorModel();
						}
					}
				}
			}
		});
		btnSil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnSil.setBounds(132, 259, 121, 32);
		panel.add(btnSil);

		JScrollPane w_doctorscrollpane = new JScrollPane();
		w_doctorscrollpane.setBounds(279, 10, 424, 281);
		panel.add(w_doctorscrollpane);

		w_tabledoctor = new JTable(doktorModel);
		w_doctorscrollpane.setViewportView(w_tabledoctor);

		JPanel w_ClinicPanel = new JPanel();
		w_ClinicPanel.setBackground(Color.WHITE);
		w_ClinicPanel.setForeground(Color.BLACK);
		tabbedPane.addTab("Poliklinik Yönetimi", null, w_ClinicPanel, null);
		w_ClinicPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 196, 289);
		w_ClinicPanel.add(scrollPane);

		clinicMenu = new JPopupMenu();
		JMenuItem update = new JMenuItem("Güncelle");
		JMenuItem delete = new JMenuItem("Sil");
		clinicMenu.add(update);
		clinicMenu.add(delete);
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedID = Integer.parseInt(w_tableclinic.getValueAt(w_tableclinic.getSelectedRow(), 0).toString());
				try {
					Clinic selectClinic = poliklinik.getFetch(selectedID);
					UpdateClinicGUI updateGUI = new UpdateClinicGUI (selectClinic);
					updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					updateGUI.setVisible(true);
					updateGUI.addWindowListener(new WindowAdapter() {
						
						@Override
						public void windowClosed(WindowEvent e) {
							updateClinicModel();
							
						}
						
					});
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});

		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.control("sure")) {
					int selectedID = Integer.parseInt(w_tableclinic.getValueAt(w_tableclinic.getSelectedRow(), 0).toString());
					if (poliklinik.deleteClinic(selectedID)) {
						Helper.showMsg("success");
						updateClinicModel();
					}
					else {
						Helper.showMsg("error");
					}
				}
			}
		});
		
		w_tableclinic = new JTable(clinicModel);
		scrollPane.setViewportView(w_tableclinic);
		w_tableclinic.setComponentPopupMenu(clinicMenu);
		w_tableclinic.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				Point pointer = e.getPoint();
				int selectedRow = w_tableclinic.rowAtPoint(pointer);
				w_tableclinic.addRowSelectionInterval(selectedRow, selectedRow);
			}
		});

		JLabel lblNewLabel_1_1_2_2 = new JLabel("Poliklinik");
		lblNewLabel_1_1_2_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2_2.setBounds(254, 24, 66, 22);
		w_ClinicPanel.add(lblNewLabel_1_1_2_2);

		fld_clinic = new JTextField();
		fld_clinic.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		fld_clinic.setColumns(10);
		fld_clinic.setBounds(219, 56, 146, 28);
		w_ClinicPanel.add(fld_clinic);

		JButton btnEkle_1 = new JButton("Ekle");
		btnEkle_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinic.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					Helper.showMsg("success");
					try {
						poliklinik.addClinic(fld_clinic.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					fld_clinic.setText(null);
					updateClinicModel();
				}
			}
		});
		btnEkle_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1.setBounds(229, 94, 121, 32);
		w_ClinicPanel.add(btnEkle_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(392, 10, 311, 289);
		w_ClinicPanel.add(scrollPane_1);

		w_tableworker = new JTable();
		scrollPane_1.setViewportView(w_tableworker);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(230, 235, 120, 21);
		for (int i = 0; i < bashekim.addDoctorList().size(); i++) {
			comboBox.addItem(new Item (bashekim.addDoctorList().get(i).getId(),bashekim.addDoctorList().get(i).getName()));
		}
		w_ClinicPanel.add(comboBox);
		
		JButton btnEkle_1_1 = new JButton("Ekle");
		btnEkle_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = w_tableclinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = w_tableclinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) comboBox.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btnEkle_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1_1.setBounds(229, 266, 121, 32);
		w_ClinicPanel.add(btnEkle_1_1);
		
		JLabel lblNewLabel_1_1_2_2_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_1_2_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel_1_1_2_2_1.setBounds(239, 145, 96, 22);
		w_ClinicPanel.add(lblNewLabel_1_1_2_2_1);
		
		JButton btnEkle_1_1_1 = new JButton("Se\u00E7");
		btnEkle_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = w_tableclinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = w_tableclinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clear = (DefaultTableModel) w_tableworker.getModel();
					clear.setRowCount(0);
					for (int i = 0; i < bashekim.addClinicDoctorList(selClinicID).size(); i++) {
						workerData[0] = bashekim.addClinicDoctorList(selClinicID).get(i).getId();
						workerData[1] = bashekim.addClinicDoctorList(selClinicID).get(i).getName();
						workerModel.addRow(workerData);
					}
					w_tableworker.setModel(workerModel);
				}
				else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btnEkle_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnEkle_1_1_1.setBounds(229, 177, 121, 32);
		w_ClinicPanel.add(btnEkle_1_1_1);
		w_tabledoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(w_tabledoctor.getValueAt(w_tabledoctor.getSelectedRow(), 0).toString());
				} catch (Exception e1) {
				}
				;
			}

		});
		w_tabledoctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(w_tabledoctor.getValueAt(w_tabledoctor.getSelectedRow(), 0).toString());
					String selectName = w_tabledoctor.getValueAt(w_tabledoctor.getSelectedRow(), 1).toString();
					String selectTcNo = w_tabledoctor.getValueAt(w_tabledoctor.getSelectedRow(), 2).toString();
					String selectPass = w_tabledoctor.getValueAt(w_tabledoctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectID, selectName, selectTcNo, selectPass);
						if (control) {
							Helper.showMsg("success");

						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}

		});
	}

	public void updateDoktorModel() {
		DefaultTableModel clear = (DefaultTableModel) w_tabledoctor.getModel();
		clear.setRowCount(0);
		for (int i = 0; i < bashekim.addDoctorList().size(); i++) {
			doktorData[0] = bashekim.addDoctorList().get(i).getId();
			doktorData[1] = bashekim.addDoctorList().get(i).getName();
			doktorData[2] = bashekim.addDoctorList().get(i).getTcno();
			doktorData[3] = bashekim.addDoctorList().get(i).getPassword();
			doktorModel.addRow(doktorData);
		}
	}

	public void updateClinicModel() {
		DefaultTableModel clear = (DefaultTableModel) w_tableclinic.getModel();
		clear.setRowCount(0);
		for (int i = 0; i < poliklinik.addClinicList().size(); i++) {
			clinicData[0] = poliklinik.addClinicList().get(i).getId();
			clinicData[1] = poliklinik.addClinicList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
