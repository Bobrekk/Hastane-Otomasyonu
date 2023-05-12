package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {
	Statement s = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public Doctor() {
		super();
	}
	public Doctor(int id, String name, String tcno, String password, String type) {
		super(id, name, tcno, password, type);
	}
	public boolean addWhour (int doctorid , String doctorname , String wDate ) throws SQLException {
		int key = 0;
		int counter = 0;
		Connection c = conn.connDb();
		s = c.createStatement();
		rs = s.executeQuery("SELECT * FROM whour WHERE status = 'A' AND doctorid = " + doctorid + " AND wdate = '" + wDate + "'");
		String query = "INSERT INTO whour" + "(doctorid,doctorname,wdate) VALUES " + "(?,?,?)";
		while (rs.next()) {
			counter++;
		}
		if (counter == 0) {
			ps = c.prepareStatement(query);
			ps.setInt(1, doctorid);
			ps.setString(2, doctorname);
			ps.setString(3, wDate);
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
	public ArrayList <wHour> addwHourList(int doctorID) {
		ArrayList <wHour> list = new ArrayList <>();
		wHour obj;
		try {
			Connection c = conn.connDb();
			c = conn.connDb();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM whour WHERE status = 'A' AND doctorid = " + doctorID);
			while (rs.next()) {
				obj = new wHour ();
				obj.setDoctorid(rs.getInt("id"));
				obj.setDoctorname(rs.getString("doctorname"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				Connection c = conn.connDb();
				c.close();
				s.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public boolean deleteDoctor (int ID){
		Connection c = conn.connDb();
		c = conn.connDb();
		boolean key = false;
		String query = "DELETE FROM whour WHERE id = ?";
		try {
			s = c.createStatement();
			ps = c.prepareStatement(query);
			ps.setInt(1, ID);
			ps.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == false) {
			return false;
		}
		else {
			return true;
		}
	}
}
