package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;
import Helper.Helper;

public class Appointment {
	int doctorid, hastaid, id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String doctorname, hastaname, appdate;
	DBConnection conn = new DBConnection ();
	Connection c = null;
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public Appointment(int doctorid, int hastaid, String doctorname, String hastaname, String appdate) {
		super();
		this.doctorid = doctorid;
		this.hastaid = hastaid;
		this.doctorname = doctorname;
		this.hastaname = hastaname;
		this.appdate = appdate;
	}
	public Appointment() {}
	
	public ArrayList<Appointment> addAppointmentList(int hastaid) {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM appointment WHERE hastaid = " + hastaid);
			while (rs.next()) {
				obj = new Appointment ();
				obj.setDoctorid(rs.getInt("doctorid"));
				obj.setHastaid(rs.getInt("hastaid"));
				obj.setDoctorname(rs.getString("doctorname"));
				obj.setHastaname(rs.getString("hastaname"));
				obj.setAppdate(rs.getString("appdate"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
				s.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<Appointment> addAppointmentDoctorList(int hastaid) {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		try {
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM appointment WHERE hastaid = " + hastaid);
			while (rs.next()) {
				obj = new Appointment ();
				obj.setDoctorid(rs.getInt("doctorid"));
				obj.setHastaid(rs.getInt("hastaid"));
				obj.setDoctorname(rs.getString("doctorname"));
				obj.setHastaname(rs.getString("hastaname"));
				obj.setAppdate(rs.getString("appdate"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
				s.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public int getHastaid() {
		return hastaid;
	}
	public void setHastaid(int hastaid) {
		this.hastaid = hastaid;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getHastaname() {
		return hastaname;
	}
	public void setHastaname(String hastaname) {
		this.hastaname = hastaname;
	}
	public String getAppdate() {
		return appdate;
	}
	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}
	
	
	
}
