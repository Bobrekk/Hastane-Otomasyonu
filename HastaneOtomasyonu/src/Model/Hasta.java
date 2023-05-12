package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Hasta extends User {
	Connection c = conn.connDb();
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	public Hasta() {
		super();
	}

	public Hasta(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}
	
	public boolean register (String name, String tcno, String pass ) throws SQLException {
		int key = 0;
		int counter = 0;
		s = c.createStatement();
		rs = s.executeQuery("SELECT * FROM user WHERE tcno = " + "'" + tcno + "'");
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES " + "(?,?,?,?)";
		while (rs.next()) {
			counter++;
			Helper.showMsg("Bu TC numarasýna ait kayýt bulunmaktadýr !");
		}
		if (counter == 0) {
			ps = c.prepareStatement(query);
			ps.setString(1, tcno);
			ps.setString(2, pass);
			ps.setString(3, name);
			ps.setString(4, "hasta");
			ps.executeUpdate();
			key = 1;
		}
		if (key == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addAppointment (int doctorid, String doctorname, int hastaid, String hastaname, String appdate) throws SQLException {
		int key = 0;
		s = c.createStatement();
		String query = "INSERT INTO appointment" + "(doctorid,doctorname,hastaid,hastaname,appdate) VALUES " + "(?,?,?,?,?)";
			ps = c.prepareStatement(query);
			ps.setInt(1, doctorid);
			ps.setString(2, doctorname);
			ps.setInt(3, hastaid);
			ps.setString(4, hastaname);
			ps.setString(5,appdate);
			ps.executeUpdate();
			key = 1;
		if (key == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean UpdateAppointment (int doctorid, String wdate) throws SQLException {
		int key = 0;
		s = c.createStatement();
		String query = "UPDATE whour SET status = ? WHERE doctorid = ? AND wdate = ?";
			ps = c.prepareStatement(query);
			ps.setString(1, "P");
			ps.setInt(2, doctorid);
			ps.setString(3, wdate);
			ps.executeUpdate();
			key = 1;
		if (key == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}
